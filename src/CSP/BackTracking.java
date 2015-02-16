package CSP;

import java.util.ArrayList;

//The way we have our class structure set up, we may not need an assignment variable
//We are just updating the actual items and Bags as they violate or do not violate constraints
//Therefore I dont think we need a seperate variable that would hold the current "correct" value pairs...

public class BackTracking {
	
	static int count = 0;
	DataReader dr = new DataReader();
	ArrayList<Item> items = dr.getItem();
	ArrayList<Bag> bags = dr.getBag();
	
	public boolean backTracking(int count) {
	//Check to see if current Item/Bag pairs is complete. This is just calling the isComplete() method. I am having trouble calling it htough
	//Select first variable (item) in the variable list aka items.get(0). Probably just do a for loop that goes through each item in the ArrayList for now
			//Later we can change it. Mainly when we do the MRV stuff to select a specific item to try.
	//For each value this variable (bag) can have, do the following
			//In our case, add value, check against Constraints
				//If violates, remove 
				//Else, call BackTrack again (I think we have to remove the Item from the list.
		
	//If exit for loop, return failure
		
		if(dr.isComplete())
			return true;
		return false;
	}
}