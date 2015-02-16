package CSP;

import java.util.ArrayList;


/**
 * @author Dan True, Nick
 *
 */
public class Bag {
	private char name;					//The name of this bag (lower case letters, values)
	private int weightCapacity;			//The maximum allowed weight this bag can have
	private int currentWeight;			//The current total weight of this bag
	private ArrayList<Item> items;		//The current list of items in this bag
	private int numItems;
	
	public Bag (char name, int weightCapacity) {
		this.name = name;
		this.weightCapacity = weightCapacity;
		currentWeight = 0;
		items = new ArrayList<Item>();
		numItems = 0;
	}
	
	public void addItem (Item i) {
		items.add(i);
		currentWeight += i.getWeight();
		numItems++;
	}
	
	public void removeItem (Item i) {
		currentWeight -= i.getWeight();
		items.remove(i);
		numItems--;
	}
	
	
	/*************************************************************
	 * GETTERS AND SETTERS
	 */
	public char getName () {	
		return name;
	}
	
	public int getWeightCapacity () {
		return weightCapacity;
	}
	
	public int getCurrentWeight () {
		return currentWeight;
	}
	
	public ArrayList<Item> getItems() {
		return this.items;
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public boolean equals(Bag b) {
		if(this.name == (b.name))
			return true;
		return false;
	}
}
