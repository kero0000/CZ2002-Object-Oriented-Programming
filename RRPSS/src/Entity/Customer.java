package Entity;//hello world

public class Customer {
	private String customerId;
	private String name;
	private String phoneNumber;
	private boolean isMember;


	public Customer() {
		
	}
	
	public Customer(String customerId,String name,String phoneNumber, boolean isMember) {
		this.customerId = customerId;
		this.name = name;
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
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
