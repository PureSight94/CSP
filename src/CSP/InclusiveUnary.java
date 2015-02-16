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

	private Item item;				//Item that has the constraint
	private ArrayList<Bag> bags;	//List of Bags that 
	
	public InclusiveUnary(Item i, ArrayList<Bag> bags) {
		this.item = i;
		this.bags = bags;
	}
	
	/*
	 * So, I was thinking
	 * We are going to want each isValid to be true at the start of the assignment
	 * It's not enough to see if they simply violate a constraint
	 * For example: The Inclusive Unary: A x y is violated is A is not in x or y. Do we return true if it isnt assigned yet
	 * Or do we only return false if it has been assigned to a bag other than x or y?
	 */
	
	//Checks each bag in the constraint list of bags
	//Checks each item within each bag.
	//If the item specified in the constraint is in one of the bags, isValid returns true
	//If the item specified in the constraint is not in any of the bags, isValid returns false
	public boolean isValid() {
		if(item.getBag() == null)
			return true;
		for(Bag b: bags) {
			for(Item i: b.getItems()) {
				if(i.getName() == item.getName()) {
					return true;
				}
			}
		}
		return false;
	}
	
}
