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
	
	//Terribly unoptimized and inefficient
	//Checks Each Bag in the constraints bag list and sees if any one of these bags contains the item that the constraint doesn't allow us to have
	//If a bag has one such item, then isValid returns false
	//If no bag has such an item, then isValid returns true
	public boolean isValid() {
		for(Bag b: bags) {
			for(Item i: b.getItems() ) {
				if(i.getName() == item.getName()) 
					return false;
			}
		}
		return true;
	}

}
