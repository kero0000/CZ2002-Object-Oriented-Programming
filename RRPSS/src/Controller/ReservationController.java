package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Database.ReadinFile;
import Database.ReservationDB;
import Entity.Reservation;

public class ReservationController {
    
    public static final String SEPARATOR = "|";
	private static String fileName = "Reservation.txt";

    public static void createReservation() throws IOException {

        String reservationNum;
        String guestFirstName;
        String guestLastName;
        String tableId; // or should this be table number? speak with i.c. of table class to see how we're tracking individual tables
        String reservationTime;
        String resDate = "";
        Date reservationDate;
        int numOfAdults;
        int numOfChildren;
        boolean checker1 = false;
        boolean checker2 = false;

        String roomRegExp = "[0][2-7][-][0][1-8]";
		Pattern roomIdPattern = Pattern.compile(roomRegExp);

		// To be used for data validation
		String digit = "\\d+";
		String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date todaysdate = new Date();

        do {
            System.out.println("Enter Reservation Date (dd/mm/yyyy):");
            try {
                resDate = sc.nextLine();
                reservationDate = sdf.parse(resDate);

                if (reservationDate.before(todaysdate) || !resDate.matches(dateValidation)) {
                    System.out.println("Invalid date entered! Please enter a future date and please use the correct format, E.g. (24/12/2021)");
                } else {
                    checker1 = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid date entered! Please enter a future date and please use the correct format, E.g. (24/12/2021)");
            }
        } while (!checker1 || !resDate.matches(dateValidation));

        

    }
    // update reservation
    //delete reservation
}
