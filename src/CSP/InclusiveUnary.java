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
	
	//Checks each bag in the constraint list of bags
	//Checks each item within each bag.
	//If the item specified in the constraint is in one of the bags, isValid returns true
	//If the item specified in the constraint is not in any of the bags, isValid returns false
	public boolean isValid() {
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
