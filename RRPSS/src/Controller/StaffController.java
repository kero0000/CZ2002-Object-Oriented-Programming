package Controller;

import java.io.IOException;
import java.util.ArrayList;
import Entity.Staff;
import Database.StaffDB;

public class StaffController {
	private static final String filename = "Staff.txt";
    private static StaffController instance = null;
    ArrayList<Staff> staffList = new ArrayList<Staff>();
    
    public StaffController() {
    	
    	staffList = new ArrayList<Staff>();
    }
     
    //Retrieval of all staffs from DB
    public void loadFromDB() {
    	StaffDB staffDB = new StaffDB();
        try {
			this.staffList = staffDB.read(filename);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
  /*Save all staffs to DB
    public void saveToDB() {
    	StaffDB staffdb = new StaffDB();
        try {
			staffdb.save(filename, items);
		} catch (Exception e) {

			e.printStackTrace();
		}		
    }*/
    
    /**
	 * Creating new instance of staff controller
	 * 
	 * @return staffController instance
	 */
    public static StaffController retrieveInstance() {
    	if(instance == null)
    		instance = new StaffController();
   
    	return instance;
    }
    
    /**
	 * @parameter employeeID Specifies the employeeId to 
	 * get staff's info
	 */
	public Staff getStaff(int employeeId) {
		for(Staff staff :  staffList) {
    		if(staffList.size() != 0 && 
    				staff.getEmployeeId() == employeeId)
    			return staff;
    	}
		return null;
	}  
}
