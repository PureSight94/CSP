import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataReader {
	
	private static String inputFile;
	private static int numBags = 0;
	
	public static void readData() {
		BufferedReader br;
		int numLines = 0;
		try{
			br = new BufferedReader(new FileReader(inputFile));
			String thisLine = null;
			int typeNum = 0;
			
			while( (thisLine = br.readLine()) != null) {
				System.out.println(thisLine);
				numLines++;

				if(thisLine.charAt(0) == '#') {
					typeNum++;
				}
				else if(typeNum == 1) {
					numBags++;
				}
				else if(typeNum == 2) {
					System.out.println("Values");
				}
				else if(typeNum == 3) {
					System.out.println("Fitting Limits");
				}
				else if(typeNum == 4) {
					System.out.println("unary inclusive");
				}
				else if(typeNum == 5) {
					System.out.println("unary exclusive");
				}
				else if(typeNum == 6) {
					System.out.println("Binary Equals");
				}
				else if(typeNum == 7) {
					System.out.println("Binary Not Equals");
				}
				else if(typeNum == 8) {
					System.out.println("Mutual Exclusive");
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
	
	
public static void main(String[] args) {
	if(args.length != 1) {
		System.out.println("Incorrect number of command line arguments");
		System.exit(-1);
	}
	
	inputFile = args[0];
	System.out.println(inputFile);
	readData();
	System.out.println(numBags);
}

	
}
