import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;

public class Order extends Customer {

	public Order(String name, String number, String address, String location, String email) {
			super(name, number, address, location, email);
		}

//	Attributes
	static ArrayList<ArrayList<String>> orderItems = new ArrayList<ArrayList<String>>();
	float orderPrice;
	long orderNumber;
	
	void attributesOrder() {
		System.out.println("Name: " + customerName);
		System.out.println("Contact number: " + customerContactNumber);
		System.out.println("Address: " + customerAddress);
		System.out.println("Location: " + customerLocation);
		System.out.println("Email: " + customerEmail);
		System.out.println("Order number: " + orderNumber);
		System.out.println("Order items: " + orderItems);
		System.out.println("Order price: " + orderPrice);
	}
	
	static String closestDriver(String customerLocation) throws IOException {
		
		File file = new File("drivers.txt");
		Scanner sc = new Scanner(file);
		
		int i = 0;
		int smallNum = 100;
		int num;
		String driver = null;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String tempArray[] = line.split(",");
			String numToString = tempArray[2].substring(1);
			num = Integer.parseInt(numToString);
			String restrauntLocation = tempArray[1].substring(1);
			if (num<smallNum && customerLocation.equals(restrauntLocation)) {
				smallNum = num;
				driver = tempArray[0];
				break;
			}
			i++;
		}
		
//		increase deliveries in drivers file
		ArrayList<String> fileLines = new ArrayList<String>();
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			fileLines.add(scanner.nextLine());
		}
		
		String driverLine = fileLines.get(i);
		String[] tempListDriver = driverLine.split(", ");
		int numberOfDeliveries = Integer.parseInt(tempListDriver[2]) + 1;
		
		fileLines.set(i, tempListDriver[0] + ", " + tempListDriver[1] + ", " + numberOfDeliveries);
		
		PrintWriter pr = new PrintWriter("drivers.txt");
		
		for (int j=0; j<fileLines.size(); j++) {
			pr.println(fileLines.get(j));
		}
		pr.close();
		
		return driver;
	
	}
	
	static void newOrder(String customerLocation) throws IOException {
		
		long orderNumber = System.currentTimeMillis();
		
		try {
			FileInputStream file = new FileInputStream("restraunts.txt");
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
				System.out.println(sc.nextLine());
			}
			sc.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
		System.out.println();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Select a restraunt number from the list above: ");
		String selectedRestraunt = sc.nextLine();
		
		String restrauntLine = Files.readAllLines(Paths.get("restraunts.txt")).get(Integer.parseInt(selectedRestraunt));
		String restrauntLineArray[] = restrauntLine.split(",");
		String restrauntContactNumber = restrauntLineArray[3];
		
		boolean running = true;
		    while (running) {
		        System.out.println("Enter an item to order:");
		        String name = sc.nextLine();
		        
		        System.out.println("Enter the price:");
		        String price = sc.nextLine();
		        
		        System.out.println("Enter the quantity:");
		        String quantity = sc.nextLine();
		        
		        ArrayList<String> tempList = new ArrayList<String>();
		        tempList.add(name);
		        tempList.add(price);
		        tempList.add(quantity);
		        orderItems.add(tempList);
		        
		        System.out.println("Would you like to add another item?: (y/n)");
		        if (!sc.nextLine().equals("y")) {
		            running = false;
		        }
		    }
		System.out.println("Do you have any special requirments?");
		String specialRequirments = "Special Requirments: " + sc.nextLine();
		
		String orderCustomerDetailsOutput = "Order number: " + orderNumber + "\n" + "Customer: " + customerName + "\n" + "Email: " + customerEmail + "\n" + "Phone number: " + customerContactNumber + "\n" + "Location: " + customerLocation;
		
		String orderDetails = "You have ordered the following: \n";
		for (int i=0; i<orderItems.size(); i++) {
			ArrayList<String> tempArrayList = new ArrayList<String>();
			tempArrayList.addAll(orderItems.get(i));
			System.out.println(orderItems);
			orderDetails += tempArrayList.get(2) + " x " + tempArrayList.get(0) + " (R" + tempArrayList.get(1) + ") \n";
		}
		
		double total = 0;
		for (int i=0; i<orderItems.size(); i++) {
			ArrayList<String> tempPrice = orderItems.get(i);
			total += Double.parseDouble(tempPrice.get(1));
		}
		
		String closestDriver = "The closest driver to you is: " + closestDriver(customerLocation);
		
		String addressOutput = customerAddress;
		
		String restrauntOutput = "If you need the contact the restraunt, their number is " + restrauntContactNumber;
		
		String orderOutput = orderCustomerDetailsOutput + "\n" + orderDetails + "\n" + specialRequirments + "\n \n" + "Total: " + "R" + total + "\n \n" + closestDriver + "\n" + addressOutput + "\n" + restrauntOutput;
		System.out.println(orderOutput);
		
		try {
			FileWriter file = new FileWriter("order.txt");
			file.write(orderOutput);
			file.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
		
//		writing customer text file
		try {
			FileWriter file = new FileWriter("customer.txt", true);
			file.write(Customer.customerName + ", " + orderNumber + "\n");
			file.close();
		} catch (Exception e) {
			System.out.println("Error");
		}
//		sorting text file alphabetically 
		try {
			File file = new File("customer.txt");
			Scanner scanner = new Scanner(file);
			
//			writing all lines to a list so that it can be put back into the file easily
			ArrayList<String> lineList = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				lineList.add(scanner.nextLine());
			}
			
//			writing the list into the file after clearing the file
			PrintWriter pr = new PrintWriter("customer.txt");
			Collections.sort(lineList);
			for (int i=0; i<lineList.size(); i++) {
				pr.println(lineList.get(i));
			}	
			pr.close();
		} catch (Exception e) {
			System.out.println("Error: customer.txt, sorting");
		}
		
//		writing customer text file grouped by location
		try {
			FileWriter file = new FileWriter("customerByLocation.txt", true);
			file.write(Customer.customerLocation + ',' + Customer.customerName + "\n");
			file.close();
		} catch (Exception e) {
			System.out.println("Error: customerByLocation.txt");
		}
		
		try {
			File file = new File("customerByLocation.txt");
			Scanner scanner = new Scanner(file);
			
//			putting list back into file after clearing file
			ArrayList<String> lineList = new ArrayList<String>();
			while (scanner.hasNextLine()) {
				lineList.add(scanner.nextLine());
			}
			Collections.sort(lineList);
			
			PrintWriter pr = new PrintWriter("customerByLocation.txt");
			for (int i=0; i<lineList.size(); i++) {
				pr.println(lineList.get(i));
			}
			pr.close();
			
		} catch (Exception e) {
			System.out.println("Error: customerByLocation");
		}
		
		

	}
}