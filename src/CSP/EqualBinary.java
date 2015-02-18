package CSP;

import java.util.ArrayList;

public class EqualBinary implements IConstraint {

	private Item item1;
	private Item item2;
	
	public EqualBinary(Item i1, Item i2) {
		item1 = i1;
		item2 = i2;
	}
	
	
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

	public static void main(String[] args) {
		Item i1  = new Item('a', 10);
		Item i2 = new Item('b', 15);
		Item i3 = new Item('c', 10);
		Item i4 = new Item('d', 15);
		
		Bag b1 = new Bag('A', 50);
		Bag b2 = new Bag('B', 20);
		
		ArrayList<Bag> bags = new ArrayList<Bag>();
		bags.add(b1);
		
		EqualBinary EB = new EqualBinary(i1, i2);
		
		Assignment a1 = new Assignment(b1, i1);
		Assignment a2 = new Assignment(b2, i2);
		Assignment a3 = new Assignment(b1, i2);
		Assignment a4 = new Assignment(b2, i1);
		Assignment a5 = new Assignment(b1, i4);
		
		ArrayList<Assignment> ass = new ArrayList<Assignment>();
		ass.add(a1);
		ass.add(a2);
		
		ArrayList<Assignment> ass2 = new ArrayList<Assignment>();
		ass2.add(a2);
		
		ArrayList<Assignment> ass3 = new ArrayList<Assignment>();
		ass3.add(a2);
		ass3.add(a1);
		
		ArrayList<Assignment> ass4 = new ArrayList<Assignment>();
		ass.add(a5);
		ass.add(a1);
		
		//Should be false
		System.out.println(EB.isValid(ass));
		
		//Should be true
		System.out.println(EB.isValid(ass2));
		
		//Should be false
		System.out.println(EB.isValid(ass3));
		
		//Should be true
		System.out.println(EB.isValid(ass4));
	}
	
	
}
