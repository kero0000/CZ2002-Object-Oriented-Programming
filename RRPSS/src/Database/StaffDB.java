package Database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Item;
import Entity.Staff;

public class StaffDB implements DB {
	public static final String SEPARATOR = "|";
	
	@Override
	public ArrayList<Staff> read(String fileName) throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) ReadinFile.read(fileName);
		ArrayList<Staff> staffList = new ArrayList<Staff>();
		
		for (int i = 0; i < stringArray.size(); i++) {
			String st = stringArray.get(i);
			
			StringTokenizer star = new StringTokenizer(st, SEPARATOR);
			
			int employeeId = Integer.valueOf(star.nextToken().trim());
			String name = star.nextToken().trim();
			String gender = star.nextToken().trim();
			String jobTitle = star.nextToken().trim();

			Staff staffInfo = new Staff(name, gender, jobTitle, employeeId);
			staffList.add(staffInfo);
		
		}
		return staffList;
	}
	
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
