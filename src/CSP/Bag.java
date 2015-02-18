package CSP;


/**
 * @author Dan True, Nick
 *
 */
public class Bag {
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
	
	public boolean equals (Bag b) {
		return (this.name == b.name && this.weightCapacity == b.weightCapacity);
	}
	
	public int incrementWeight(int weight) {
		currentWeight += weight; 
		return currentWeight;
	}
	
	public int decrementWeight(int weight) {
		currentWeight -= weight;
		return currentWeight;
	}
	
	
	public int incrementCount() {
		itemCount++;
		return itemCount;
	}
	
	public int decrementCount() {
		itemCount--;
		return itemCount;
	}
	/*************************************************************
	 * GETTERS AND SETTERS
	 */
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
