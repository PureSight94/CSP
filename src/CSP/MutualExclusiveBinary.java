package CSP;

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
	
	//Example A B x y
	//If A has the value x, B cannot have value y
	//If B has value x, A cannot have value y
	//If one of these values are null, then the constraint is not violated, return true
	public boolean isValid() {
		if(item1.getBag().equals(null) || item2.getBag().equals(null)) {
			return true;
		}
		if(item1.getBag().getName() == bag1.getName() && item2.getBag().getName() == bag2.getName()) 
			return false;
		if(item1.getBag().getName() == bag2.getName() && item2.getBag().getName() == bag1.getName())
			return false;
		return true;
	}

	
	
}
