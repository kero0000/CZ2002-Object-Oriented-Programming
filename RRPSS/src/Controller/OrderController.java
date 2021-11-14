package Controller;

import java.io.*;

import java.util.ArrayList;
import java.util.Calendar;
import Entity.Order;
import Entity.Item;
import Database.OrderDB;

/**
 * The class which contains most of the methods 
 * required to implement functionalities related to
 * management of orders.
 */
public class OrderController{
    
    /**
     * The text file that stores all order's
     * information
	 */
	private static final String FILENAME = "Order.txt";
	
    /**
     * Instance of OrderController
	 */
    private static OrderController instance = null;
    
    /**
     * Array to store all instances of orders
	 */
    private ArrayList<Order> orderList = new ArrayList<Order>();
    
    /**
     * Creates a new OrderController
     * with an instance of an arraylist 
     * to store order's information
	 */
    public OrderController() {
    	orderList = new ArrayList<Order>();
    }

    /**
	 * Creating new instance of order controller
	 * @return OrderController instance
	 */
    public static OrderController retrieveInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    
    /**
	 * Add a new item to order
	 * @param order Order instance
	 * @param itemId ID of item to be added
	 */
    public void createOrderItem(Order order, int itemId) {
        Item item = MenuPromotionController.retrieveInstance().retrieveItem(itemId);
        if (item != null) { 
        	order.addItem(item);
        	this.saveToDB();}
        else System.out.println("This item does not exist");
    }
    
    /**
	 * Update order
	 * @param order Order instance that is to be updated
	 */
    public void updateOrder(Order order) {
        orderList.remove(order);
        checkID(); 
        orderList.add(order);
        this.saveToDB();
    }

    /**
	 * Remove order from database
	 * @param order Order instance that is to be removed
	 */
    public void deleteOrder(Order order) { // for when order is empty so can just delete
    	int id = order.getOrderId(); 
    	if(id == (Order.getIdCount()-1))	
    		Order.setIdCount(id); 
        orderList.remove(order);
        this.saveToDB();
        checkID(); 
    }
    
    /**
	 * Retrieve a specific order
	 * @param orderID ID of order instance that is to be retrieved
	 */
    public Order retrieveOrder(int orderID) {
        for (Order order : orderList) {
            if (order.getOrderId() == orderID)
                return order;
        }
        return null;
    }
    
    /**
	 * Retrieve a orders from a table
	 * @param tableID ID of the table
	 * @return an array list of order instances from a particular table
	 */
    public ArrayList<Order> retrieveOrderList(String tableID) {
    	ArrayList<Order> ol = new ArrayList<Order>();
    	for (Order order : orderList) {
            if (order.gettableId().equals(tableID))
                ol.add(order);
        }
    	if(ol.size() > 0) return ol;
    	else return null;
    }

    /**
	 * prints the order Invoice
	 * @param orderID orderID of the invoice to be printed
	 */
    public void printOrderInvoice(int orderID) {
        Order order;
        order = retrieveOrder(orderID);
        order.viewInvoice();
     // call tablecontroller.updateTableStatus to vacant
    }
    
    /**
   	 * display the order Invoice
   	 * @param orderID orderID of the invoice to be displayed
   	 */
    public void displayOrder(int orderID) {
    	Order order;
        order = retrieveOrder(orderID);
        order.viewInvoice();
    }
    
    /**
   	 * Update the items in an order
   	 * @param item The item to be updated to
   	 */
    public void updateItemInOrders(Item item) {
        for (Order order : orderList) {
            ArrayList<Item> items = order.getItems();
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getItemId() == item.getItemId()) {
                    items.set(i, item);
                    System.out.println(String.format("Order %d was updated.", order.getOrderId()));
                }
            }
        }
        this.saveToDB();
    }
    
    /**
	 * Checking of order id
	 * 
	 */
    public void checkID() {
    	int id = 1;
		if(orderList!=null) {
			for(Order order : orderList){
				if(order.getOrderId() > id) id = order.getOrderId();
			}
		}
		Order.setIdCount(id+1);
    }

    /**
	 * Retrieve all information of the orders from 
	 * the text file
	 */
    public void loadInDB() {
    	OrderDB orderdb = new OrderDB();
        try {
			this.orderList = orderdb.read(FILENAME);
			checkID(); //ADDED TO CHECK
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    /**
	 * Save all information of the orders 
	 * into the text file
	 */
    public void saveToDB() {
    	OrderDB orderdb = new OrderDB();
        try {
        	orderdb.save(FILENAME, orderList);
        	checkID(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
    }
    
    
    @SuppressWarnings("deprecation")
	public void printSalesReport(int month, int year)	{
    	double totalSales = 0; 
    	
    	
    	
    	for(Order order : this.orderList)	{
    		Calendar orderDateCalendar = Calendar.getInstance();
    		orderDateCalendar.setTime(order.getDateObject());
    		
    		
    		
    		if (order.getIsPrintedInvoice() == true
    				&& orderDateCalendar.get(Calendar.MONTH) == month-1 
    				&& orderDateCalendar.get(Calendar.YEAR) == year)	{
    			
    			totalSales += order.totalPrice();
    		}
    	}
    	
    	System.out.println(String.format("\nTotal Sales for the period of %d/%d is %.2f SGD.\n\n", month, year, totalSales));    
    }

}