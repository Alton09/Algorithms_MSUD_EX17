package ex17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Ex17 {
	private static int weightCapacity;
	private static int[][] table;
	
	/**
	 * Driver of the program.
	 * @param args Contains The full file path of the data file.
	 */
	public static void main(String[] args) {
		table = readDataFile(args);
		branchBoundAlg();
	}
	
	/**
	 * Reads the user's data input file, and returns a 2D array of the data<br/>
	 * <b>Note:</b> Assumes data file is in the correct format.
	 * @param args Used if file path is passed in through command line argument.
	 * @return 2D array of data.
	 */
	private static int[][] readDataFile(String[] args) {
		int[][] table = null;
		
		// Get data file
		File dataFile = null;
		if(args != null) {
			dataFile = new File(args[0]);
			
			// Get data from file
			Scanner input = null;
			try {
				input = new Scanner(dataFile);
				weightCapacity = input.nextInt();
				table = new int[input.nextInt()][3];
				for(int i = 0; i < table.length; i++) {
					int profit = input.nextInt();
					int weight = input.nextInt();
					int pPerWeight = Math.round(profit/weight);
					table[i][0] = profit;
					table[i][1] = weight;
					table[i][2] = pPerWeight;
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Exiting program.");
				input.close();
				System.exit(1);
			} 
		} else {
			System.out.println("No data file found in parameter.\nExiting program.");
			System.exit(1);
		}
		return table;
	}
	
	/**
	 * The Branch & Bound 0-1 knapsack algorithm
	 */
	private static void branchBoundAlg() {
		// Build the display for the table
		StringBuilder sb = buildTableDisplay();
		
	   // Display first Node
		sb.append("Exploring ");
		Node firstNode = new Node(new ArrayList<Integer>(0), new ArrayList<Integer>(0), 0, 0, 0);
		calculateBound(firstNode);
		sb.append(firstNode);
		firstNode = null;
		
		// Begin Branch and bound
		Node noChoice = new Node(new ArrayList<Integer>(0), new ArrayList<Integer>(1), 1, 0, 0);
		noChoice.getItemsIgnored().add(1);
		Node choice = new Node(new ArrayList<Integer>(1), new ArrayList<Integer>(0), 1, table[0][0], table[0][1]);
		choice.getItemsChosen().add(1);
		int level = 1;
		PriorityQueue<Node> q = new PriorityQueue<>();
		boolean done = false;
		while(!done) {
			if(noChoice.getWeight() <= weightCapacity) {
				q.add(noChoice);
				calculateBound(noChoice);
			}
			if(choice.getWeight() <= weightCapacity) {
				q.add(choice);
				calculateBound(choice);
			}
			
			// Pursue highest bound
			// TODO This condition needs to be getting highest bound from queue
			if(choice.getBound() > noChoice.getBound()) {
				ArrayList<Integer> temp;
				Node newC = null;
				Node newNC = null;
				level = choice.getLevel() + 1;
				int newProfit = 0;
				int newWeight = 0;
				
				// Create new no choice node
				newNC = new Node(choice.getItemsChosen(), null, level, choice.getProfit(), choice.getWeight());
				temp = new ArrayList<>(choice.getItemsIgnored());
				temp.add(level);
				newNC.setItemsIgnored(temp);
				
				// Create new choice node
				newProfit = choice.getProfit() + table[level - 1][0];
				newWeight = choice.getWeight() + table[level - 1][1];
				newC = new Node(null, choice.getItemsIgnored(), level, newProfit, newWeight);
				temp = new ArrayList<>(choice.getItemsChosen());
				temp.add(level);
				newC.setItemsChosen(temp);
				
				// reset choices
				choice = newC;
				noChoice = newNC;
			} else {
				
			}
		}
	}
	
	/**
	 * Builds the display for the 0-1 knapsack table.
	 * @return A StringBuilder reference containing the table display.
	 */
	private static StringBuilder buildTableDisplay() {
		StringBuilder sb= new StringBuilder("Capacity of knapsack is ");
		sb.append(weightCapacity);
		sb.append("\nItems are:\n");
		for(int i = 0; i < table.length; i++) {
			sb.append(i + 1);
			sb.append(": ");
			sb.append(table[i][0]);
			sb.append(' ');
			sb.append(table[i][1]);
			sb.append('\n');
		}
		return sb;
	}
	
	/**
	 * Calculates Bound for a Node.
	 * @param node Node containing bound to be set.
	 */
	private static void calculateBound(Node node) {
		if(node != null) {
			ArrayList<Integer> chosen = node.getItemsChosen();
			ArrayList<Integer> ignored = node.getItemsIgnored();
			boolean skipItem = false;
			int bound = node.getProfit();
			int totalWeight = node.getWeight();
			for(int i = 0; i < table.length; i++) {
				int profit = table[i][0];
				int weight = table[i][1];
				int pPerW = table[i][2];
				
				// Skip chosen and ignored items
				if(!chosen.isEmpty() || !ignored.isEmpty()) {
					if(chosen.contains(i+1) || ignored.contains(i+1)) {
						skipItem = true;
					}
				}
				
				// Accumulate bound
				if(!skipItem) {
					if((weight + totalWeight) <= weightCapacity) {
						totalWeight += weight; 
						bound += profit;
					} else  { 
						// Finish Calculation
						bound += (weightCapacity - totalWeight) * pPerW;
						break;
					}
				}
				skipItem = false;
			}
			
			node.setBound(bound);
		}
	}
}
