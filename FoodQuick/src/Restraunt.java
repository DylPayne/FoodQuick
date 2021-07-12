import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Restraunt {

//	Attributes
	static String restrauntName;
	static String restrauntLocation;
	static String restrauntContactNumber;
	
//	Method to print attributes
	void attributes() {
		
		System.out.println("Restraunt name: " + restrauntName);
		System.out.println("Restraunt location: " + restrauntLocation);
		System.out.println("Restraunt contact number: " + restrauntContactNumber);
		
	}
	
	static int newRestraunt() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the restraunt name: ");
		String restrauntNameInput = sc.nextLine();	
		
		System.out.println("Enter the restraunt location: ");
		String restrauntLocationInput = sc.nextLine();
		
		System.out.println("Enter the restraunt contact number: ");
		String restrauntContactNumberInput = sc.nextLine();
		

		BufferedReader reader = new BufferedReader(new FileReader("restraunts.txt"));
		int count = 0;
		while (reader.readLine() != null) {
			count = count + 1;
		}
		reader.close();

		String txtFileString = "\n" + count + "," + restrauntNameInput + "," + restrauntLocationInput + "," + restrauntContactNumberInput;
		
		try {
			FileWriter file = new FileWriter("restraunts.txt", true);
			file.write(txtFileString);
			file.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		return count;
	}
	
	static Hashtable<String, List<String>> createDictionary() throws FileNotFoundException {
		
		Dictionary<String, List<String>> restraunts = new Hashtable<String, List<String>>();
		
		File file = new File("restraunts.txt");
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String tempArray[] = sc.nextLine().split(",");
			List<String> restrauntList = new ArrayList<String>();
			restrauntList = (List<String>) Arrays.asList(tempArray);
			restraunts.put(tempArray[0], restrauntList);
		}
		
		System.out.println(restraunts);
		
		return (Hashtable<String, List<String>>) restraunts;
		
	}
	
	static void locations(int len) {
		
		try {
			File file = new File("restraunts.txt");
			Scanner sc = new Scanner(file);
			int i = 0;
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
				i ++;
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
	static String getNumber() {
		return restrauntContactNumber;
	}
	
}