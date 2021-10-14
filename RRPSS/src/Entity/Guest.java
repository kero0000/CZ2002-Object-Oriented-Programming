package Entity;//hello world

public class Guest {
	private String guestId;
	private String Name;
	private String phoneNumber;
	private boolean isMember;


	public Guest() {
		
	}
	
	public Guest(String guestId,String Name,String phoneNumber, boolean isMember) {
		this.guestId = guestId;
		this.Name = Name;
		this.phoneNumber = phoneNumber;
		this.isMember = isMember;
	}

	public String getGuestId() {
		return guestId;
	}

	public void setGuestId(String guestId) {
		this.guestId = guestId;
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