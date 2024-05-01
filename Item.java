import java.time.LocalDate;

public class Item {
    private String name;
    private int quantity;
    private LocalDate lastModified; // Add a field for lastModified date
    private String modifiedBy;

    public Item(String name, int quantity, LocalDate lastModified, String modifiedBy) {
        this.name = name;
        this.quantity = quantity;
        this.lastModified = lastModified; // Assign lastModified date
        this.modifiedBy = modifiedBy;
    }

    public String getName() {
        return name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.lastModified = LocalDate.now();
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void updateModificationDetails(String modifiedBy) {
        this.modifiedBy = modifiedBy;
        this.lastModified = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Item{" +
               "name='" + name + '\'' +
               ", quantity=" + quantity +
               ", lastModified=" + lastModified +
               ", modifiedBy='" + modifiedBy + '\'' +
               '}';
    }
}
