package Boundary;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import Controller.TableController;
import Database.ReservationDB;
import Entity.Reservation;

public class expiredReservations extends TimerTask{
	@Override
	public void run() {
		ReservationDB reservationDB = new ReservationDB();
		String fileName = "Reservation.txt";
		ArrayList<Reservation> reservationList = null;
		Calendar c = Calendar.getInstance();
	    c.set(Calendar.HOUR_OF_DAY, 0); //anything 0 - 23
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    Date today = c.getTime(); //the midnight, that's the first second of the day.
	    Date tomorrow = new Date(today.getTime() + 1000 * 60 * 60 * 24);
		try {
			reservationList = reservationDB.read(fileName);
		}catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		
		if(reservationList.isEmpty())
			return;
		for(int i = 0; i < reservationList.size(); i++) {
			if (reservationList.get(i).getReservationDate().before(tomorrow) &&  reservationList.get(i).getReservationTime().plusMinutes(10).isBefore(LocalTime.now())) {		
				TableController.updateTableStatus(reservationList.get(i).getTableId(), "VACANT");
				reservationList.remove(i--);
        	}
		}
			
		try {
			reservationDB.save(fileName, reservationList);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	
	
		}
}
