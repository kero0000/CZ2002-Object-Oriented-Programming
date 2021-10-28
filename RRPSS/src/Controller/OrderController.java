package Controller;

import java.io.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import Entity.Order;
import Entity.Item;
import Database.OrderDB;


public class OrderController{
    
	private static final String filename = "Order.txt";
    private static OrderController instance = null;
    ArrayList<Order> orderList = new ArrayList<Order>();
    
    public OrderController() {
    	orderList = new ArrayList<Order>();
    }

    public static OrderController retrieveInstance() {
        if (instance == null) {
            instance = new OrderController();
        }
        return instance;
    }
    
    public void createOrderItem(Order order, int itemId) {
        Item item = MenuController.retrieveInstance().retrieveItem(itemId);
        if (item != null) order.addItem(item);
        else System.out.println("This item does not exist");
    }

    public void updateOrder(Order order) {
        orderList.remove(order);
        checkID(); 
        orderList.add(order);
    }


    public void deleteOrder(Order order) { // for when order is empty so can just delete
    	int id = order.getOrderId(); 
    	if(id == (Order.getIdCount()-1))	
    		Order.setIdCount(id); 
        orderList.remove(order);
        checkID(); 
    }
    

    public Order retrieveOrder(int orderID) {
        for (Order order : orderList) {
            if (order.getOrderId() == orderID)
                return order;
        }
        return null;
    }
    

    public ArrayList<Order> retrieveOrderList(String tableID) {
    	ArrayList<Order> ol = new ArrayList<Order>();
    	for (Order order : orderList) {
            if (order.gettableId().equals(tableID))
                ol.add(order);
        }
    	if(ol.size() > 0) return ol;
    	else return null;
    }


    public void printOrderInvoice(int orderID) {
        Order order;
        order = retrieveOrder(orderID);
        order.viewInvoice();
     // call tablecontroller.updateTableStatus to vacant
    }
    
    public void displayOrder(int orderID) {
    	Order order;
        order = retrieveOrder(orderID);
        order.viewInvoice();
    }
    
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
        this.savetoDB();
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


    public void loadinDB() {
    	OrderDB orderdb = new OrderDB();
        try {
			this.orderList = orderdb.read(filename);
			checkID(); //ADDED TO CHECK
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    

    public void savetoDB() {
    	OrderDB orderdb = new OrderDB();
        try {
        	orderdb.save(filename, orderList);
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
    		
    		/*
    		if (order.getIsPrintedInvoice() == true
    				&& order.getDateObject().getMonth() == month 
    				&& order.getDateObject().getYear() == year)	{
    		*/
    		
    		// why we have -1 in that month, because the Calendar class starts with 0 for january.
    		if (order.getIsPrintedInvoice() == true
    				&& orderDateCalendar.get(Calendar.MONTH) == month-1 
    				&& orderDateCalendar.get(Calendar.YEAR) == year)	{
    			
    			totalSales += order.totalPrice();
    		}
    	}
    	
    	System.out.println(String.format("\nTotal Sales for the period of %d/%d is %.2f SGD.\n\n", month, year, totalSales));    
    }

}