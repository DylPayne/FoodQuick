import java.io.FileWriter;
import java.util.Scanner;

public class Customer {

//	Attributes
	static String customerName;
	static String customerContactNumber;
	static String customerAddress;
	static String customerLocation;
	static String customerEmail;
	
	public Customer(String name, String number, String address, String location, String email) {
		customerName = name;
		customerContactNumber = number;
		customerAddress = address;
		customerLocation = location;
		customerEmail = email;
	}
	
//	Method to print customer details
	static void attributes() {
		System.out.println("Name: " + customerName);
		System.out.println("Contact number: " + customerContactNumber);
		System.out.println("Address: " + customerAddress);
		System.out.println("Location: " + customerLocation);
		System.out.println("Email: " + customerEmail);
	}
	
//	Method to create new customer
	static String newCustomer() {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String name = sc.nextLine();

		System.out.println("Enter your contact number: ");
		String contactNumber = sc.nextLine();
		
		System.out.println("Enter your address: ");
		String address = sc.nextLine();
		
		System.out.println("Enter your location: ");
		String location = sc.nextLine();
		
		System.out.println("Enter your email: ");
		String email = sc.nextLine();
		
		customerName = name;
		customerContactNumber = contactNumber;
		customerAddress = address;
		customerLocation = location;
		customerEmail = email;
		
		System.out.println();
		
		
		
		return customerLocation;		
		
	}
	
}