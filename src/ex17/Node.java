package ex17;

import java.lang.Comparable;
import java.util.ArrayList;

public class Node implements Comparable<Node> {
	protected static int nodeCount;
	private ArrayList<Integer> itemsChosen;
	private ArrayList<Integer> itemsIgnored;
	private float thisCount;
	private float level;
	private float profit;
	private float weight;
	private float bound;
	
	public Node(ArrayList<Integer> itemsChosen, ArrayList<Integer> itemsIgnored, float level, float price, float weight) {
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
		return "<Node " + thisCount + ": items: " + itemsChosen + " level: " + level + " profit: " + profit + " weight: " + weight + " bound: " + bound + ">";
	}
	
	public float getProfit() {
		return profit;
	}

	public float getWeight() {
		return weight;
	}

	public float getBound() {
		return bound;
	}
	
	public void setProfit(float profit) {
		this.profit = profit;
	}
	
	public void setWeight(float weight) {
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
	
	public float getLevel() {
		return level;
	}

	public float getThisCount() {
		return thisCount;
	}
	
	public void setItemsChosen(ArrayList<Integer> itemsChosen) {
		this.itemsChosen = itemsChosen;
	}

	public void setItemsIgnored(ArrayList<Integer> itemsIgnored) {
		this.itemsIgnored = itemsIgnored;
	}

	public void setLevel(float level) {
		this.level = level;
	}

	public void setThisCount(float thisCount) {
		this.thisCount = thisCount;
	}
}
