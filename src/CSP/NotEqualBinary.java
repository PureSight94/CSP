package CSP;

import java.util.ArrayList;

public class NotEqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public NotEqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	@Override
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
	
	public static void main(String[] args) {
		Item i1 = new Item('A', 2);
		Item i2 = new Item('B', 3);
		Bag b1 = new Bag('a', 4);
		Bag b2 = new Bag('b', 5);
		
		ArrayList<Assignment> aList = new ArrayList<Assignment>();
		NotEqualBinary neB = new NotEqualBinary(i1, i2);
		Assignment i1Tob1 = new Assignment(b1, i1);
		Assignment i2Tob1 = new Assignment(b1, i2);
		Assignment i2Tob2 = new Assignment(b2, i2);
		
		aList.add(i1Tob1);
		
		System.out.println("i1 put into b1 only: " + neB.isValid(aList));
		
		aList.add(i2Tob1);
		
		System.out.println("i2 put into b1, and i1 put into b1: " + neB.isValid(aList));
		
		aList.remove(i2Tob1);
		aList.add(i2Tob2);
		
		System.out.println("i2 put into b2, and i1 put into b1: " + neB.isValid(aList));
		
		
	}
}
