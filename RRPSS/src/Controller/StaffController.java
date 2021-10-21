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
    
   //Save all staffs to DB
    public void saveToDB() {
    	StaffDB staffdb = new StaffDB();
        try {
			staffdb.save(filename, staffList);
		} catch (Exception e) {

			e.printStackTrace();
		}		
    }
    
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
	public Staff getStaff(String employeeId) {
		for(Staff staff :  staffList) {
    		if(staffList.size() != 0 && 
    				staff.getEmployeeId().equals(employeeId))
    			return staff;
    	}
		return null;
	}
	
	// add staff to database
	public void addStaff(String name, String gender, String jobTitle) {
		String employeeId;
		if(staffList.isEmpty()) {
			employeeId = "01";
		}
		else {
			if(staffList.size() < 9)
				employeeId =  "0" + String.valueOf(staffList.size() + 1);
			else
				employeeId =  String.valueOf(staffList.size() + 1);
		}		
		
		staffList.add(new Staff(name,gender,jobTitle,employeeId));
		System.out.println("New Staff " + staffList.get(Integer.valueOf(employeeId)-1).getName() + " added");
		saveToDB();
	}
	
	// remove staff from database
	public void removeStaff(String employeeId) {
		if(staffList.isEmpty()) {
			System.out.println("There're no employees currently");
			return;
		}
		try {
			staffList.get(Integer.valueOf(employeeId)-1);
		}catch(Exception e) {
			System.out.println("Employee no. "+ employeeId + " doesn't exists or wrong Id input");
			return;
		}
		staffList.remove(Integer.valueOf(employeeId)-1);
		System.out.println("Employee no. " + employeeId + " has been succesfully removed");
		saveToDB();		
	}
}
