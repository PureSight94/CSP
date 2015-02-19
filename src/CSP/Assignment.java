package CSP;


public class Assignment {
	private Bag bag;
	private Item item;
		
	public Assignment (Bag b, Item i) {
		this.bag = b;
		this.item = i;
	}
	
	/*************************************************************
	 * GETTERS
	 ************************************************************/
	
	public Bag getBag () {
		return bag;
	}
	
	public Item getItem () {
		return item;
	}
	
	
	
}
