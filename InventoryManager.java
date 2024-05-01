import java.io.*;
import java.util.*;
import java.time.LocalDate;

public class InventoryManager {
    private Map<String, Item> inventoryMap;
    private String inventoryFilePath; // Rename to inventoryFilePath

    public InventoryManager(String inventoryFilePath) {
        this.inventoryFilePath = inventoryFilePath;
        inventoryMap = new HashMap<>();
        loadFromFile(); 
    }

    public void addItem(String name, int quantity, String modifiedBy) {
        if (inventoryMap.containsKey(name)) {
            Item existingItem = inventoryMap.get(name);
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.updateModificationDetails(modifiedBy);
        } else {
            LocalDate currentDate = LocalDate.now();
            inventoryMap.put(name, new Item(name, quantity, currentDate, modifiedBy));
        
        }
        saveToFile();
        System.out.println("Item added/updated successfully!");
    }
    

    public void deleteItem(String name) {
        if (inventoryMap.remove(name) != null) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Item not found!");
        }
        saveToFile();
    }

    public void updateItemQuantity(String name, int newQuantity, String modifiedBy) {
        if (inventoryMap.containsKey(name)) {
            Item item = inventoryMap.get(name);
            item.setQuantity(newQuantity);
            item.updateModificationDetails(modifiedBy);
            System.out.println("Quantity updated successfully!");
        } else {
            System.out.println("Item not found!");
        }
        saveToFile();
    }

    public void searchItem(String name) {
        if (inventoryMap.containsKey(name)) {
            System.out.println(inventoryMap.get(name));
        } else {
            System.out.println("Item not found!");
        }
    }

    public void displayInventory() {
        if (inventoryMap.isEmpty()) {
            System.out.println("Inventory is empty!");
        } else {
            System.out.println("Inventory:");
            for (Item item : inventoryMap.values()) {
                System.out.println(item);
            }
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(inventoryFilePath))) {
            for (Item item : inventoryMap.values()) {
                writer.println(item.getName() + "," + item.getQuantity() + "," + item.getLastModified() + "," + item.getModifiedBy());
            }
            System.out.println("Inventory data saved to " + inventoryFilePath);
        } catch (IOException e) {
            System.out.println("Error saving inventory data to file: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
    
    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inventoryFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String name = parts[0];
                    int quantity = Integer.parseInt(parts[1]);
                    String lastModified = parts[2];
                    String modifiedBy = parts[3];
                    inventoryMap.put(name, new Item(name, quantity, LocalDate.parse(lastModified), modifiedBy));
                } else {
                    System.out.println("Invalid data format in file: " + inventoryFilePath);
                }
            }
            System.out.println("Inventory data loaded from " + inventoryFilePath);
        } catch (IOException e) {
            System.out.println("Error loading inventory data from file: " + e.getMessage());
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
