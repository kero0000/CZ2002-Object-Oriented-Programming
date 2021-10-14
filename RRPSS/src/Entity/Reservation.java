package Entity;

import java.util.Date;

public class Reservation {
	private String reservationNum;
	private String date;
	private String time;
	private String guestId;
	private String tableId;
	private String status;
	private int pax;

	
	
	public Reservation(String reservationNum, String guestId, String tableId, String status, int numOfPax, String date, String time) {
		super();
		this.reservationNum = reservationNum;
		this.guestId = guestId;
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
	public String getGuestId() {
		return guestId;
	}
	public void setGuestId(String guestId) {
		this.guestId = guestId;
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
	
	public String getDate() {
		return this.date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time = time;
	}

}