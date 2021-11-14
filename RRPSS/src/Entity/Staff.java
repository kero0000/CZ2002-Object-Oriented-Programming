package Entity;

/**
 * Represents a staff in the restaurant
 */
public class Staff {
	
	/**
	 * First and last name of staff
	 */
	private String name;
	
	/**
	 * The gender of the staff
	 */
	private String gender;
	
	/**
	 * The identification num of staff
	 */
	private String employeeId;
	
	/**
	 * The job title of staff
	 */
	private String jobTitle;
	
	
	/**
	 * Creates a new Staff with the given name, job and ID.
	 * The name should include both first and
	 * last name.
	 * @param name This Staff's name.
	 * @param gender This Staff's age.
	 * @param jobTitle This Staff's job
	 * @param employeeId This Staff's ID
	 */
	public Staff(String name, String gender, String jobTitle, String employeeId) {
		this.name = name;
		this.gender = gender;
		this.jobTitle = jobTitle;
		this.employeeId = employeeId;
	}
	
	/**
	 * Gets the first and last name of the staff
	 * @return Staff's name
	 */
		
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the gender of the staff
	 * @return Staff's gender
	 */
	public String getGender() {
		return gender;
	}
	
	/**
	 * Gets the job of the staff
	 * @return Staff's job
	 */
	public String getJobTitle() {
		return jobTitle;
	}
	
	/**
	 * Gets the ID of the staff
	 * @return Staff's ID
	 */
	public String getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * Changes the name of this Staff.
	 * This may involve a lengthy legal process.
	 * @param name This Staff's new name.
	 * Should include both first
	 * and last name.
	 */
	public void setName(String name) {
		this.name = name;
		System.out.println("Name set!");
	}
	
	/**
	 * Changes the gender of this Staff.
	 * This may involve a lengthy operation/legal process
	 * @param gender This Staff's new gender.
	 * Should include as M as Male, F as Female or Others 
	 */
	public void setGender(String gender) {
		this.gender = gender;
		System.out.println("Gender set!");
	}
		
	/** 
	 * Changes the job of this Staff.
	 * This may involve a promotion/demotion of a staff
	 * @param jobTitle This Staff's new jobTitle
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
		System.out.println("Job set!");
	}
	
	/** 
	 * Changes the ID of this Staff.
	 * Usually not required
	 * @param employeeId This Staff's new employeeId
	 */
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * default method when just the 
	 * instance of the staff is called
	 * @return a String of Staff's information
	 */
	@Override
	public String toString() {
		return employeeId + " : " + name + ", " + gender + ", " + jobTitle; 
	}
}
