package CSP;


public class Assignment {
	private Bag bag;
	private Item item;
		
	public Assignment (Bag b, Item i) {
		bag = b;
		item = i;
	}
	
	public Bag getBag () {
		return bag;
	}
	
	public Item getItem () {
		return item;
	}
	
	
	
}
