package CSP;

//The way we have our class structure set up, we may not need an assignment variable
//We are just updating the actual items and Bags as they violate or do not violate constraints
//Therefore I dont think we need a seperate variable that would hold the current "correct" value pairs...

public class BackTracking {
	
	public void backTracking() {
	//Check to see if current Item/Bag pairs is complete
	//Select first variable (item) in the variable list
	//For each value this variable (bag) can have, do the following
			//In our case, add value, check against Constraints
				//If violates, remove 
				//Else, call BackTrack again (I think we have to remove the Item from the list.
		
	//If exit for loop, return failure
		
	}
	
}
