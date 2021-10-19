package Entity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import Controller.StaffController;

public class Order {
	private static final double GST = 1.07;
	private static final double SC = 1.10;
	private static int idCount = 1;
	private String employeeId;
    private int orderId;
    private String tableId;
    private String reservationNum;
    private ArrayList<Item> items = new ArrayList<Item>();
    private ArrayList<Promotion> promotions = new ArrayList<Promotion>();
    private String date;
    private String status = "Ordering";
    private String remarks = "";
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm:ss");    
	StaffController staffs = new StaffController();

	public Order(String tableId, String employeeId, ArrayList<Item> items, String status, String remarks){
        this.orderId = idCount;
        this.items = items;
        Calendar c = Calendar.getInstance();
        String d = sdf.format(c.getTime());
        this.date = d;
        this.status = status;
        this.remarks = remarks;
        this.tableId = tableId;
        this.employeeId = employeeId;
        idCount++;
    }
    
    public Order(int orderId, String tableID, String employeeId, String reservationNum, ArrayList<Item> items, String date, String status, String remarks){
        this.orderId = orderId;
        this.tableId = tableID;
        this.employeeId = employeeId;
        this.reservationNum = reservationNum;
        this.items = items;
        this.date = date;
        this.status = status;
        this.remarks = remarks;
        idCount = orderId+1;//ADDED TO CHECK //removed on left
    }
    
    public Order(String tableId,String employeeId){
        this.orderId = idCount;
        this.employeeId = employeeId;
        Calendar c = Calendar.getInstance();
        String d = sdf.format(c.getTime());
        this.date = d;
        this.tableId = tableId;
        idCount++;
    }
    
    public static int getIdCount() {
        return idCount;
    }

    public static void setIdCount(int ID) {
        idCount = ID;
    }
    
    public ArrayList<Item> getItems() {
        return items;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String gettableId() {
        return tableId;
    }
    
    public void settableId(String tableId) {
        this.tableId = tableId;
    }

    public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}

	public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }
    
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    public String getStaffId() {
    	return employeeId;
    }
    
    public void setStaffId(String employeeId) {
    	this.employeeId = employeeId;
    }
    public void addItem(Item item) {
        this.items.add(item);
    }

    public boolean removeItem(Item item) {
        for (Item it : items) {
            if (it.getItemId() == item.getItemId()) {
                this.items.remove(it);
                return true;
            }
        }
        return false;
    }
    
    public double subTotal(){
    	double currentTotal = 0;
    	for(Item item : items) {
    		currentTotal += item.getPrice();	
    	}
    	return currentTotal;
    }
    
    public double taxes(){
    	double tax = 0;	
    	tax = subTotal() * GST * SC;
    	return tax;
    }
    public double totalPrice() {
    	double total = 0;
    	total = subTotal() + taxes();
    	return total;
    }
    
    public void viewInvoice() {
        System.out.println("                                      RRPSS                                      ");
        //System.out.println(toString());
        System.out.println("=================================================================================");
        System.out.println("Date: " + date);
        System.out.println("Table: " + tableId);
        System.out.println("Reservation No: + reservationNum");
        System.out.println("Attended By:"+ employeeId + " "+ StaffController.retrieveInstance().getStaff(employeeId).getName());
        System.out.println("ID                                 Description                          Price(S$)");
        System.out.println("=================================================================================");
        for (Item item : items) {
        	System.out.println(item.toString());
        }
        System.out.println("=================================================================================");
        System.out.println("Subtotal:														        "+ toCurrency(subTotal()));
        System.out.println("Taxes:                                                                 	"+ toCurrency(taxes()));
        System.out.println("=================================================================================");
        System.out.println("Total:                                                                 	"+ toCurrency(totalPrice()));
        System.out.println("=================================================================================");
        System.out.println("*                         Thank you for dining with us!                         *");
        System.out.println("=================================================================================");
    }
    
    public String toString() {

        return (String.format("%-5d%-30s%-10s", orderId, remarks, status));
    }
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