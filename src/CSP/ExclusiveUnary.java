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
		// TODO Auto-generated method stub
		return false;
	}

}
