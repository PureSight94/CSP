/**
 * 
 */
package CSP;

import java.util.ArrayList;

/**
 * @author COSNUEMER
 *
 */
public class InclusiveUnary implements IConstraint {

	private Item item;
	private ArrayList<Bag> allowedBags;
	
	public InclusiveUnary(Item i, ArrayList<Bag> bags) {
		this.item = i;
		this.allowedBags = bags;
	}
	
	@Override
	public boolean isValid(ArrayList<Assignment> assignments) {
		for(Assignment a : assignments) {
			if(a.getItem().equals(item)) {
				return allowedBags.contains(a.getBag());
			}
		}
		
		return true;
	}
	
	public static void main (String[] args) {
		Item i = new Item('A', 2);
		Bag good = new Bag('a', 4);
		Bag bad = new Bag('b', 5);
		ArrayList<Bag> allowedBags = new ArrayList<Bag>();
		allowedBags.add(good);
		
		InclusiveUnary iU = new InclusiveUnary(i, allowedBags);
		Assignment a = new Assignment(good, i);
		Assignment b = new Assignment(bad, i);
		ArrayList<Assignment> aList = new ArrayList<Assignment>();
		ArrayList<Assignment> bList = new ArrayList<Assignment>();
		aList.add(a);
		bList.add(b);
		
		System.out.println("Bag: good\n" + iU.isValid(aList));
		System.out.println("Bag: bad\n" + iU.isValid(bList));
	}
}
