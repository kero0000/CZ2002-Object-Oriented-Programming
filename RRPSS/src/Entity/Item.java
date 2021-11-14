package Entity;
/**
Represents an item on Menu.
Item can be a single dish/drink or a promotional set of items.
*/
public class Item {
	/**
     * ID of item in Menu
     */
    private static int idCount = 1;
    /**
     * ID of item in Menu
     */
    private int itemId;
    /**
     * Name of menu/promotion item
     */
    private String name;
    /**
     * Description of menu/promotion item
     */
    private String desc;
    /**
     * Price of menu/promotion item
     */
    private double price;
    /**
     * Type of menu/promotion item
     */
    private int type;
    /**
    Constructor of menu item
    */
    public Item(String name, String desc, double price, int type) {
        this.itemId = idCount;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.type = type;
        idCount++;
    }
    /**
    Constructor of menu item
    */
    public Item(int id, String name, String desc, double price, int type) {
        this.itemId = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.type = type;
    }
    /**
    Get ID count
    */
    public static int getIdCount() {
        return idCount;
    }
    /**
    Set ID count
    */
    public static void setIdCount(int ID) {
        idCount = ID;
    }
    /**
    Get ID of item
    */
    public int getItemId() {
        return itemId;
    }
    /**
    Get name of item
    */
    public String getName() {
        return name;
    }
    /**
    Set name of item
    */
    public void setName(String name) {
        this.name = name;
    }
    /**
    Get description of item
    */
    public String getDesc() {
        return desc;
    }
    /**
    Set description of item
    */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
    Get price of item
    */
    public double getPrice() {
        return price;
    }
    /**
    Set price of item
    */
    public void setPrice(double price) {
        this.price = price;
    }
    /**
    * Get type of item
    */
    public int getType() {
        return type;
    }
    /**
    * Set type of item
    */
    public void setType(int type) {
        this.type = type;
    }
    /**
    * Format item ID, name, description and price into string type
    * @return String of item ID, name, description and price
    */
    public String toString() {

        return (String.format("%-6d%-25s%-41s%.2f", itemId, name, desc, price));
    }

}