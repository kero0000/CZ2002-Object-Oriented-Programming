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
	private static String FILENAME = "Reservation.txt";
    ArrayList<Reservation> reservationList = new ArrayList<Reservation>();

    // Creating a new reservation
    public static void createReservation() throws IOException {

        String reservationNum;
        String guestFirstName;
        String guestLastName;
        String tableId; // or should this be table number? speak with i.c. of table class to see how we're tracking individual tables
        String reservationTime;
        String resDate = "";
        Date reservationDate;
        int pax;

        boolean checker1 = false;
        boolean checker2 = false; //don't need this, this would only be required if a secondary date requires checking. Only concerned w/ 1 date for restaurant table reservation. Can delete before committing.

        String tableRegExp = "[0][2-7][-][0][1-8]";
		Pattern tableIdPattern = Pattern.compile(tableRegExp);

		// To be used for data validation
		String digit = "\\d+";
		String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

		// dealing with entry of reservation date
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

        // entry of reservation time (should I change this to a switch implementation where the user just chooses an option and then based on that a time is selected? Will mean no need to check for invalid time since only valid times will be options?)
        
        System.out.println("Please choose a Reservation Time: ");

        System.out.println("---------- Lunch service ----------");
        System.out.println("(1)  11:00");
        System.out.println("(2)  11:30");
        System.out.println("(3)  12:00");
        System.out.println("(4)  12:30");
        System.out.println("(5)  13:00");
        System.out.println("(6)  13:30");
        System.out.println("(7)  14:00");
        System.out.println("(8)  14:30");
        System.out.println("---------- Dinner service ----------");
        System.out.println("(9)  18:00");
        System.out.println("(10) 18:30");
        System.out.println("(11) 19:00");
        System.out.println("(12) 19:30");
        System.out.println("(13) 20:00");
        System.out.println("(14) 20:30");
        System.out.println("(15) 21:00");
        System.out.println("(16) 21:30");

        int timeChoice = sc.nextInt();

        switch(timeChoice) {

            case 1:
                reservationTime = "11:00";
                break;

            case 2:
                reservationTime = "11:30";
                break;

            case 3:
                reservationTime = "12:00";
                break;

            case 4:
                reservationTime = "12:30";
                break;

            case 5:
                reservationTime = "13:00";
                break;

            case 6:
                reservationTime = "13:30";
                break;

            case 7:
                reservationTime = "14:00";
                break;
            
            case 8:
                reservationTime = "14:30";
                break;

            case 9:
                reservationTime = "18:00";
                break;

            case 10:
                reservationTime = "18:30";
                break;

            case 11:
                reservationTime = "19:00";
                break;

            case 12:
                reservationTime = "19:30";
                break;

            case 13:
                reservationTime = "20:00";
                break;

            case 14:
                reservationTime = "20:30";
                break;

            case 15:
                reservationTime = "21:00";
                break;

            case 16:
                reservationTime = "21:30";
                break;
        }


        /*
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

        do {
            System.out.println("Enter Reservation Time (hh:mm): ");

            try {
                reservationTime = sc.nextLine();
                reservationTime = reservationTime + ":00";
                Date resTime = sdf2.parse(reservationTime);

                if (resTime.before(todaysdate)) {
                    System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
                } else {
                    checker2 = true;
                }
            } catch (ParseException e) {
                System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
            }
        } while (!checker2);
        */

        // entry of table pax
        System.out.println("Please enter the number of pax: ");
        pax = sc.nextInt();

        // check whether a table for the requested number of pax is available for the particular date and time and assign accordingly. If not available, print error message informing no tables available.
        boolean tableAvailable = true;
        System.out.println("Checking table availability, please wait...");
        if (tableAvailable == false) {
            System.out.println("No table available for " + pax + " pax on " + reservationDate + "at " + reservationTime + ".");
        }

        // entry of guest particulars
        System.out.println("Please enter the guest's first name: ");
        guestFirstName = sc.nextLine();
        System.out.println("Please enter the guest's last name: ");
        guestLastName = sc.nextLine();
        
        Reservation r = new Reservation(//input parameters);
        		reservationList.add(r);
        		// ReservationDB rdb = new ReservationDB
        		//rdb.save('Reservation.txt', reservationList);
        // considering table availability, and assigned, assign reservation number and save the reservation to database

    }

    // Checking an existing reservation
    public static void checkReservation() {

    }

    // Updating an existing reservation
    public static void updateReservation() {
    
    }

    //delete reservation
    public static void deleteReservation() {

    }
}
