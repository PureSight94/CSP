package CSP;
/**
 * @author Dan True, Nick
 *
 */
public class Item {
	private char name;		//CSP variables (upper case letters)
	private int weight;		//The weight of this item
	private Bag location;	//The Bag in which this item is in
	
	public Item (char name, int weight) {
		this.name = name;
		this.weight = weight;
		location = null;
	}
	
	//Getters
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public Bag getBag() {
		return this.location;
	}
	
	//Setters
	public void setBag(Bag newBag) {
		this.location = newBag;
	}
	
}
