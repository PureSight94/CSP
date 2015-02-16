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
	private ArrayList<Bag> bags;
	
	public InclusiveUnary(Item i, ArrayList<Bag> bags) {
		this.item = i;
		this.bags = bags;
	}
	
	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
