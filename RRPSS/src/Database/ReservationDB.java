package Database;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import Entity.Reservation;


public class ReservationDB implements DB {
	
	public static final String SEPARATOR = "|";

	@Override
	public ArrayList read(String fileName) throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(fileName);
		ArrayList alr = new ArrayList();// to store Reservation data
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for (int i = 0; i < stringArray.size(); i++) {

			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
			

			String reservationNum = star.nextToken().trim();
			String guestId = star.nextToken().trim();
			String tableId = star.nextToken().trim();
			String date = star.nextToken().trim();
			String status = star.nextToken().trim();
			String pax_string = star.nextToken().trim();
			
			Date Date = null;
			try {
				Date = sdf.parse(date);
			} catch (ParseException e) {

				e.printStackTrace();
			}// using delimiter ","

			int pax = Integer.valueOf(pax_string);
			
			// create  object from file data
			Reservation r = new Reservation(reservationNum, guestId, tableId,  status, pax_string, Date,  time);
			alr.add(r);
		}
		return alr;
		
	}
	
	@Override
	public void save(String filename, List al) throws IOException {
		List alw = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		for (int i = 0; i < al.size(); i++) {
			Reservation r = (Reservation) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(r.getReservationNum().trim());
			st.append(SEPARATOR);
			st.append(r.getGuestId().trim());
			st.append(SEPARATOR);
			st.append(sdf.format(r.getDate()).trim());
			st.append(SEPARATOR);
			st.append(r.gettableId().trim());
			st.append(SEPARATOR);
			st.append(r.getStatus().trim());
			st.append(SEPARATOR);
			st.append(String.valueOf(r.getNumOfPax()).trim());
			st.append(SEPARATOR);
			alw.add(st.toString());
		}
		
		ReadinFile.write(filename, alw);
	}
}