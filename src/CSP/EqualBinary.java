package CSP;

public class EqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public EqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	//Checks the bag that each item belongs to. 
	//If the items belong to the same bag, or if either one of the items have yet to be assigned, return true
	//If the items belong to different bags, return false
	public boolean isValid() {
		if(item1.getBag() == null || item2.getBag() == null)
			return true;
		if(item1.getBag().getName() == item2.getBag().getName())
			return true;
		return false;
	}

}
