package Controller;

import java.util.ArrayList;
import Entity.Staff;
import Database.StaffDB;

/**
 * The class which contains most of the methods 
 * required to implement functionalities related to
 * management of staffs.
 */
public class StaffController {
	
    /**
     * The text file that stores all staff's
     * information
	 */
	private static final String FILENAME = "Staff.txt";
	
    /**
     * The instance of the StaffController
	 */
    private static StaffController instance = null;
    
    /**
     * An arrayList to store instances of Staffs to
     * use and modify the informations
	 */
    ArrayList<Staff> staffList = new ArrayList<Staff>();
    
    /**
     * Creates a new StaffController
     * with an instance of an arraylist 
     * to store staffs information
	 */
    public StaffController() {
    	
    	staffList = new ArrayList<Staff>();
    }
     
    /**
	 * Creating new instance of staff controller
	 * @return staffController instance
	 */
    public static StaffController retrieveInstance() {
    	if(instance == null)
    		instance = new StaffController();
   
    	return instance;
    }
    
    /**
	 * @param employeeId Staff's employeeId 
	 * @return Staff instance with the same ID
	 * as the @param
	 */
	public Staff getStaff(String employeeId) {
		for(Staff staff :  staffList) {
    		if(staffList.size() != 0 && 
    				staff.getEmployeeId().equals(employeeId))
    			return staff;
    	}
		System.out.println("Employee no. "+ employeeId + " doesn't exists or wrong ID entered");
		return null;
	}
	
	/**
	 * Add a new staff into the database
	 * @param name new Staff's name
	 * @param gender new Staff's gender
	 * @param jobTitle new Staff's jobTitle
	 */
	public void addStaff(String name, String gender, String jobTitle) {
		String employeeId;
		if(staffList.isEmpty()) {
			employeeId = "01";
		}
		else {
			int Id = Integer.valueOf(staffList.get(staffList.size()-1).getEmployeeId())+1;
			if(Id < 10)
				employeeId = "0" + String.valueOf(Id);
			else
				employeeId = String.valueOf(Id);
		}		
		
		staffList.add(new Staff(name,gender,jobTitle,employeeId));
		System.out.println("New Staff " + getStaff(employeeId).getName() + " added");
		saveToDB();
	}
	
	/**
	 * Remove a Staff from the database
	 * @param employeeId ID of the staff to be removed
	 */
	public void removeStaff(String employeeId) {
		if(staffList.isEmpty()) {
			System.out.println("There're no employees currently");
			return;
		}
		else if(!(getStaff(employeeId) instanceof Staff)){
			System.out.println("Employee doesn't exist!");
			return;
		}
			
		else {		
			staffList.remove(getStaff(employeeId));
			System.out.println("Employee no. " + employeeId + " has been succesfully removed");
			saveToDB();		
		}
	}
	
	/**
	 * Display all the information of the staffs 
	 * within the database
	 */
	public void displayStaff() {
		if(staffList.isEmpty()) {
			System.out.println("There're no employees currently");
			return;
		}
		else {
			System.out.println("============================================================="
								+ "\n Employee List:"
								+ "\n=============================================================");

			for(Staff staff: staffList) {
				System.out.println("\n" + staff);		
			}
			System.out.println("\nInformation of "+ staffList.size() + " employees displayed!");
			
		}		
	}

	/**
	 * Retrieve all information of the staffs from 
	 * the text file
	 */
	public void loadinDB() {
		StaffDB staffDB = new StaffDB();
	    try {
			this.staffList = staffDB.read(FILENAME);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Save any modified/updated information 
	 * of the staffs into the text file
	 */
	public void saveToDB() {
		StaffDB staffdb = new StaffDB();
	    try {
			staffdb.save(FILENAME, staffList);
		} catch (Exception e) {
	
			e.printStackTrace();
		}		
	}
}
