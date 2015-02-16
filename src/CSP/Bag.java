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
	
	public Bag (char name, int weightCapacity) {
		this.name = name;
		this.weightCapacity = weightCapacity;
		currentWeight = 0;
		items = new ArrayList<Item>();
	}
	
	public void addItem (Item i) {
		items.add(i);
		currentWeight += i.getWeight();
	}
	
	public void removeItem (Item i) {
		currentWeight -= i.getWeight();
		items.remove(i);
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
}
