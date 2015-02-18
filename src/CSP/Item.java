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
	
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public ArrayList<Bag> getPossibleBags() {
		return this.possibleBags;
	}
	
	public void addPossibleLocation(Bag newBag) {
		this.possibleBags.add(newBag);
	}
	
	public void removePossibleLocation(Bag existingBag) {
		this.possibleBags.remove(existingBag);
	}
	
	public void clearPossibleLocations() {
		this.possibleBags = new ArrayList<Bag>();
	}
	
	public boolean equals(Item i) {
		return (this.name == i.name && this.weight == i.weight);
	}

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
