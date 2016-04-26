package ex17;

import java.lang.Comparable;
import java.util.ArrayList;

public class Node implements Comparable<Node> {
	private static int nodeCount = 1;
	private ArrayList<Integer> itemsChosen;
	private ArrayList<Integer> itemsIgnored;
	private int thisCount;
	private int level;
	private int profit;
	private int weight;
	private float bound;
	
	public Node(ArrayList<Integer> itemsChosen, ArrayList<Integer> itemsIgnored, int level, int price, int weight) {
		this.itemsChosen = itemsChosen;
		this.itemsIgnored = itemsIgnored;
		thisCount = nodeCount;
		nodeCount++;
		this.level = level;
		this.profit = price;
		this.weight = weight;
	}
	
	/**
	 * Compares two nodes.<br/>
	 * <b>Note:</b> Does not handle exceptions, such as NullPointerException or ClassCastException.
	 *  @param otherNode The node to compare with.
	 * @return 1 for <, 0 for ==, and -1 for >
	 */
	@Override
	public int compareTo(Node otherNode) {
		float otherBound = otherNode.getBound();
		if(bound < otherBound) {
			return 1;
		} else if(bound == otherBound) {
			return 0;
		}
		return -1;
	}

	@Override
	public String toString() {
		return "<Node " + thisCount + " items: " + itemsChosen + " level: " + level + " profit: " + profit + " weight: " + weight + " bound: " + bound + ">";
	}
	
	public int getProfit() {
		return profit;
	}

	public int getWeight() {
		return weight;
	}

	public float getBound() {
		return bound;
	}
	
	public void setProfit(int profit) {
		this.profit = profit;
	}
	
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setBound(float bound) {
		this.bound = bound;
	}

	public ArrayList<Integer> getItemsChosen() {
		return itemsChosen;
	}

	public ArrayList<Integer> getItemsIgnored() {
		return itemsIgnored;
	}
	
	public int getLevel() {
		return level;
	}

	public int getThisCount() {
		return thisCount;
	}
	
	public void setItemsChosen(ArrayList<Integer> itemsChosen) {
		this.itemsChosen = itemsChosen;
	}

	public void setItemsIgnored(ArrayList<Integer> itemsIgnored) {
		this.itemsIgnored = itemsIgnored;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setThisCount(int thisCount) {
		this.thisCount = thisCount;
	}
}
