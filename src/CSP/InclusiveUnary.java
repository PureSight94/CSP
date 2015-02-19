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
	
	/*
	 * Check this constraint and returns false only when the specified item
	 * is assigned and not in the specified bag.
	 */
	public boolean isValid(ArrayList<Assignment> assignments) {
		for(Assignment a : assignments) {
			if(a.getItem().equals(item)) {
				return allowedBags.contains(a.getBag());
			}
		}
		
		return true;
	}
}
