import java.util.Scanner;
import java.time.LocalDate;
import java.io.*;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String filename = "inventory.txt"; // Specify the filename
        InventoryManager manager = new InventoryManager(filename); // Provide the filename when creating an instance
        Welcome w = new Welcome();
        int choice;

        do {
            w.welcome();
            System.out.println("\n1. Add item to inventory");
            System.out.println("2. Remove item from inventory");
            System.out.println("3. Update item quantity");
            System.out.println("4. Display inventory");
            System.out.println("5. Search for item");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume the leftover newline
            w.clearScreen();
            switch (choice) {
                case 1:
                w.welcome();
                    System.out.print("Enter item name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter your username: ");
                    String user = scanner.nextLine();
                    manager.addItem(name, quantity, user);
                    break;
                case 2:
                    System.out.print("Enter item name to remove: ");
                    name = scanner.nextLine();
                    manager.deleteItem(name);
                    break;
                case 3:
                    System.out.print("Enter item name to update: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int newQuantity = scanner.nextInt();
                    scanner.nextLine();  // Consume newline
                    System.out.print("Enter your username: ");
                    user = scanner.nextLine();
                    manager.updateItemQuantity(name, newQuantity, user);
                    break;
                case 4:
                    manager.displayInventory();
                    break;
                case 5:
                    System.out.print("Enter item name to search: ");
                    name = scanner.nextLine();
                    manager.searchItem(name);
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                    break;
            }
        } while (choice != 6);

        scanner.close();
    }
}
