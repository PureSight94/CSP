package CSP;
/**
 * @author Dan True, Nick
 *
 */
public class Item {
	private char name;
	private int weight;
	
	public Item (char name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public char getName () {
		return name;
	}
	
	public int getWeight () {
		return weight;
	}
}
