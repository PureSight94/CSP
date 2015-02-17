/**
 * 
 */
package CSP;

import java.util.ArrayList;

/**
 * @author COSNUEMER
 *
 */
public interface IConstraint {
	public boolean isValid(ArrayList<Assignment> assignments);
}
