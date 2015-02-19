/**
 * 
 */
package CSP;

import java.util.ArrayList;

/*
 * Each Constraint class needs to implement an isValid method that
 * searches through assigned variable value pairs and returns a boolean
 * indicating whether or not the constraint is satisfied
 */
public interface IConstraint {
	public boolean isValid(ArrayList<Assignment> assignments);
}
