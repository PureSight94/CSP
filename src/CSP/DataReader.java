package CSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DataReader {

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
					allBags.add(new Bag(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
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
					for(Bag b : allBags) {
						i.addPossibleLocation(b);
					}
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

	public ArrayList<Bag> getBagsFromLine(String splitLine[]) {
		ArrayList<Bag> bags = new ArrayList<Bag>();
		for(int j = 1; j < splitLine.length; j++) {
			Bag b = getBagByName(splitLine[j].charAt(0));
			bags.add(b);
		}
		return bags;
	}

	//Gets the given bag from the bag list by same name
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
	
	public boolean noDoubles(ArrayList<Assignment> assignments) {
		Set<Item> itemsAssigned = new HashSet<Item>();
		for(Assignment a : assignments) {
			itemsAssigned.add(a.getItem());
		}
		
		return (itemsAssigned.size() == assignments.size());
	}

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
	
	public boolean overMinLimit() {
		for(Bag b: allBags) {
			if(b.getItemCount() < fitLimitMin || b.getCurrentWeight() < 0.9 * b.getWeightCapacity())
				return false;
		}
		return true;
	}

	public boolean underMaxLimit() {
		for(Bag b: allBags) {
			if(b.getItemCount() > fitLimitMax || b.getCurrentWeight() > b.getWeightCapacity()) {
				return false;
			}
		}
		return true;
	}

	public ArrayList<Bag> cloneBags() {
		ArrayList<Bag> cloneBags = new ArrayList<Bag>();
		for(Bag b: allBags) {
			cloneBags.add(b);
		}
		return cloneBags;
	}
	
	public ArrayList<Item> cloneItems() {
		ArrayList<Item> cloneList = new ArrayList<Item>();
		for(Item i: allItems) {
			cloneList.add(i);
		}
		return cloneList;
	}

	public boolean isComplete(ArrayList<Assignment> assignments) {
		boolean isComp = (checkValidity(assignments) && overMinLimit() && selectUnassignedItem(assignments) == null);
		return isComp;
	}

	//Order the list of Bags in order of ones that rule out the fewest choices for neighboring variables
	//The bag that leaves the most open space left
	public ArrayList<Bag> leastConstrainingValue() {
		ArrayList<Bag> possibleBags = cloneBags();
		Collections.sort(possibleBags);
		return possibleBags;
	}

	/*
	public Item minimumRemainingValues() {
		
	}
	*/
	
	public ArrayList<Assignment> backTrackRunner() {		
		ArrayList<Assignment> results = backTrack(new ArrayList<Assignment>());

		if(results == null)
			return new ArrayList<Assignment>();
		return results;
	}
	
	public ArrayList<Assignment> backTrack(ArrayList<Assignment> assignments) {	
		Item i = selectUnassignedItem(assignments);
		if(i == null)
			return new ArrayList<Assignment>();
		//ArrayList<Bag> orderedBags = leastConstrainingValue();
		for(Bag b: allBags) {
			Assignment a = new Assignment(b, i);
			assignments.add(a);
			a.getBag().incrementWeight(a.getItem().getWeight());
			a.getBag().incrementCount();
			if(checkValidity(assignments)) {
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

	public String printAssignments(ArrayList<Assignment> assignments) {
		String output = "";

		if(assignments.isEmpty())
			return "There is no such assingment.";

		for(Bag b : allBags) {
			output += "\n\n" + b.getName() + " ";
			for(Assignment a : assignments) {
				if(a.getBag().getName() == b.getName()) {
					output += a.getItem().getName() + " ";
				}
			}
			
			output += "\nNumber of items: " + b.getItemCount() + "\n"
					+ "Weight: " + b.getCurrentWeight() + "/" + b.getWeightCapacity() +  "\nWasted capacity: " + (b.getWeightCapacity() - b.getCurrentWeight());
		}

		return output;
	}

	/*
	 * Entry point of program.
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Incorrect number of command line arguments.");
			System.exit(-1);
		}

		inputFile = args[0];

		DataReader dReader = new DataReader();
		dReader.readData();
		dReader.printData();
		System.out.println(dReader.printAssignments(dReader.backTrackRunner()));
	}
}
