package CSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class DataReader {
	public static final int BT = 1;
	public static final int BTWithHeuristics = 2;
	public static final int FCWithHeuristics = 3;
	
	private static String inputFile;
	private ArrayList<Item> allItems;
	private ArrayList<Bag> allBags;
	private int fitLimitMin;
	private int fitLimitMax;

	/*
	 * Constructor.
	 */
	public DataReader () {
		allItems = new ArrayList<Item>();
		allBags = new ArrayList<Bag>();
	}

	ArrayList<IConstraint> constraintList = new ArrayList<IConstraint>();
	
	/*
	 * Process and parse input file.
	 * Creates all Item and Bag objects from the input file.
	 * Parses min and max fit limits for the bags.
	 * Creates a list of IConstraints from the information from the input file.
	 */
	public void readData() {
		BufferedReader br;
		int numLines = 0;

		try{
			br = new BufferedReader(new FileReader(inputFile));
			String thisLine = null;
			int typeNum = 0;

			while( (thisLine = br.readLine()) != null) {
				numLines++;
				String splitLine[] = thisLine.split(" ");

				if(thisLine.charAt(0) == '#') {
					typeNum++;
				}
				else if(typeNum == 1) {
					// Items
					allItems.add(new Item(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
				}
				else if(typeNum == 2) {
					// Bags
					Bag b = new Bag(splitLine[0].charAt(0), Integer.parseInt(splitLine[1]));
					allBags.add(b);
					for(Item i : allItems) {
						i.addPossibleLocation(b);
					}
				}
				else if(typeNum == 3) {
					// Fit limit values
					fitLimitMin = Integer.parseInt(splitLine[0]);
					fitLimitMax = Integer.parseInt(splitLine[1]);
				}
				else if(typeNum == 4) {
					// Unary inclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> bags = getBagsFromLine(splitLine);
					i.clearPossibleLocations();
					for(Bag b : bags) {
						i.addPossibleLocation(b);
					}
					InclusiveUnary IU = new InclusiveUnary(i, bags);
					constraintList.add(IU);
				}
				else if(typeNum == 5) {
					// Unary exclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> bags = getBagsFromLine(splitLine);
					for(Bag b : bags) {
						i.removePossibleLocation(b);
					}
					ExclusiveUnary EU = new ExclusiveUnary(i, bags);
					constraintList.add(EU);
				}
				else if(typeNum == 6) {
					// Binary Equals
					Item i1 = getItemByName(splitLine[0].charAt(0));
					Item i2 = getItemByName(splitLine[1].charAt(0));
					EqualBinary EB = new EqualBinary(i1, i2);
					constraintList.add(EB);
				}
				else if(typeNum == 7) {
					// Binary Not Equals
					Item i1 = getItemByName(splitLine[0].charAt(0));
					Item i2 = getItemByName(splitLine[1].charAt(0));
					NotEqualBinary NEB = new NotEqualBinary(i1, i2);
					constraintList.add(NEB);
				}
				else if(typeNum == 8) {
					// Mutual Exclusive
					Item i1 = getItemByName(splitLine[0].charAt(0));
					Item i2 = getItemByName(splitLine[1].charAt(0));
					Bag b1 = getBagByName(splitLine[2].charAt(0));
					Bag b2 = getBagByName(splitLine[3].charAt(0));
					MutualExclusiveBinary MEB = new MutualExclusiveBinary(i1, i2, b1, b2);
					constraintList.add(MEB);
				}
			}

			if(numLines == 8) {
				System.out.println("No such assignment is possible.");
				System.exit(-1);
			}
			
			if(fitLimitMax == 0)
				fitLimitMax = allItems.size() + 1;
			
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Return a list of bags equal to the given names.
	 */
	public ArrayList<Bag> getBagsFromLine(String splitLine[]) {
		ArrayList<Bag> bags = new ArrayList<Bag>();
		for(int j = 1; j < splitLine.length; j++) {
			Bag b = getBagByName(splitLine[j].charAt(0));
			bags.add(b);
		}
		return bags;
	}

	/*
	 * Gets the given bag from the bag list of the given name.
	 */
	public Bag getBagByName(char bagCheck) {
		for(Bag b: allBags) {
			if(b.getName() == bagCheck)
				return b;
		}
		return null;
	}

	public Item getItemByName(char itemCheck) {
		for(Item i: allItems) {
			if(i.getName() == itemCheck)
				return i;
		}
		return null;
	}

	/*
	 * Print function for debug purposes.
	 * Simply print data received from file.
	 */
	public void printData () {
		System.out.println("Item: ");
		for(Item i : allItems) {
			System.out.println("\t" + i.getName() + " " + i.getWeight());
		}

		System.out.println("Bag: ");
		for(Bag b : allBags) {
			System.out.println("\t" + b.getName() + " " + b.getWeightCapacity());
		}

		System.out.println("Min: " + fitLimitMin);
		System.out.println("Max: " + fitLimitMax);
	}
	
	/*
	 * Make sure that no item is in two bags at one time.
	 * Returns True if valid, False otherwise.
	 */
	public boolean noDoubles(ArrayList<Assignment> assignments) {
		Set<Item> itemsAssigned = new HashSet<Item>();
		for(Assignment a : assignments) {
			itemsAssigned.add(a.getItem());
		}
		
		return (itemsAssigned.size() == assignments.size());
	}

	/*
	 * Check if a set of assignments is valid against all constraints.
	 * Also checks if all bag's weights are below the maximum limit.
	 * Makes sure no item is in two bags at once.
	 */
	public boolean checkValidity(ArrayList<Assignment> assignments) {
		if(!underMaxLimit())
			return false;
		for(IConstraint c: constraintList) {
			if(!c.isValid(assignments))
				return false;
		}
		if(!noDoubles(assignments))
			return false;
		return true;
	}
	
	/*
	 * Check if all bag's weights over the minimum limit.
	 * Also check if all bag's weight is at least 90% of their capacity.
	 * Return True if valid, False otherwise.
	 */
	public boolean overMinLimit() {
		for(Bag b: allBags) {
			if(b.getItemCount() < fitLimitMin || b.getCurrentWeight() < 0.9 * b.getWeightCapacity())
				return false;
		}
		return true;
	}
	
	/*
	 * Check if all bag's weights are under the maximum limit.
	 * Also check if all bags don't have more than the max number of items in each.
	 * Return True if valid, False otherwise.
	 */
	public boolean underMaxLimit() {
		for(Bag b: allBags) {
			if(b.getItemCount() > fitLimitMax || b.getCurrentWeight() > b.getWeightCapacity()) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Returns a copy of the given list of bags.
	 */
	public ArrayList<Bag> cloneBags(ArrayList<Bag> bagListToClone) {
		ArrayList<Bag> cloneBags = new ArrayList<Bag>();
		for(Bag b: bagListToClone) {
			cloneBags.add(b);
		}
		return cloneBags;
	}
	
	/*
	 * Returns a copy of the list of all the items.
	 */
	public ArrayList<Item> cloneItems() {
		ArrayList<Item> cloneList = new ArrayList<Item>();
		for(Item i: allItems) {
			cloneList.add(i);
		}
		return cloneList;
	}
	
	/*
	 * Check to see if a list of assignments meets the requirements.
	 * Returns True if the list of assignments is a valid solution,
	 * False otherwise.
	 */
	public boolean isComplete(ArrayList<Assignment> assignments) {
		boolean isValid = checkValidity(assignments);
		boolean isOverMinLimit = overMinLimit(); 
		boolean noUnassignedItems = (selectUnassignedItem(assignments) == null);
		
		return isValid && isOverMinLimit && noUnassignedItems;
	}

	/*
	 * Takes in a list of bags and returns a new list of bags
	 * in order of most open space to least open space.
	 */
	public ArrayList<Bag> leastConstrainingValue(ArrayList<Bag> bagList) {
		ArrayList<Bag> orderedBags = cloneBags(bagList);
		Collections.sort(orderedBags);
		
		return orderedBags;
	}

	/*
	 * Returns the unassigned item with the least number of valid possible bags.
	 * If there is a tie, it returns the item with the largest weight.
	 */
	public Item minimumRemainingValues(ArrayList<Assignment> assignments) {
		ArrayList<Item> totalItems = cloneItems();
		for(Assignment a: assignments) {
			totalItems.remove(a.getItem());
		}
		Collections.sort(totalItems);
		if(totalItems.isEmpty())
			return null;
		return totalItems.get(0);
	}
	
	/*
	 * Clear all information from each bag.
	 * Allows you to run the program multiple times with the same data.
	 */
	public void reset() {
		for(Bag b: allBags) {
			b.clear();
		}
	}
	
	/*
	 * The backtrack function. This dispatches the correct version of the CSP
	 * depending on if it is with the heuristics or forward checking.
	 * Returns a list of assignments if a solution is found, and
	 * returns an empty list if not found.
	 */
	public ArrayList<Assignment> backTrackRunner(int mode) {
		ArrayList<Assignment> results;

		switch(mode) {
		case BT: 
			// Backtracking alone
			results = backTrack(new ArrayList<Assignment>());
			break;
		case BTWithHeuristics:
			// Backtracking with heuristics
			results = backTrackHeuristics(new ArrayList<Assignment>());
			break;
		case FCWithHeuristics:
			// Forward checking with heuristics
			results = backTrackFC(new ArrayList<Assignment>());
			break;
		default: 
			results = backTrack(new ArrayList<Assignment>());
			break;
		}
		
		if(results == null)
			return new ArrayList<Assignment>();
		
		return results;
	}
	
	/*
	 * Backtrack function without heuristics of forward checking.
	 */
	public ArrayList<Assignment> backTrack(ArrayList<Assignment> assignments) {	
		Item i = selectUnassignedItem(assignments);
		if(i == null)
			return new ArrayList<Assignment>();
		
		for(Bag b: allBags) {
			Assignment a = new Assignment(b, i);
			assignments.add(a);
			a.getBag().incrementWeight(a.getItem().getWeight());
			a.getBag().incrementCount();
			if(checkValidity(assignments)) {
				
				// Recursive call
				ArrayList<Assignment> results = backTrack(assignments);
				
				if(results == null) {
					a.getBag().decrementWeight(a.getItem().getWeight());
					a.getBag().decrementCount();
					assignments.remove(a);
				}
			}
			else {
				a.getBag().decrementWeight(a.getItem().getWeight());
				a.getBag().decrementCount();
				assignments.remove(a);
			}
		}
		
		if(isComplete(assignments))
			return assignments;
		
		return null;
	}

	/*
	 * Backtrack function with heuristics.
	 * Almost identical to previous function, but uses constraints when deciding
	 * next unassigned item, and the order of the bags.
	 */
	public ArrayList<Assignment> backTrackHeuristics(ArrayList<Assignment> assignments) {
		Item i = minimumRemainingValues(assignments);
		
		if(i == null)
			return new ArrayList<Assignment>();
		
		// Order the bags so least constraining bags are first.
		ArrayList<Bag> orderedBags = leastConstrainingValue(allBags);
		
		for(Bag b: orderedBags) {
			Assignment a = new Assignment(b, i);
			assignments.add(a);
			a.getBag().incrementWeight(a.getItem().getWeight());
			a.getBag().incrementCount();
			if(checkValidity(assignments)) {
				
				// Recursive call
				ArrayList<Assignment> results = backTrack(assignments);
				
				if(results == null) {
					a.getBag().decrementWeight(a.getItem().getWeight());
					a.getBag().decrementCount();
					assignments.remove(a);
				}
			}
			else {
				a.getBag().decrementWeight(a.getItem().getWeight());
				a.getBag().decrementCount();
				assignments.remove(a);
			}
		}
		
		if(isComplete(assignments))
			return assignments;
		
		return null;
	}
	
	/*
	 * Backtrack function with heuristics and forward checking.
	 * Almost identical to previous function, but uses uses forward checking
	 * to prune the list of bags to check.
	 */
	public ArrayList<Assignment> backTrackFC(ArrayList<Assignment> assignments) {
		Item i = minimumRemainingValues(assignments);
		
		if(i == null)
			return new ArrayList<Assignment>();
		
		// Prune bags that are not valid
		ArrayList<Bag> possibleBags = i.getPossibleBags();
		
		// Order bags so that the least constraining bags are first
		ArrayList<Bag> orderedBags = leastConstrainingValue(possibleBags);
		
		for(Bag b: orderedBags) {
			Assignment a = new Assignment(b, i);
			assignments.add(a);
			a.getBag().incrementWeight(a.getItem().getWeight());
			a.getBag().incrementCount();
			if(checkValidity(assignments)) {
				
				// Recursive call
				ArrayList<Assignment> results = backTrack(assignments);
				
				if(results == null) {
					a.getBag().decrementWeight(a.getItem().getWeight());
					a.getBag().decrementCount();
					assignments.remove(a);
				}
			}
			else {
				a.getBag().decrementWeight(a.getItem().getWeight());
				a.getBag().decrementCount();
				assignments.remove(a);
			}
		}
		
		if(isComplete(assignments))
			return assignments;
		
		return null;
	}
	
	/*
	 * Takes in a list of assignments and returns an unassigned item.
	 * This is the basic version of the function, and does not include
	 * the heuristics.
	 */
	public Item selectUnassignedItem(ArrayList<Assignment> assignments) {
		ArrayList<Item> totalItems = cloneItems();
		for(Assignment a: assignments) {
			totalItems.remove(a.getItem());
		}
		if(totalItems.size() == 0)
			return null;
		else
			return totalItems.get(0);
	}

	/*
	 * Takes in a list of Assignments, and returns a String representation
	 * of the assignments. This is used as the main output of our program.
	 * If there is no solution, "There is no such assignment."
	 * 
	 * An example of output:
	 * 
	 *      x A B C
	 *      Number of items: 3
	 *      Weight: 20/20
	 *      Wasted capacity: 0
	 *      
	 */
	public String printAssignments(ArrayList<Assignment> assignments) {
		String output = "";

		if(assignments.isEmpty())
			return "There is no such assignment.";

		for(Bag b : allBags) {
			output += "\n" + b.getName() + " ";
			for(Assignment a : assignments) {
				if(a.getBag().getName() == b.getName()) {
					output += a.getItem().getName() + " ";
				}
			}
			
			output += "\nNumber of items: " + b.getItemCount() + "\n"
					+ "Weight: " + b.getCurrentWeight() + "/" + b.getWeightCapacity() 
					+ "\nWasted capacity: " + (b.getWeightCapacity() - b.getCurrentWeight())
					+ "\n";
		}
		
		// Reset weights and item counts in each bag.
		reset();

		return output;
	}

	/*
	 * Entry point of program.
	 */
	public static void main(String[] args) {
		
		// Print an error if the arguments are not correct.
		if(args.length != 1) {
			System.out.println("Incorrect number of command line arguments.");
			System.exit(-1);
		}
		
		// The input file path name
		inputFile = args[0];

		// Read in the file, parse the file, and calculate the solution using Forward Checking
		DataReader dReader = new DataReader();
		dReader.readData();
		ArrayList<Assignment> solution = dReader.backTrackRunner(FCWithHeuristics);
		
		// Calculates and prints out the solution, formatted correctly.
		System.out.println(  "\n*****************************************");
		System.out.println(    "*          Assignment Solution          *");
		System.out.println(    "*****************************************\n");
		System.out.println(    dReader.printAssignments(solution));
		
		// Calculate average times it takes to run our program over the file.
		System.out.println("\n\n*****************************************");
		System.out.println(    "*   Running times (average of 5 runs)   *");
		System.out.println(    "*****************************************\n");
		
		long totalTime = 0;
		long start;
		long end;
		long averageTime = 0;
		String table = "";
		
		/*
		 *  Runs CSP algorithm with BackTracking ONLY.
		 *  Calculates the average time it took to run from 5 runs.
		 *  Print results to standard out.
		 */
		for(int i = 0; i < 5; i++) {
			start = System.nanoTime();
			dReader.backTrackRunner(BT);
			end = System.nanoTime();
			totalTime += end - start;
		}
		averageTime = totalTime/5;
		table += "BT: " + averageTime + " nsec\n";
		totalTime = 0;
		
		/*
		 * Runs CSP algorithm with BackTracking and heuristics (MRV and LCV).
		 */
		for(int i = 0; i < 5; i++) {
			start = System.nanoTime();
			dReader.backTrackRunner(BTWithHeuristics);
			end = System.nanoTime();
			totalTime += end - start;
		}
		averageTime = totalTime/5;
		table += "BT with heuristics: " + averageTime + " nsec\n";
		totalTime = 0;
		
		/*
		 * Runs CSP algorithm with ForwardChecking and heuristics (MRV and LCV).
		 */
		for(int i = 0; i < 5; i++) {
			start = System.nanoTime();
			dReader.backTrackRunner(FCWithHeuristics);
			end = System.nanoTime();
			totalTime += end - start;
		}
		averageTime = totalTime/5;
		table += "FC with heuristics: " + averageTime + " nsec\n";
		totalTime = 0;
		
		// Print table of times to standard out.
		System.out.println(table);
	}
}
