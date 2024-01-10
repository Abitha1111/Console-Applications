package View;

import java.util.Scanner;
import Model.Product;
import Repository.DatabaseConnection;
import viewmodel.InventoryViewModel;

public class InventoryManagement {
	public static void main(String[] args) {
		InventoryViewModel viewModel = new InventoryViewModel();
		Scanner sc = new Scanner(System.in);
		int item;

		do {
			System.out.println("---Inventory Management System---\n");
			System.out.println("1. Add new Item");
			System.out.println("2. Search Item");
			System.out.println("3. Display all Item");
			System.out.println("4. Update Item ");
			System.out.println("5. Remove Item ");
			System.out.println("6. Exit");
			System.out.print("Enter your choice : \n");

			item = sc.nextInt();
			switch (item) {
			case 1:
				System.out.println("--Add new Item--\n");
				System.out.print("Enter Item name : ");
				String name = sc.next();
				System.out.print("Enter Item quantity : ");
				int quantity = sc.nextInt();
				DatabaseConnection.insertDatabase(name, quantity);
				break;

			case 2:
				System.out.println("--Search Item--\n");
				System.out.println("1. Search by Item Number");
				System.out.println("2. Search by Item Name");
				System.out.print("Enter your choice: ");
				int searchChoice = sc.nextInt();
				if(searchChoice==1) {
					System.out.println("Enter the item no:");
					int searchno=sc.nextInt();
					DatabaseConnection.searchByNumber(searchno);
				}
				else if(searchChoice==2) {
					System.out.println("Enter the item name:");
					String searchname=sc.next();
					DatabaseConnection.searchByName(searchname);
				}
				else {
					System.out.println("Wrong choice\n");
				}
					
				break;

			case 3:
				System.out.println("--Display all Item--\n");
				DatabaseConnection.displayAllItems();
			
				break;

			case 4:
				System.out.println("--Update Item--");
				System.out.print("Enter Item no to update: ");
				int ItemNo = sc.nextInt();
				System.out.print("Enter new Item Name: ");
				String newName = sc.next();
				System.out.print("Enter new Item Quantity to update: ");
				int updateQuantity = sc.nextInt();
				DatabaseConnection.updatedatabase(newName, updateQuantity, ItemNo);
				break;

			case 5:
				System.out.println("--Remove Item--");
				System.out.println("Enter Item no:");
				int removeItemNo = sc.nextInt();
				DatabaseConnection.removedatabase(removeItemNo);

				break;

			case 6:
				System.out.println("Exiting...");
				break;

			default:
				System.out.println("Invalid choice!");
				break;
			}
		} while (item != 6);
	}
}
