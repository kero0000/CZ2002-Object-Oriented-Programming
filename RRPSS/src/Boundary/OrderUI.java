package Boundary;

import java.util.InputMismatchException;
import java.util.Scanner;
import Entity.Order;
import Entity.Item;
import Controller.MenuPromotionController;
import Controller.OrderController;
import Controller.TableController;

/**
 * the order user interface
 */
public class OrderUI {
    public static OrderUI instance = null;
    Scanner sc = new Scanner(System.in);
   
    
    private OrderUI() {
        sc = new Scanner(System.in);
    }
	/**
	 * create instance of order UI
	 * @return instance of orderUI
	 */
    public static OrderUI getInstance() {
        if (instance == null) instance = new OrderUI();
        return instance;
    }
	/**
	 * display the options of what the user wants to do
	 */
    public void displayOptions() {
        int choice;
    	do {
            System.out.println("\n==================================================");
			System.out.println(" Table Orders: ");
			System.out.println("==================================================");
			System.out.println("(1) Create Order");
			System.out.println("(2) Update Order");
			System.out.println("(3) Remove Order");
			System.out.println("(4) View Order");
			System.out.println("(5) Print Order Invoice (confirms payment for this order.)");
			
			System.out.println("(6) Back");
			
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                	createOrder();
                    break;
                case 2:
                	Scanner sc = new Scanner(System.in);
                    int id = -1;
                    do {
					    try {
					    	System.out.println("Enter order ID to be updated: ");
					    	id = sc.nextInt();
					    	if(id <= 0) System.out.printf("Invalid input! ");
					    } catch (InputMismatchException e) {
					    	System.out.printf("Invalid input! ");
					    }
					    sc.nextLine();
					} while (id <= 0);
                    Order order = OrderController.retrieveInstance().retrieveOrder(id);
                    if (order != null) updateOrder(order);
                    else System.out.println("Order does not exist!");
                    break;
                case 3: 
                      OrderUI.getInstance().runRemoveOrder(); 
                    break;
                case 4:
                	viewOrder();
                    break;
                case 5:
                	printOrderInvoice();
                	
                    break; 
                    
                case 6:
                    break; // MUST go back to mainUI and invoke OrderController.savetoDB() to save changes made to Order.txt
                default:
                	System.out.println("Please enter a valid option!");
					choice = 0;
            }
        } while (choice < 5);
    }
	/**
	 * create order
	 */
    public void createOrder() {
    	sc = new Scanner(System.in);
    	String tableId;
        String employeeId;
        String membership;
        System.out.println("Enter table ID:");
        tableId = sc.nextLine();
        System.out.println("Enter staff identification number:");
        employeeId = sc.nextLine();
        System.out.println("Enter membership status:");
        membership = sc.nextLine().toLowerCase();
        System.out.println("");
        TableController.updateTableStatus(tableId, "OCCUPIED");
        OrderController.retrieveInstance().checkID();
        Order order = new Order(tableId,employeeId, membership);

        updateOrder(order);
        System.out.println("Order created for table " + tableId);
        OrderController.retrieveInstance().saveToDB();
    }
	/**
	 * update a specific order
	 * @param order that is in text file
	 */
    public void updateOrder(Order order) {
    	sc = new Scanner(System.in);
    	int input;
    	MenuPromotionController.retrieveInstance().loadInDB();// get menu data from menu.txt at the start of the function
        int id;
        do {
        	System.out.println("Please Choose a option to Continue:");
            	System.out.println("(1) Add item");
            	System.out.println("(2) Remove item");
    			System.out.println("(3) Finish");
            
            input = sc.nextInt();
            switch (input) {
                case 1: // add item to current order
                    MenuPromotionController.retrieveInstance().displayMenu();// display menu options
                    System.out.println("");
                    id = -1;
                    do {
					    try {
					    	System.out.print("Enter Item ID: ");
		                    id = sc.nextInt();
					    	if(id <= 0) System.out.printf("Invalid input! ");
					    } catch (InputMismatchException e) {
					    	System.out.printf("Invalid input! ");
					    }
					    sc.nextLine();
					} while (id <= 0);
                    OrderController.retrieveInstance().createOrderItem(order, id);
                    input = 0;
                    break;
                case 2:// remove item from current order
                		if (!(order.getItems().isEmpty())) {
                    		id = -1;
                            do {
        					    try {
        					    	System.out.print("Enter Item ID: ");
        		                    id = sc.nextInt();
        					    	if(id <= 0) System.out.printf("Invalid input! ");
        					    } catch (InputMismatchException e) {
        					    	System.out.printf("Invalid input! ");
        					    }
        					    sc.nextLine();
        					} while (id <= 0);
                            Item it = MenuPromotionController.retrieveInstance().retrieveItem(id);
                            if (it == null)
                                System.out.println("Item does not exist!");
                            else if (!order.removeItem(it))
                                System.out.println("Item not in order!");
                            input = 0;
                    	}
                    	else System.out.println("Order has no item!");
                     
                	break;
                case 3:
                	break;
            }
            OrderController.retrieveInstance().updateOrder(order);
            order.viewInvoice();
        } while (input < 3);
        //OrderController.retrieveInstance().savetoDB();
    }
    
	/**
	 * remove an order
	 */
    public void runRemoveOrder() {
    	sc = new Scanner(System.in);
    	int orderID = -1;
        do {
		    try {
		    	System.out.print("Enter Order ID:");
		        orderID = sc.nextInt();
		    	if(orderID <= 0) System.out.printf("Invalid input! ");
		    } catch (InputMismatchException e) {
		    	System.out.printf("Invalid input! ");
		    }
		    sc.nextLine();
		} while (orderID <= 0);
        Order order = OrderController.retrieveInstance().retrieveOrder(orderID);
        if (order != null) {
        	OrderController.retrieveInstance().deleteOrder(order);
            System.out.printf("Order %d has been removed.\n", orderID);
        }
        else System.out.println("Order does not exist!");
    }
	/**
	 * view an order 
	 */
	public void viewOrder() {
		sc = new Scanner(System.in);
		int orderID = -1;
		do {
			try {
				System.out.print("Enter Order ID:");
				orderID = sc.nextInt();
				if (orderID <= 0)
					System.out.printf("Invalid input! ");
			} catch (InputMismatchException e) {
				System.out.printf("Invalid input! ");
			}
			sc.nextLine();
		} while (orderID <= 0);
		Order order = OrderController.retrieveInstance().retrieveOrder(orderID);
		if (order != null)
			order.viewInvoice();
		else
			System.out.println("Order does not exist!");
	}
	/**
	 * make payment and print order invoice and vacate table
	 */
	public void printOrderInvoice() {
		sc = new Scanner(System.in);
		int orderID = -1;
		do {
			try {
				System.out.print("Enter Order ID:");
				orderID = sc.nextInt();
				if (orderID <= 0)
					System.out.printf("Invalid input! ");
			} catch (InputMismatchException e) {
				System.out.printf("Invalid input! ");
			}
			sc.nextLine();
		} while (orderID <= 0);
		Order order = OrderController.retrieveInstance().retrieveOrder(orderID);
		if (order != null)	{
			order.setIsPrintedInvoice(true);
			OrderController.retrieveInstance().updateOrder(order);
			OrderController.retrieveInstance().saveToDB();
			order.viewInvoice();
			TableController.updateTableStatus(order.gettableId(), "VACANT");
			System.out.println("\nPrinted order invoice. Order is considered paid.\n\n");
		}
		else
			System.out.println("Order does not exist!");
	}
	
	
	
    
}