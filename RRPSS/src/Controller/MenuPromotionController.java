package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Entity.Item;
import Database.MenuPromotionDB;
/**
 * The class which contains most of the methods 
 * required to implement functionalities related to
 * management of items on the menu.
 */
public class MenuPromotionController {

    private static final String FILENAME = "Menu.txt";
    private static MenuPromotionController instance = null;
    private ArrayList<Item> items = new ArrayList<Item>();

    public MenuPromotionController() {

        items = new ArrayList<Item>();
    }

    /**
	 * Creates new instance of MenuPromotionController
	 * @return instance of MenuPromotion Controller
	 */
    public static MenuPromotionController retrieveInstance() {
        if (instance == null) {
            instance = new MenuPromotionController();
        }
        return instance;
    }
    
    /**
	 * Retrieval of food menu item
	 * @param itemId		Specifies the item id to retrieve item
	 * @return item
	 */
    public Item retrieveItem(int itemID) {
        for (Item item : items) {
            if (item.getItemId() == itemID) return item;	//Return Item if found
        }
        return null;										//Else return null
    }
    
    /**
	 * Creation of food menu item
	 * @param item			Specifies the item to be created
	 */
    public void createItem(Item item) {
        items.add(item);
        checkID();
        this.saveToDB();
    }

    /**
	 * Deletion of food menu item
	 * @param item			Specifies the item
	 */
    public void deleteItem(Item item) {
        items.remove(item);
        checkID();
        this.saveToDB();
    }

    /**
	 * Formatting to print food menu item
	 * @param item			Specifies the item
	 */
    public void printItem(Item item) {
        System.out.println(item.toString());
    }
    
    /**
	 * Deletion of food menu item
	 * @param itemID		Specifies the item id
	 * @param itemName		Specifies the item name
	 * @param itemDesc		Specifies the item description
	 * @param price			Specifies the item price
	 * @param itemType		Specifies the item type
	 */
    public void updateItem(int itemID, String itemName, String itemDesc, double price, int itemType) {
        Item item = null;
        item = retrieveItem(itemID);
        if (!itemName.isEmpty()) item.setName(itemName);
        else System.out.println("Item name cannot be empty! Original name was kept.");
        if (!itemDesc.isEmpty()) item.setDesc(itemDesc);
        else System.out.println("Item description cannot be empty! Original description was kept.");
        if (price > 0.0) item.setPrice(price);
        else System.out.println("Item price cannot be negative nor zero! Original price was kept.");
        if (itemType >= 0 && itemType <= 2) item.setType(itemType);
        else System.out.println("Item type must be either 0, 1, 2 or 3! Original type was kept.");
        System.out.println("Item " + item.getItemId() + ": " + item.getName() + " is updated.");
        //OrderController.getInstance().updateItemInOrders(item);
        this.saveToDB();
    }
    /**
	 * Formatting of menu/promotion item type
	 */
    public void displayMenu() {
        System.out.println("ID   Name                          Description                          Price(S$)");
		System.out.println("===================================Main Course===================================");	
        for (Item item : items) {
            if (item.getType() == 0)
                System.out.println(item.toString());
        }
        System.out.println("======================================Drink======================================");
        for (Item item : items) {
            if (item.getType() == 1)
                System.out.println(item.toString());
        }
        System.out.println("=====================================Dessert=====================================");
        for (Item item : items) {
            if (item.getType() == 2)
                System.out.println(item.toString());
        }
        System.out.println("====================================Promotions====================================");
        for (Item item : items) {
            if (item.getType() == 3)
                System.out.println(item.toString());
        }
    }
    /**
	 * Get menu item ID
	 */
    public void checkID() {
    	int id = 1;
		if(items!=null) {
			for(Item item : items){
				if(item.getItemId() > id) id = item.getItemId();
			}
		}
		Item.setIdCount(id+1);
    }
    /**
	 * Retrieve menu items from MenuDatabase
	 */
    public void loadInDB() {
    	MenuPromotionDB menudb = new MenuPromotionDB();
        try {
			this.items = menudb.read(FILENAME);
			checkID();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
	 * Save menu items to MenuDatabase
	 */
    public void saveToDB() {
    	MenuPromotionDB menudb = new MenuPromotionDB();
        try {
			menudb.save(FILENAME, items);
			checkID(); //ADDED TO CHECK
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }

}