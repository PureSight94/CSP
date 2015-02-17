/**
 * 
 */
package CSP;

import java.util.ArrayList;

/**
 * @author COSNUEMER
 *
 */
public class ExclusiveUnary implements IConstraint {

	private Item item;
	private ArrayList<Bag> bags;
	
	public ExclusiveUnary(Item i, ArrayList<Bag> bags) {
		this.item = i;
		this.bags = bags;
	}
	
	@Override
	public boolean isValid(ArrayList<Assignment> assignments) {
			for(Assignment a : assignments) {
				if(a.getItem().equals(item)) {
					return !(bags.contains(a.getBag()));
				}
			}
			return true;
		}
	
	
	public static void main(String[] args) {
		Item i1  = new Item('a', 10);
		Item i2 = new Item('b', 15);
		Bag b1 = new Bag('A', 50);
		Bag b2 = new Bag('B', 20);
		
		ArrayList<Bag> bags = new ArrayList<Bag>();
		bags.add(b1);
		
		ExclusiveUnary EU = new ExclusiveUnary(i1, bags);
		
		Assignment a1 = new Assignment(b1, i1);
		Assignment a2 = new Assignment(b2, i2);
		
		ArrayList<Assignment> ass = new ArrayList<Assignment>();
		
		ass.add(a1);
		ass.add(a2);
		
		ArrayList<Assignment> ass2 = new ArrayList<Assignment>();
		ass2.add(a2);
		
		//Should be false
		System.out.println(EU.isValid(ass));
		
		//Should be true
		System.out.println(EU.isValid(ass2));
	}

}


