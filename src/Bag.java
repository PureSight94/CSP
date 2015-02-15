import java.util.ArrayList;


/**
 * @author Dan True, Nick
 *
 */
public class Bag {
	private char name;
	private int weightCapacity;
	private int currentWeight;
	private ArrayList<Item> items;
	
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
