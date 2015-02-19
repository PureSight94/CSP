package CSP;

import java.util.ArrayList;

public class EqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public EqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	/*
	 * Checks this constraint and returns false only if the 
	 * two items specified in the constraint are both assigned
	 * and are not in the same bag
	 */
	public boolean isValid(ArrayList<Assignment> assignments) {
		Bag b1 = null;
		Bag b2 = null;
		for(Assignment a: assignments) {
			if(a.getItem().equals(item1)) {
				b1 = a.getBag();
			}
			if(a.getItem().equals(item2)) {
				b2= a.getBag();
			}
			if(b1 != null && b2 != null) {
				return(b1.equals(b2));
			}
		}

		return true;
	}	
}
