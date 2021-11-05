package Entity;

import java.util.Date;

public class Reservation {
	private String reservationNum;
	private Date reservationDate;
	//private String reservationTime;
	private Date reservationTime;
	private String guestFirstName;
	private String guestLastName;
	private String tableId;
	private String status;
	private int numOfPax;

	
	
	public Reservation(String reservationNum, Date reservationDate, Date reservationTime, int numOfPax, String guestFirstName, String guestLastName, String tableId, String status) {
		//super();
		this.reservationNum = reservationNum;
		this.guestFirstName = guestFirstName;
		this.guestLastName = guestLastName;
		this.tableId = tableId;
		this.status = status;
		this.numOfPax = numOfPax;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
	}

	
	public Reservation() {
		super();
	}

	public String getReservationNum() {
		return reservationNum;
	}

	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}

	public String getGuestFirstName() {
		return this.guestFirstName;
	}

	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}

	public String getGuestLastName() {
		return this.guestLastName;
	}

	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNumOfPax() {
		return this.numOfPax;
	}

	public void setNumOfPax(int numOfPax) {
		this.numOfPax = numOfPax;
	}
	
	public Date getReservationDate() {
		return this.reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getReservationTime() {
		return this.reservationTime;
	}
	public void setReservationTime(Date reservationTime) {
		this.reservationTime = reservationTime;
	}

}