package CSP;
/**
 * @author Dan True, Nick
 *
 */
public class Item {
	private char name;
	private int weight;
	private Bag location;
	
	public Item (char name, int weight) {
		this.name = name;
		this.weight = weight;
		location = null;
	}
	
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
	
	public Bag getBag() {
		return this.location;
	}
	
	public void setBag(Bag newBag) {
		this.location = newBag;
	}
	
}
