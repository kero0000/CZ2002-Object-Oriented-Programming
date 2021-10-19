package Entity;//hello world

public class Customer {
	private String customerId;
	private String Name;
	private String phoneNumber;
	private boolean isMember;


	public Customer() {
		
	}
	
	public Customer(String customerId,String Name,String phoneNumber, boolean isMember) {
		this.customerId = customerId;
		this.Name = Name;
		this.phoneNumber = phoneNumber;
		this.isMember = isMember;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public boolean getisMember() {
		return this.isMember;
	}
	
	public void setisMember(boolean isMember) {
		this.isMember = isMember;
	}

} 
