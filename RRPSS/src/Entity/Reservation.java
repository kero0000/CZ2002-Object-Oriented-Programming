package Entity;

import java.util.Date;

public class Reservation {
	private String reservationNum;
	private String guestId;
	private String tableId;
	private String status;
	private int pax;

	
	
	public Reservation(String reservationNum, String guestId, String tableId, String status, int numOfPax) {
		super();
		this.reservationNum = reservationNum;
		this.guestId = guestId;
		this.tableId = tableId;
		this.status = status;
		this.pax = numOfPax;
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

}