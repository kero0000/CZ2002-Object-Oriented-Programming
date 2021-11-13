package Boundary;

//TODO: create promotion changing functions 

import java.util.InputMismatchException;
import java.util.Scanner;
import Entity.Item;
import Controller.MenuPromotionController;

public class MenuPromotionUI {

    private static MenuPromotionUI instance = null;
    Scanner sc = new Scanner(System.in);

    public MenuPromotionUI() {
        sc = new Scanner(System.in);
    }
    /**
     Get instance of MenuPromotionUI
     */
    public static MenuPromotionUI getInstance() {
        if (instance == null) instance = new MenuPromotionUI();
        return instance;
    }
    /**
     Prints all options onto console
     */
    public void displayOptions() {
    	int choice;
    	do {
            MenuPromotionController.retrieveInstance().displayMenu();
            System.out.println("\n==================================================");
			System.out.println(" Menu item Management: ");
			System.out.println("==================================================");
			System.out.println("(1) Create Menu item");
			System.out.println("(2) Update Menu item");
			System.out.println("(3) Remove Menu item");
			System.out.println("(4) Create promotion package item");
			System.out.println("(5) Update promotion package item");
			System.out.println("(6) Remove promotion package item");
			System.out.println("(7) Back");
            choice = Integer.valueOf(sc.nextLine());
            switch (choice) {
                case 1:
                	createMenuItem();
                    break;
                case 2:
                	updateMenuItem();
                    break;
                case 3:
                	removeMenuItem();
                    break;                 
                case 4:
                	createPromotion();
                	break;
                case 5:
                	updatePromotion();
                	break;
                case 6:
                	removePromotion();
                	break;
                case 7: // Back
                    break;
                default:
                	System.out.println("Please enter a valid option!");
					choice = 0;
            }
        } while (choice < 7);
    }
    /**
     Creates menu item
     */
    public void createMenuItem() {
        String itemName = "";
        String itemDesc = "";
        double price = 0.0;
        int itemType = -1;
        System.out.println("Enter item name:");							//Input itemName
        itemName = sc.nextLine();
        System.out.println("Enter item description:");					//Input itemDesc
        itemDesc = sc.nextLine();
        do {
		    try {
		    	System.out.println("Enter item price:");				//Input itemPrice > $0.00
		        price = sc.nextDouble();
		    	if(price <= 0.0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (price <= 0.0);
        do {
		    try {														//Input type of menuItem
		    	System.out.println("Enter type (0 - Main Course, 1 - Drink, 2 - Dessert):");
		        itemType = sc.nextInt();
		    	if(itemType != 0 && itemType != 1 && itemType != 2) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemType != 0 && itemType != 1 && itemType != 2);
        MenuPromotionController.retrieveInstance().checkID();
        Item item = new Item(itemName, itemDesc, price, itemType);
        MenuPromotionController.retrieveInstance().createItem(item);
        System.out.println("Item " + item.getItemId() + ": " + item.getName() + " is created.");
    }
    /**
     Retrieves menu item from MenuDatabase
     */
    private void updateMenuItem() {
        int itemId = -1;
        do {
		    try {
		    	System.out.println("Enter item ID to be updated:");
		        itemId = sc.nextInt();
		    	if(itemId <= 0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemId <= 0);
        Item item = MenuPromotionController.retrieveInstance().retrieveItem(itemId);
        if (item != null) {
            MenuPromotionController.retrieveInstance().printItem(item);
            doUpdate(itemId);
            MenuPromotionController.retrieveInstance().saveToDB();
        } else {
            System.out.println("Item does not exist!");
        }
    }
    /**
     * Updates name, description, price and type of menu item
     * @param itemID ID of menu item
     */
    public void doUpdate(int itemID) {
    	String itemName = "";
        String itemDesc = "";
        double price = 0.0;
        int itemType = -1;
        System.out.println("Enter item name:");
        itemName = sc.nextLine();
        System.out.println("Enter item description:");
        itemDesc = sc.nextLine();
        do {
		    try {
		    	System.out.println("Enter item price:");
		        price = sc.nextDouble();
		    	if(price <= 0.0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (price <= 0.0);
        do {
		    try {
		    	System.out.println("Enter type (0 - Main Course, 1 - Drink, 2 - Dessert):");
		        itemType = sc.nextInt();
		    	if(itemType != 0 && itemType != 1 && itemType != 2) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemType != 0 && itemType != 1 && itemType != 2);
        MenuPromotionController.retrieveInstance().updateItem(itemID, itemName, itemDesc, price, itemType);
    }
    /**
     * Updates name, description, price and type of promotion item
     * @param itemID ID of menu item
     */
    public void doPromoUpdate(int itemID) {
    	String itemName = "";
        String itemDesc = "";
        double price = 0.0;
        int itemType = 3;
        System.out.println("Enter item name:");
        itemName = sc.nextLine();
        System.out.println("Enter item description:");
        itemDesc = sc.nextLine();
        do {
		    try {
		    	System.out.println("Enter item price:");
		        price = sc.nextDouble();
		    	if(price <= 0.0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (price <= 0.0);
        MenuPromotionController.retrieveInstance().updateItem(itemID, itemName, itemDesc, price, itemType);
    }
    /**
     * Removes menu item from MenuDatabase
     */
    public void removeMenuItem() {
        int itemId = -1;
        do {
		    try {
		    	System.out.println("Enter item ID to be removed: ");
		        itemId = sc.nextInt();
		    	if(itemId <= 0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemId <= 0);
        Item item = MenuPromotionController.retrieveInstance().retrieveItem(itemId);
        if (item != null) {
            MenuPromotionController.retrieveInstance().deleteItem(item);
            System.out.println("Item has been removed.");
        } else {
            System.out.println("Item does not exist!");
        }
    }
    /**
     * Creates menu item
     */
    public void createPromotion() {
        String promoName = "";
        String promoDesc = "";
        double price = 0.0;
        int itemType = 3;
        System.out.println("Enter promo name:");						//Input itemName
        promoName = sc.nextLine();
        System.out.println("Enter promo description:");					//Input itemDesc
        promoDesc = sc.nextLine();
        do {
		    try {
		    	System.out.println("Enter item price:");				//Input itemPrice > $0.00
		        price = sc.nextDouble();
		    	if(price <= 0.0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (price <= 0.0);
        MenuPromotionController.retrieveInstance().checkID();
        Item item = new Item(promoName, promoDesc, price, itemType);
        MenuPromotionController.retrieveInstance().createItem(item);
        System.out.println("Item " + item.getItemId() + ": " + item.getName() + " is created.");
    }
    /**
     * Retrieves promotion item from MenuDatabase
     */
    private void updatePromotion() {
        int itemId = 3;
        do {
		    try {
		    	System.out.println("Enter item ID to be updated:");
		        itemId = sc.nextInt();
		    	if(itemId <= 0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemId <= 0);
        Item item = MenuPromotionController.retrieveInstance().retrieveItem(itemId);
        if (item != null) {
            MenuPromotionController.retrieveInstance().printItem(item);
            doPromoUpdate(itemId);
        } else {
            System.out.println("Item does not exist!");
        }
    }
    /**
     * Removes promotion item from MenuDatabase
     */
    public void removePromotion() {
        int itemId = 3;
        do {
		    try {
		    	System.out.println("Enter item ID to be removed: ");
		        itemId = sc.nextInt();
		    	if(itemId <= 0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (itemId <= 0);
        Item item = MenuPromotionController.retrieveInstance().retrieveItem(itemId);
        if (item != null) {
            MenuPromotionController.retrieveInstance().deleteItem(item);
            System.out.println("Item has been removed.");
        } else {
            System.out.println("Item does not exist!");
        }
    }
}
