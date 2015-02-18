package CSP;

import java.util.ArrayList;

public class MutualExclusiveBinary implements IConstraint {

	private Item item1;
	private Item item2;
	private Bag bag1;
	private Bag bag2;
	
	public MutualExclusiveBinary(Item i1, Item i2, Bag b1, Bag b2) {
		item1 = i1;
		item2 = i2;
		bag1 = b1;
		bag2 = b2;
	}
	
	@Override
	public boolean isValid(ArrayList<Assignment> assignments) {
		Bag b1 = null;
		Bag b2 = null;
		for(Assignment a: assignments) {
			if(a.getItem().equals(item1)) {
				b1 = a.getBag();
			}
			if(a.getItem().equals(item2)) {
				b2 = a.getBag();
			}
			if(b1 != null && b2 != null) {
				if(b1.equals(bag1) && b2.equals(bag2)) 
					return false;
				if(b1.equals(bag2) && b2.equals(bag1))
					return false;
			}
		}
		return true;
	}
}







