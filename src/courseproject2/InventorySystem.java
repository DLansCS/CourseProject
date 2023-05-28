package courseproject2;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventorySystem {	
	
	// run function to call main methods
	public void run() {
		
		choiceOption();
		
	}

	// intro function to print message and choices
	public void intro() {
		System.out.println("\nWelcome to S-Mart Inventory System");
		System.out.println("Please enter your choice in number format");
		System.out.println("--------------------------------------------\n");
		System.out.println("1) Search for items in inventory system");
		System.out.println("2) Remove item from inventory system");
		System.out.println("3) Create item and add them to inventory system");
		System.out.println("4) Create purchase order for items in stock");
		System.out.println("5) Generate receipts when items purchased and inventory removed");
		System.out.println("6) Generate report for expired goods");
		System.out.println("7) Exit program");
	}
	
	// choiceOption function allows the user to enter a number in the menu system
	public void choiceOption() {
		
		int choice = -1;
		Scanner scan = new Scanner(System.in);
		
		while(choice != 7) {
			
			intro();
			
			while(!scan.hasNextInt()) {
				String input = scan.next();
				System.out.println("Invalid input: " + input + " Please enter a number value 1-7");
			}

			// ask user input for menu
			choice = scan.nextInt();
			
			// switch menu to call the correct function corresponding to choice
			switch(choice) {
			case 1: 
				searchItems();
				break;
			case 2:
				removeItem();
				System.out.print("Removing...");
				break;
			case 3:
				createItem();
				break;
			case 4:
				purchaseOrder();
				break;
			case 5:
				generateReceipt();
				break;
			case 6:
				expiredGoods();
				break;
			case 7:
				System.out.println("Exiting program");
				break;
			default:
				System.out.println("Invalid input");
			}
		}
		scan.close(); // closing scanner to stop memory leaks
	}
	
	// searchItem function allows user to search database for item
	public void searchItems() {
		System.out.println("Searching for item...");
		try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseproject", "root", "root");
		PreparedStatement ps = connect.prepareStatement("Select * from products");
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("ID: " + rs.getString("productID") + " - Name: " + rs.getString("productName") + " - Price: " + rs.getString("price") + " - Quantity Available: " +
			rs.getString("quantityAvailable"));
		}
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// removeItem function allows user to remove item from database
	public void removeItem() {
		System.out.println("Removing item...");
	}
	
	// create item allows user to create a new item and save in database
	public void createItem() {
		System.out.println("Creating item...");
	}
	
	// purchaseOrder function allows us to print a purchase order receipt
	public void purchaseOrder() {
		System.out.println("Purchasing order...");
	}
	
	// generate receipt allows us to print items removed from database
	public void generateReceipt() {
		System.out.println("Generating receipt...");
	}
	
	// expiredGoods function to print food and expiration dates
	public void expiredGoods() {
		System.out.println("Expired goods report...");
	}
}
