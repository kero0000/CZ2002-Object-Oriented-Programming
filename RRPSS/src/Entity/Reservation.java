package Entity;

import java.util.Date;
import java.time.*;

/**
 * Class that represents a reservation made at the restaurant
 */
public class Reservation {
	
	/**
	 * A number that serves as the index number for reservations
	 */
	private String reservationNum;

	/**
	 * Date object for the date of the reservation
	 */
	private Date reservationDate;

	/**
	 * LocalTime object for the time of the reservation
	 */
	private LocalTime reservationTime;

	/**
	 * The first name of the guest to whom the reservation belongs
	 */
	private String guestFirstName;

	/**
	 * The last name of the guest to whom the reservation belongs
	 */
	private String guestLastName;

	/**
	 * The table id of the table reserved
	 */
	private String tableId;

	/**
	 * The status of the reservation (CONFIRMED)
	 */
	private String status;

	/**
	 * The number of pax the reservation is for
	 */
	private int numOfPax;

	
	/**
	 * Reservation constructor
	 * @param reservationNum
	 * @param reservationDate
	 * @param reservationTime
	 * @param numOfPax
	 * @param guestFirstName
	 * @param guestLastName
	 * @param tableId
	 * @param status
	 */
	public Reservation(String reservationNum, Date reservationDate, LocalTime reservationTime, int numOfPax, String guestFirstName, String guestLastName, String tableId, String status) {
		
		this.reservationNum = reservationNum;
		this.guestFirstName = guestFirstName;
		this.guestLastName = guestLastName;
		this.tableId = tableId;
		this.status = status;
		this.numOfPax = numOfPax;
		this.reservationDate = reservationDate;
		this.reservationTime = reservationTime;
	}

	/*
	public Reservation() {
		super();
	}*/

	/**
	 * Get the reservation number of a reservation
	 * @return reservationNum
	 */
	public String getReservationNum() {
		return reservationNum;
	}

	/**
	 * Set the reservationNumber of a reservation
	 * @param reservationNum
	 */
	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}

	/**
	 * Get the first name of the guest whom the reservation belongs to
	 * @return guestFirstName
	 */
	public String getGuestFirstName() {
		return this.guestFirstName;
	}

	/**
	 * Set the first name of the guest whom the reservation belongs to
	 * @param guestFirstName
	 */
	public void setGuestFirstName(String guestFirstName) {
		this.guestFirstName = guestFirstName;
	}

	/**
	 * Get the last name of the guest whom the reservation belongs to
	 * @return guestLastName
	 */
	public String getGuestLastName() {
		return this.guestLastName;
	}

	/**
	 * Set the last name of the guest whom the reservation belongs to
	 * @param guestLastName
	 */
	public void setGuestLastName(String guestLastName) {
		this.guestLastName = guestLastName;
	}

	/**
	 * Get the table id of the reserved table
	 * @return tableId
	 */
	public String getTableId() {
		return tableId;
	}

	/**
	 * Set the table id of the reserved table
	 * @param tableId
	 */
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}

	/**
	 * Get the status of the reservation
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set the status of the reservation
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the number of pax that the reservation is for
	 * @return numOfPax
	 */
	public int getNumOfPax() {
		return this.numOfPax;
	}

	/**
	 * Set the number of pax that the reservation is for
	 * @param numOfPax
	 */
	public void setNumOfPax(int numOfPax) {
		this.numOfPax = numOfPax;
	}
	
	/**
	 * Get the date of the reservation
	 * @return reservationDate
	 */
	public Date getReservationDate() {
		return this.reservationDate;
	}
	
	/**
	 * Set the date of the reservation
	 * @param reservationDate
	 */
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}

	/**
	 * Get the time of the reservation
	 * @return reservationTime
	 */
	public LocalTime getReservationTime() {
		return this.reservationTime;
	}

	/**
	 * Set the time of the reservation
	 * @param reservationTime
	 */
	public void setReservationTime(LocalTime reservationTime) {
		this.reservationTime = reservationTime;
	}

}