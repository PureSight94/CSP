package CSP;

import java.util.ArrayList;

/**
 * @author Dan True, Nick
 *
 */
public class Item {
	private char name;
	private int weight;
	private ArrayList<Bag> possibleLocations;
	
	public Item (char name, int weight) {
		this.name = name;
		this.weight = weight;
		possibleLocations = new ArrayList<Bag>();
	}
	
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public ArrayList<Bag> getPossibleLocations() {
		return this.possibleLocations;
	}
	
	public void addPossibleLocation(Bag newBag) {
		this.possibleLocations.add(newBag);
	}
	
	public void removePossibleLocation(Bag existingBag) {
		this.possibleLocations.remove(existingBag);
	}
	
	public void clearPossibleLocations() {
		this.possibleLocations = new ArrayList<Bag>();
	}
	
	public boolean equals(Item i) {
		return (this.name == i.name && this.weight == i.weight);
	}
	
}
