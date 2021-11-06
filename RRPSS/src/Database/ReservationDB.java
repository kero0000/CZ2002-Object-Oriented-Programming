package Database;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.time.*;

import Entity.Reservation;


public class ReservationDB implements DB {
	
	public static final String SEPARATOR = "|";

	@Override
	public ArrayList<Reservation> read(String fileName) throws IOException {
		ArrayList stringArray = (ArrayList<String>) ReadinFile.read(fileName);
		ArrayList<Reservation> reservationList = new ArrayList();// to store Reservation data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");
		
		for (int i = 0; i < stringArray.size(); i++) {

			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
			

			String reservationNum = star.nextToken().trim();
			String resDate = star.nextToken().trim();
			String resTime = star.nextToken().trim();
			String pax = star.nextToken().trim();
			String guestFirstName = star.nextToken().trim();
			String guestLastName = star.nextToken().trim();
			String tableId = star.nextToken().trim();
			String status = star.nextToken().trim();
			
			Date reservationDate = null;
			try {
				reservationDate = sdf.parse(resDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}// using delimiter ","

			//Date reservationTime = null;
			LocalTime reservationTime = null;
			/*try {
				//reservationTime = sdf2.parse(resTime);
			} catch (ParseException e) {
				e.printStackTrace();
			}*/

			reservationTime = LocalTime.parse(resTime);

			int numOfPax = Integer.valueOf(pax);
			
			// create  object from file data
			Reservation reservationEntry = new Reservation(reservationNum, reservationDate, reservationTime, numOfPax, guestFirstName, guestLastName, tableId, status);
			reservationList.add(reservationEntry);
		}

		return reservationList;
		
	}
	
	@Override
	public void save(String filename, List reservationList) throws IOException {
		ArrayList<String> reservationsw = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

		for (int i = 0; i < reservationList.size(); i++) {
			Reservation reservation = (Reservation) reservationList.get(i);
			StringBuilder st = new StringBuilder();
			st.append(reservation.getReservationNum().trim());
			st.append(SEPARATOR);
			st.append(sdf.format(reservation.getReservationDate()).trim());
			st.append(SEPARATOR);
			//st.append(LocalTime.parse(reservation.getReservationTime()).trim());
			st.append(reservation.getReservationTime().toString().trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(reservation.getNumOfPax()).trim());
			st.append(SEPARATOR);
			st.append(reservation.getGuestFirstName().trim());
			st.append(SEPARATOR);
			st.append(reservation.getGuestLastName().trim());
			st.append(SEPARATOR);
			st.append(reservation.getTableId());
			st.append(SEPARATOR);
			st.append(reservation.getStatus());
			st.append(SEPARATOR);

			reservationsw.add(st.toString());
		}
		
		ReadinFile.write(filename, reservationsw);
	}
}