package Entity;

public class Staff {
	private String name;
	private String gender;
	private int employeeId;
	private String jobTitle;
	
	public Staff(String name, String gender, String jobTitle, int employeeId) {
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.employeeId = employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getGender() {
		return gender;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}
}
