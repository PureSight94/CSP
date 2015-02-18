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
