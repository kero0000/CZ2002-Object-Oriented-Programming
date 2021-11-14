package Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Item;
import Entity.Staff;

/**
 * Contains the methods to read and save data into and from the Staff text file.
 */
public class StaffDB implements DB {
	
	/**
	 * Symbol indicator to separate 
	 * the attributes of the staffs
	 */
	public static final String SEPARATOR = "|";
		
	/**
	 * Read the whole Staff.txt file.
	 * @param filename Staff.txt file to be read.
	 * @return orderList arraylist of all the Staffs in the Staff.txt file
	 */
	@Override
	public ArrayList<Staff> read(String fileName) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) ReadinFile.read(fileName);
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		
		for (int i = 0; i < stringArray.size(); i++) {
			String st = stringArray.get(i);
			
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			String employeeId = star.nextToken().trim();
			String name = star.nextToken().trim();
			String gender = star.nextToken().trim();
			String jobTitle = star.nextToken().trim();

			Staff staffInfo = new Staff(name, gender, jobTitle, employeeId);
			staffList.add(staffInfo);
		
		}
		return staffList;
	}
	
	/**
	 * save a list of staff objects into Staff.txt file.
	 * @param filename Staff.txt file to be read.
	 * @param orderList The list of Staffs to be saved in the Staff.txt file.
	 */
	@Override
	public void save(String filename, List staffList) throws IOException {
		ArrayList<String> tempList = new ArrayList<String>(); //temp store Staff info
		
		for (int i = 0; i < staffList.size(); i++) {
			Staff staff = (Staff)staffList.get(i); //explicit downcast
			
			StringBuilder st = new StringBuilder();
			st.append(staff.getEmployeeId());
			st.append(SEPARATOR);
			st.append(staff.getName());
			st.append(SEPARATOR);
			st.append(staff.getGender());
			st.append(SEPARATOR);
			st.append(staff.getJobTitle());
			st.append(SEPARATOR);

			tempList.add(st.toString());
		}
		
		ReadinFile.write(filename, tempList);
	}

}
