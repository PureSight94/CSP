package CSP;

import java.util.ArrayList;

/**
 * @author Dan True, Nick
 *
 */
public class Item implements Comparable<Item>{
	private char name;
	private int weight;
	private ArrayList<Bag> possibleBags;
	
	public Item (char name, int weight) {
		this.name = name;
		this.weight = weight;
		possibleBags = new ArrayList<Bag>();
	}
	
	/*************************************************************
	 * GETTERS
	 ************************************************************/
	
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public ArrayList<Bag> getPossibleBags() {
		return this.possibleBags;
	}
	
	/*
	 * Adds a bag to the Items list of possible, legal, bags
	 */
	public void addPossibleLocation(Bag newBag) {
		this.possibleBags.add(newBag);
	}
	
	/*
	 * Removes a bag from an Items list of possible, legal, bags
	 * Usually done when we make an assignment, used for forward checking
	 */
	public void removePossibleLocation(Bag existingBag) {
		this.possibleBags.remove(existingBag);
	}
	
	/*
	 * Resets the list of possible bags for this item 
	 * back to an empty ArrayList of type Bag
	 */
	public void clearPossibleLocations() {
		this.possibleBags = new ArrayList<Bag>();
	}
	
	/*
	 * Overides the equals method and returns true when
	 * the two given items have the same name and same weight
	 */
	public boolean equals(Item i) {
		return (this.name == i.name && this.weight == i.weight);
	}

	/*
	 * Implementing the compareTo method so that we can sort a list of Items
	 * We compare the items from smallest amount of possible bags, to those with larger.
	 * When the sizes are equal, we sort by the larger weights to smaller weights.
	 */
	public int compareTo(Item i2) {
		int i1Size = this.possibleBags.size();
		int i2Size = i2.possibleBags.size();
		int i1Weight = this.getWeight();
		int i2Weight = i2.getWeight();
		
		if(i1Size > i2Size)
			return 1;
		else if(i1Size < i2Size)
			return -1;
		else {
			if(i1Weight > i2Weight)
				return -1;
			else if(i1Weight < i2Weight)
				return 1;
			else
				return 0;
		}
	}
	
}
