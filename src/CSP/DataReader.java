package CSP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataReader {
	
	private static String inputFile;
	private ArrayList<Item> items;
	private ArrayList<Bag> bags;
	private static int fitLimitMin;
	private static int fitLimitMax;
	
	/*
	 * Constructor.
	 */
	public DataReader () {
		items = new ArrayList<Item>();
		bags = new ArrayList<Bag>();
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
					items.add(new Item(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
				}
				else if(typeNum == 2) {
					// Bags
					bags.add(new Bag(splitLine[0].charAt(0), Integer.parseInt(splitLine[1])));
				}
				else if(typeNum == 3) {
					// Fit limit values
					fitLimitMin = Integer.parseInt(splitLine[0]);
					fitLimitMax = Integer.parseInt(splitLine[1]);
				}
				else if(typeNum == 4) {
					// Unary inclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> bags = new ArrayList<Bag>();
					bags = getBagsFromLine(splitLine);
					InclusiveUnary IU = new InclusiveUnary(i, bags);
					constraintList.add(IU);
				}
				else if(typeNum == 5) {
					// Unary exclusive
					Item i = getItemByName(splitLine[0].charAt(0));
					ArrayList<Bag> bags = getBagsFromLine(splitLine);
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
				System.out.println("There is no problem specified!");
				System.exit(-1);
			}
			
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
		for(Bag b: bags) {
			if(b.getName() == bagCheck)
				return b;
		}
		return null;
	}
	
	public Item getItemByName(char itemCheck) {
		for(Item i: items) {
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
		for(Item i : items) {
			System.out.println("\t" + i.getName() + " " + i.getWeight());
		}
		
		System.out.println("Bag: ");
		for(Bag b : bags) {
			System.out.println("\t" + b.getName() + " " + b.getWeightCapacity());
		}
		
		System.out.println("Min: " + fitLimitMin);
		System.out.println("Max: " + fitLimitMax);
	}

	public static int getFitLimitMin() {
		return fitLimitMin;
	}
	
	public static int getFitLimitMax() {
		return fitLimitMax;
	}
	
	
	//MUST ADD A CHECK FOR OVER 100 PERCENT AND OVER MAX
	public boolean checkValidity(ArrayList<Assignment> assignments) {
		for(IConstraint c: constraintList) {
			if(!c.isValid(assignments))
				return false;
		}
		return true;
	}
	
	
	
	public ArrayList<Item> cloneItems() {
		ArrayList<Item> cloneList = new ArrayList<Item>();
		for(Item i: items) {
			cloneList.add(i);
		}
		return cloneList;
	}
	
	public ArrayList<Assignment> backTrackRunner() {
		return backTrack(new ArrayList<Assignment>());
	}
	
	public boolean isComplete(ArrayList<Assignment> assignments) {
		if(selectUnassignedItem(assignments) != null)
			System.out.println("next item: " + selectUnassignedItem(assignments).getName());
		else
			System.out.println("next item: " + selectUnassignedItem(assignments));
		System.out.println("checkValidity: " + checkValidity(assignments));
		return (checkValidity(assignments) && selectUnassignedItem(assignments) == null);
	}
	
	public ArrayList<Assignment> backTrack(ArrayList<Assignment> assignments) {
		Item i = selectUnassignedItem(assignments);
		if(i == null)
			return new ArrayList<Assignment>();
		for(Bag b: bags) {
			Assignment a = new Assignment(b, i);
			assignments.add(a);
			if(checkValidity(assignments)) {
				backTrack(assignments);
				System.out.println("Valid Assignment");
			}
			else 
				assignments.remove(a);
		}
		if(isComplete(assignments))
			return assignments;
		else
			return new ArrayList<Assignment>();
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
			return "NO ASSIGNMENTS";
		for(Assignment a : assignments) {
			output += a.getItem().getName() + " -> " + a.getBag().getName() + "\n";
		}
		
		return output;
	}
	
	/*
	 * Entry point of program.
	 */
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Incorrect number of command line arguments");
			System.exit(-1);
		}

		inputFile = args[0];
		
		DataReader dReader = new DataReader();
		dReader.readData();
		dReader.printData();
		System.out.println(dReader.printAssignments(dReader.backTrackRunner()));
	}
}
