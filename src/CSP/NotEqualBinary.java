package CSP;

public class NotEqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public NotEqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	//Checks the location of each item.
	//Returns true if the items are in different bags or if one has yet to be assigned
	//Returns false if they are in the same bag
	public boolean isValid() {
		if(item1.getBag().equals(null) || item2.getBag().equals(null))
			return true;
		if(item1.getBag().getName() == (item2.getBag().getName()))
			return false;
		return true;
	}

}
