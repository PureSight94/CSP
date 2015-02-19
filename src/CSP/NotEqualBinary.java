package CSP;

import java.util.ArrayList;

public class NotEqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public NotEqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	/*
	 * Checks this constraint and returns false only if
	 * the two items specified in the constraint are in
	 * the same bag.
	 */
	public boolean isValid(ArrayList<Assignment> assignments) {
		int seenCount = 0;
		Bag b1 = null;
		Bag b2 = null;
		
		for(Assignment a : assignments) {
			if(a.getItem().equals(item1) || a.getItem().equals(item2)) {
				seenCount++;
				if(seenCount == 1) {
					b1 = a.getBag();
				}
				if(seenCount == 2) {
					b2 = a.getBag();
					
					return !(b1.equals(b2));
				}
			}
		}
		
		return true;
	}
}
