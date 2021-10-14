package Entity;

import java.util.Date;

public class Reservation {
	private String reservationNum;
	private Date date;
	private String time;
	private String customerId;
	private String tableId;
	private String status;
	private int pax;

	
	
	public Reservation(String reservationNum, String customerId, String tableId, String status, int numOfPax, Date date, String time) {
		super();
		this.reservationNum = reservationNum;
		this.customerId = customerId;
		this.tableId = tableId;
		this.status = status;
		this.pax = numOfPax;
		this.date = date;
		this.time = time;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String gettableId() {
		return tableId;
	}
	public void settableId(String tableId) {
		this.tableId = tableId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getNumOfPax() {
		return this.pax;
	}
	public void setNumOfPax(int pax) {
		this.pax = pax;
	}
	
	public Date getDate() {
		return this.date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}