package Entity;

/**
 * Represents a customer in the restaurant.
 */
public class Customer {
	
	/**
	 * The identification num of customer.
	 */
	private String customerId;
	
	/**
	 * The first and last name of customer.
	 */
	private String name;
	
	/**
	 * The phone number of customer.
	 */
	private String phoneNumber;
	
	/**
	 * The membership status of customer.
	 */
	private boolean isMember;

	/**
	 * Creates a new customer with the given name, phoneNumber, ID and membership status.
	 * The name should include both first and
	 * last name.
	 * @param customerId This Customer's ID
	 * @param name This Customer's name.
	 * @param phoneNumber This Customer's phoneNumber.
	 * @param isMember This Customer's Membership status
	 */
	public Customer(String customerId,String name,String phoneNumber, boolean isMember) {
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.isMember = isMember;
	}

	/**
	 * Gets the customer ID
	 * @return Customer's ID
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * Sets the new customer ID
	 * @param customerID The new customer's ID
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Gets the first and last name of the customer
	 * @return Customer's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the name of this Customer.
	 * This may involve a lengthy legal process.
	 * @param name This Customer's new name.
	 * Should include both first
	 * and last name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the phone number of customer
	 * @return Customer's phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Changes the phone number of this Customer.
	 * @param phoneNumber This Customer's new phone number.
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Gets the membership status of this Customer.
	 * @return true or false for membership
	 */
	public boolean getisMember() {
		return this.isMember;
	}
	
	/**
	 * Changes the membership status of this Customer.
	 * @param true or false for membership
	 */
	public void setisMember(boolean isMember) {
		this.isMember = isMember;
	}

} 
