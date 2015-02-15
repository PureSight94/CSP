package CSP;

public class NotEqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public NotEqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

}
