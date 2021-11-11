package Boundary;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import Controller.TableController;
import Database.ReservationDB;
import Entity.Reservation;

public class expiredReservations extends TimerTask{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	ReservationDB reservationDB = new ReservationDB();
	String fileName = "Reservation.txt";
	ArrayList<Reservation> reservationList = null;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	Date today = new Date();
	try {
		reservationList = reservationDB.read(fileName);
	}
	catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("IOException > " + e.getMessage());
	}
	for (Reservation reservation : reservationList) {

        if (reservation.getReservationDate().before(today)) {
			reservation.setStatus("EXPIRED");
			TableController.updateTableStatus(reservation.getTableId(), "VACANT");
        	}
	}
	try {
		reservationDB.save(fileName, reservationList);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		System.out.println("IOException > " + e.getMessage());
	}


	}
}
