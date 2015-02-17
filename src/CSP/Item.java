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
	
	public ArrayList<Bag> getBag() {
		return this.possibleLocations;
	}
	
	public void addBag(Bag newBag) {
		this.possibleLocations.add(newBag);
	}
	
}
