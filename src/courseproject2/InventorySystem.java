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
		System.out.println("--------------------------------------------");
		System.out.println("1) Search for items in inventory system");
		System.out.println("2) Search for items by name");
		System.out.println("3) Add item to inventory system");
		System.out.println("4) Remove item from inventory system");
		System.out.println("5) Generate receipts when items purchased and inventory removed");
		System.out.println("6) Generate report for expired goods");
		System.out.println("7) Exit program");
		System.out.println("--------------------------------------------");

	}
	
	// choiceOption function allows the user to enter a number in the menu system
	public void choiceOption() {
		
		int choice = -1;
		String name = "";
		int price = 0;
		int quantity = 0;
		
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
				System.out.println("Please enter a name");
				name = scan.next();
				if(name.length() > 2) {
					searchItemName(name);
				}else {
					System.out.println("Please enter more than two characters");
				}
				break;
			case 3:
				System.out.println("Please enter a name");
				name = scan.next();
				System.out.println("Please enter the price");
				price = scan.nextInt();
				System.out.println("Please enter the quantity");
				quantity = scan.nextInt();
				addItem(name, price, quantity);
				break;
			case 4:
				System.out.println("Please enter the name of the item you want to remove");
				name = scan.next();
				removeItem(name);
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
	
	// searchItem function allows user to search database for all items
	public void searchItems() {
		System.out.println("\nSearching for items...");
		try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "root");
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
	
	// search database for item with specific name
	public void searchItemName(String itemName) {
		System.out.println("\nSearching for item... " + itemName);
		
		try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "root");
		PreparedStatement ps = connect.prepareStatement("Select * from products where productName like '%" + itemName + "%'"); ;
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			System.out.println("ID: " + rs.getString("productID") + " - Name: " + rs.getString("productName") + " - Price: " + rs.getString("price") + " - Quantity Available: " +
			rs.getString("quantityAvailable"));
		}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// addItem function allows user to add item to database
	public void addItem(String name, int price, int quantity) {
		try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "root");
		PreparedStatement ps = connect.prepareStatement("insert into products (productName, price, quantityAvailable) values(?,?,?)");
		ps.setString(1, name);
		ps.setInt(2, price);
		ps.setInt(3, quantity);
		ps.executeUpdate();
		System.out.println("Added item: " + name);
		searchItems();
		
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	// remove item function allows us to remove an item from database
	public void removeItem(String name) {
		System.out.println("removing item..." + name);
		try {
		Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/products", "root", "root");
		PreparedStatement ps = connect.prepareStatement("DELETE from products where productName =(?)");
		ps.setString(1, name);
		ps.executeUpdate();
		
		searchItems();
		}catch(SQLException e) {
			e.printStackTrace();
		}
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
