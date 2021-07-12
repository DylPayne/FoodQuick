import java.io.IOException;
import java.util.Scanner;

public class FoodQuick {

	public static void main(String args[]) throws IOException {
		
		System.out.println("Welcome to FoodQuick! Please enter your details below: ");
		System.out.println();
		
		String customerLocation = Customer.newCustomer();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Would you like to add a new restraunt or would you like to create a new order?: (r/o)");
		String option = sc.nextLine();
		
//		while loop to check if input is valid
		boolean running = true;
		while (running) {
			if (option.equals("r")) {
				Restraunt.newRestraunt();
				running = false;
			} else if (option.equals("o")) {
				Order.newOrder(customerLocation);
				running = false;
			} else {
				System.out.println("Invalid input");
			}	
		}
		
				
	}
	
}