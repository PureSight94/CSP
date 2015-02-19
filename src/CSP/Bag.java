package CSP;


/**
 * @author Dan True, Nick
 *
 */
public class Bag implements Comparable<Bag> {
	private char name;
	private int weightCapacity;
	private int currentWeight;
	private int itemCount;
	
	public Bag (char name, int weightCapacity) {
		this.name = name;
		this.weightCapacity = weightCapacity;
		currentWeight = 0;
		itemCount = 0;
	}
	
	/*
	 * Sets the given bag to empty by making its item count
	 * and current weight 0
	 */
	public void clear() {
		this.currentWeight = 0;
		this.itemCount = 0;
	}
	
	/*
	 * Implements the compareTo method so we can sort a list of Bags
	 * Sorts the Bag by the ones with the largest available space to smallest. 
	 */
	public int compareTo(Bag b2) {
		int b1Available = this.getWeightCapacity() - this.getCurrentWeight();
		int b2Available = b2.getWeightCapacity() - b2.getCurrentWeight();
		if(b1Available > b2Available) {
			return -1;
		}
		else if(b1Available < b2Available) {
			return 1;
		}
		else 
			return 0;
	}
	
	/*
	 * Overrides the equals method to return true if the given
	 * bags have the same name and weight capacity.
	 */
	public boolean equals (Bag b) {
		return (this.name == b.name && this.weightCapacity == b.weightCapacity);
	}
	
	/*
	 * Increments the current weight of the bag by the amount specified
	 * We use this when making new assignments with the given bag
	 */
	public int incrementWeight(int weight) {
		currentWeight += weight; 
		return currentWeight;
	}
	
	/*
	 * Decrements the weight of the given bag by the amount specified
	 * We use this when removing assignments with the given bag
	 */
	public int decrementWeight(int weight) {
		currentWeight -= weight;
		return currentWeight;
	}
	
	/*
	 * Increments the item count of the given bag.
	 * We use this when making an assignment with the given bag
	 */
	public int incrementCount() {
		itemCount++;
		return itemCount;
	}
	
	/*
	 * Decrements the item count of the given bag
	 * We use this when removing an assignment with the given bag
	 */
	public int decrementCount() {
		itemCount--;
		return itemCount;
	}
	
	/*************************************************************
	 * GETTERS
	 ************************************************************/
	public int getCurrentWeight() {
		return this.currentWeight;
	}
	
	public int getItemCount() {
		return this.itemCount;
	}
	
	public char getName () {	
		return name;
	}
	
	public int getWeightCapacity () {
		return weightCapacity;
	}
}
