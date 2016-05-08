package ex17;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Ex17 {
	private static int weightCapacity;
	private static float[][] table;
	
	/**
	 * Driver of the program.
	 * @param args Contains The full file path of the data file.
	 */
	public static void main(String[] args) {
		Node.nodeCount = 1;
		table = readDataFile(args);
		branchBoundAlg();
	}
	
	/**
	 * Driver for unit tests.
	 */
	public static float testDriver(String[] args) {
		Node.nodeCount = 1;
		table = readDataFile(args);
		return branchBoundAlg();
	}
	
	/**
	 * Reads the user's data input file, and returns a 2D array of the data<br/>
	 * <b>Note:</b> Assumes data file is in the correct format.
	 * @param args Used if file path is passed in through command line argument.
	 * @return 2D array of data.
	 */
	private static float[][] readDataFile(String[] args) {
		float[][] table = null;
		
		// Get data file
		File dataFile = null;
		if(args != null) {
			dataFile = new File(args[0]);
			
			// Get data from file
			Scanner input = null;
			try {
				input = new Scanner(dataFile);
				weightCapacity = input.nextInt();
				table = new float[input.nextInt()][3];
				for(int i = 0; i < table.length; i++) {
					float profit = input.nextInt();
					float weight = input.nextInt();
					float pPerWeight = profit/weight;
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
	private static float branchBoundAlg() {
		// Build the display for the table
		StringBuilder sb = buildTableDisplay();
		
	   // Display first Node
		sb.append("\nBegin Exploration of the possibilities tree:\n\nExploring ");
		Node firstNode = new Node(new ArrayList<Integer>(0), new ArrayList<Integer>(0), 0, 0, 0);
		calculateBound(firstNode);
		sb.append(firstNode);
		firstNode = null;
		
		// Begin Branch and bound
		Node noChoice = new Node(new ArrayList<Integer>(0), new ArrayList<Integer>(1), 1, 0, 0);
		noChoice.getItemsIgnored().add(1);
		Node choice = new Node(new ArrayList<Integer>(1), new ArrayList<Integer>(0), 1, table[0][0], table[0][1]);
		Node qChoice = null;
		choice.getItemsChosen().add(1);
		int level = 1;
		PriorityQueue<Node> q = new PriorityQueue<>();
		while(true) {
			// No choice option
			sb.append("\n  Left child is ");
			if(noChoice.getWeight() <= weightCapacity) {
				calculateBound(noChoice);
				q.add(noChoice);
				if (noChoice.getWeight() == weightCapacity){
					sb.append(noChoice);
					sb.append("\n    hit capacity exactly, so don't explore further");	
				} else {
					sb.append(noChoice);
					sb.append("\n    Explore Further");
				}
			} else {
				noChoice.setBound(noChoice.getProfit());
				sb.append(noChoice);
				sb.append("\n    Pruned becuase too heavy");
			}
			
			// choice option
			sb.append("\n  Right child is ");
			if(choice.getWeight() <= weightCapacity) {
				calculateBound(choice);
				q.add(choice);
				if (choice.getWeight() == weightCapacity){
					if(q.peek() != choice) {
						q.remove(); // Remove because there is something better
					}
					sb.append(choice);
					sb.append("\n    hit capacity exactly, so don't explore further");	
				} else {
					sb.append(choice);
					sb.append("\n    Explore Further");
				}
			} else {
				choice.setBound(choice.getProfit());
				sb.append(choice);
				sb.append("\n    pruned becuase too heavy");
			}
			
			// Pursue highest bound
			qChoice = q.remove();
			
			// Best choice
			if(qChoice.getWeight() == weightCapacity || qChoice.getLevel() == table.length) {
				sb.append("\n    note achievable profit of ");
				sb.append(qChoice.getProfit());
				
				// Prune remaining branches
				while(!q.isEmpty()) {
					Node prune = q.remove();
					sb.append("\n\nExploring ");
					sb.append(prune);
					sb.append("\n    pruned, don't explore children because "
							+ "bound " + prune.getBound() + " is smaller than known achievable profit " +  qChoice.getProfit());
				}
				sb.append("\n\nBest node: ");
				sb.append(qChoice);
				System.out.println(sb);
				return qChoice.getProfit();
			} else {
				sb.append("\n    note achievable profit of ");
				sb.append(qChoice.getProfit());
				sb.append("\n\nExploring ");
				sb.append(qChoice);
			}
			
			level = (int)qChoice.getLevel() + 1;
			
			// Create new no choice node
			ArrayList<Integer> temp;
			Node newC = null;
			Node newNC = null;
			float newProfit = 0;
			float newWeight = 0;
			newNC = new Node(qChoice.getItemsChosen(), null, level, qChoice.getProfit(), qChoice.getWeight());
			temp = new ArrayList<>(qChoice.getItemsIgnored());
			temp.add(level);
			newNC.setItemsIgnored(temp);
			
			// Create new choice node
			newProfit = qChoice.getProfit() + table[level - 1][0];
			newWeight = qChoice.getWeight() + table[level - 1][1];
			newC = new Node(null, qChoice.getItemsIgnored(), level, newProfit, newWeight);
			temp = new ArrayList<>(qChoice.getItemsChosen());
			temp.add(level);
			newC.setItemsChosen(temp);
			
			// reset choices
			choice = newC;
			noChoice = newNC;
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
			
			// Set bound to profit if all items are handled
			if(chosen.size() + ignored.size() == table.length) {
				node.setBound(node.getProfit());
				return;
			}
			
			boolean skipItem = false;
			float bound = node.getProfit();
			float totalWeight = node.getWeight();
			for(int i = 0; i < table.length; i++) {
				float profit = table[i][0];
				float weight = table[i][1];
				float pPerW = table[i][2];
				
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
