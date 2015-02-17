package CSP;


/**
 * @author Dan True, Nick
 *
 */
public class Bag {
	private char name;
	private int weightCapacity;
	
	public Bag (char name, int weightCapacity) {
		this.name = name;
		this.weightCapacity = weightCapacity;
	}
	
	public boolean equals (Bag b) {
		return (this.name == b.name && this.weightCapacity == b.weightCapacity);
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
}
