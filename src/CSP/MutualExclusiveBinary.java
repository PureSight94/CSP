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
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	
	
}
