package Entity;

import java.text.DecimalFormat;

import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import Controller.StaffController;


/**
 * Represents an order made for each table in the restaurant
 */
public class Order {
	/**
	 * Represents GST rates
	 */
	private static final double GST = 1.07;
	/**
	 * Represents service charge rates
	 */
	private static final double SC = 1.10;
	/**
	 * Represents discount applicable for membership customer
	 */
	private static final double DISCOUNT = 0.1;
	/**
	 * Represents count of the order
	 */
	private static int idCount = 1;
	/**
	 * Represents order's unique id
	 */
    private int orderId;
    /**
     * Represents table's unique id
     */
    private String tableId;
    /**
     * Represents an employee's unique id
     */
	private String employeeId;
	/**
	 * Represents whether the customer have membership and can have discount
	 */
	private String membership;
	/**
	 * Represents unique reservationNumber
	 */
    private String reservationNum;
    /**
     * Represents an arraylist of items that the order contains
     */
    private ArrayList<Item> items = new ArrayList<Item>();
    /**
     * Represents date when the order is created
     */
    private String date;
    /**
     * Represents total price of all the items ordered
     */
    private double totalprice;
    /**
     * Checks whether order invoice has been made/payment has been made
     */
	private boolean isPrintedInvoice = false; //new field
	/**
	 * date formatter
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");    
	/**
	 * controller to obtain waiter's unique id
	 */
	private StaffController staffs = new StaffController();	
	
	/**
	 * Order constructor 1
	 * @param tableId  unique tableId
	 * @param employeeId	unique employeeId of the waiter taking this order
	 * @param membership	to check whether the customer entitled to discount
	 * @param items		an arraylist of items to be ordered
	 */
	public Order(String tableId, String employeeId, String memebership, ArrayList<Item> items){
        this.orderId = idCount;
        this.items = items;
        Calendar c = Calendar.getInstance();
        String d = sdf.format(c.getTime());
        this.date = d;
        this.tableId = tableId;
        this.employeeId = employeeId;
        this.membership = membership;
        idCount++;
    }
	/**
	 * Order constructor 2
	 * @param  orderId unique id of this order
	 * @param tableId  unique tableId
	 * @param employeeId	unique employeeId of the waiter taking this order
	 * @param membership	to check whether the customer entitled to discount
	 * @param reservationNum		unique reservationNum of the order 
	 * @param isPrintedInvoice 	boolean check whether the invoice/payment is made for this order
	 * @param items		an arraylist of items to be ordered
	 * @param 	date of the creation of this order
	 */
    public Order(int orderId, String tableID, String employeeId, String membership, String reservationNum, boolean isPrintedInvoice, ArrayList<Item> items, String date){
        this.orderId = orderId;
        this.tableId = tableID;
        this.employeeId = employeeId;
        this.membership = membership;
        this.reservationNum = reservationNum;
        this.isPrintedInvoice = isPrintedInvoice;
        this.items = items;
        this.date = date;
        idCount = orderId+1;//ADDED TO CHECK //removed on left
    }
    
	/**
	 * Order constructor 3
	 * @param tableId  unique tableId
	 * @param employeeId	unique employeeId of the waiter taking this order
	 * @param membership	to check whether the customer entitled to discount
	 */
    public Order(String tableId,String employeeId, String membership){
        this.orderId = idCount;
        this.employeeId = employeeId;
        this.membership = membership;
        Calendar c = Calendar.getInstance();
        String d = sdf.format(c.getTime());
        this.date = d;
        this.tableId = tableId;
        idCount++;
    }
    
	/**
	 * get unique id count of the order
	 * @return idCount
	 */
    public static int getIdCount() {
        return idCount;
    }
	/**
	 * set unique id count of order
	 * @param ID current id in the text file
	 */
    public static void setIdCount(int ID) {
        idCount = ID;
    }
	/**
	 * get array of items ordered
	 * @return items ordered in current order
	 */
    public ArrayList<Item> getItems() {
        return items;
    }
	/**
	 * get unique id count of the order
	 * @return idCount
	 */
    public int getOrderId() {
        return orderId;
    }
	/**
	 * set unique order ID of current order
	 * @param orderId
	 */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
	/**
	 * get unique tableId of the current Order
	 * @return tableId
	 */
    public String gettableId() {
        return tableId;
    }
	/**
	 * set tableId of the current order
	 * @param tableId
	 */
    public void settableId(String tableId) {
        this.tableId = tableId;
    }
	/**
	 * get unique reservatioNumber of the current order
	 * @return reservationNumber
	 */
    public String getReservationNum() {
		return reservationNum;
	}
	/**
	 * set reservationNumber of the current order
	 * @param reservationNumber
	 */
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}
	/**
	 * get boolean whether current order has been paid and invoice printed
	 * @return isPrintedInvoice boolean
	 */
	public boolean getIsPrintedInvoice() {
        return isPrintedInvoice;
    }
	/**
	 * set boolean whether the order invoice is printed
	 * @param isPrintedInvoice 	boolean whether the order invoice is printed
	 */
    public void setIsPrintedInvoice(boolean isPrintedInvoice) {
        this.isPrintedInvoice = isPrintedInvoice;
    }
	/**
	 * get unique id count of the order
	 * @return date date the order created
	 */
	public String getDate() {
        return date;
    }
	
	/**
	 * get date this date
	 * @return date this date
	 */
	public Date getDateObject() {
        try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
        return null;
    }	
	/**
	 * set date when the order is created
	 * @param date date which the order created
	 */
    public void setDate(String date) {
        this.date = date;
    }

	/**
	 * get staff's Id
	 * @return employeeId 
	 */
    public String getStaffId() {
    	return employeeId;
    }
    
	/**
	 * set staff Id 
	 * @param employeeId
	 */
    public void setStaffId(String employeeId) {
    	this.employeeId = employeeId;
    }
    
	/**
	 * add chosen item from menu to order list
	 * @param item from menu to be added
	 */
    public void addItem(Item item) {
        this.items.add(item);
    }
	/**
	 * remove item from the order 
	 * @param item from order to be removed
	 */
    public boolean removeItem(Item item) {
        for (Item it : items) {
            if (it.getItemId() == item.getItemId()) {
                this.items.remove(it);
                return true;
            }
        }
        return false;
    }
	/**
	 * get membership status of  customer whether got discount
	 * @return membership status of customer
	 */
    public String getMembership() {
    	return membership;
    }
	/**
	 * set membership status to check whether can apply discount
	 * @param membership 
	 */
    public void setMembership(String membership) {
    	this.membership = membership;
    }
	/**
	 * obtain subtotal of the current order
	 * @return currentTotal of the order items
	 */
    public double subTotal() {
    	double currentTotal = 0;
    	for(Item item : items) {
    		currentTotal += item.getPrice();	
    	}
    	if(membership.equalsIgnoreCase("yes"))
    		return currentTotal - discount();
    	else
    		return currentTotal;
    }
	/**
	 * apply discount on the current Total of the order
	 * @return currentTotal with discount applied
	 */
    public double discount() {
    	double currentTotal = 0;
    	for(Item item : items) {
    		currentTotal += item.getPrice();	
    	}
    	
    	return currentTotal * DISCOUNT;
    }
	/**
	 * get taxes applied to current order
	 * @return tax applied to current order
	 */
    public double taxes(){
    	double tax = 0;	
    	tax = (subTotal() * GST * SC) - subTotal();
    	return tax;
    }
	/**
	 * get total price of current order
	 * @return total Price of the current order
	 */
    public double totalPrice() {
    	this.totalprice = 0;
    	totalprice = subTotal() + taxes();
    	return totalprice;
    }
	/**
	 * prints order invoice and vacate table/ payment made
	 */
    public void viewInvoice() {
    	staffs.loadinDB();
        System.out.println("                                      RRPSS                                      ");
        System.out.println("=================================================================================");
        System.out.println("Date: " + date);
        System.out.println("OrderId: " + orderId);
        System.out.println("Order Status: " + (isPrintedInvoice ? "PAID" : "NOT YET PAID"));
        System.out.println("Table: " + tableId);
        System.out.println("Reservation No: " + reservationNum);
        System.out.println("Served By: "+ employeeId + " "+ staffs.getStaff(employeeId).getName());
        System.out.println("ID    Name                     Description                              Price(S$)");
        System.out.println("=================================================================================");
        for (Item item : items) {
        	System.out.println(item.toString());
        }
        System.out.println("=================================================================================");
        if(membership.equalsIgnoreCase("yes"))
        System.out.printf("%-71s" + toCurrency(discount()), "Discount:");														     
        System.out.printf("\n%-71s" + toCurrency(subTotal()), "SubTotal:");	
        System.out.printf("\n%-71s" + toCurrency(taxes()), "Taxes:");	 
        System.out.println("\n---------------------------------------------------------------------------------");
        System.out.printf("\n%-71s" + toCurrency(totalPrice()), "Total:");   
        System.out.println("\n=================================================================================");
        System.out.println("*                         Thank you for dining with us!                         *");
        System.out.println("=================================================================================");
    }
    
	/**
	 * convert the amt to currency function
	 * @param amt of order to convert currency
	 * @return amt that has been converted
	 */
    private String toCurrency(double amt) {
    	Locale locale = new Locale("en-SG", "SG");      
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        DecimalFormat decimalFormat = ((DecimalFormat) currencyFormat); //explicit downcast 
        		
        DecimalFormatSymbols currencySymbol = decimalFormat.getDecimalFormatSymbols();
        
        currencySymbol.setCurrencySymbol("");   //remove symbol
        decimalFormat.setDecimalFormatSymbols(currencySymbol);  //remove symbol
        
        return (currencyFormat.format(amt));
    }
}
