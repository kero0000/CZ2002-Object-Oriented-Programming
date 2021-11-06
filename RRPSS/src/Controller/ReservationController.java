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
import java.time.*;

import Database.ReadinFile;
import Database.ReservationDB;
import Entity.Reservation;
import Controller.TableController;

public class ReservationController {
    
    public static final String SEPARATOR = "|";
	private static String FILENAME = "Reservation.txt";
    private static ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private static int idCount = 1;

    // Creating a new reservation
    public void createReservation() throws IOException {

        String reservationNum;
        String guestFirstName;
        String guestLastName;
        String tableId; // or should this be table number? speak with i.c. of table class to see how we're tracking individual tables
        String resTime = "";
        //Date reservationTime;
        //Date reservationTime = null; // trying this to avoid the error of "this variable hasn't been initialized" when creating Reservation object at the bottom of function
        LocalTime reservationTime = null;
        String resDate = "";
        //Date reservationDate;
        Date reservationDate = null; // trying this to avoid the error of "this variable hasn't been initialized" when creating Reservation object at the bottom of function
        int numOfPax;
        String status;

        boolean checker1 = false;
        boolean checker2 = false;

        String tableRegExp = "[0][2-7][-][0][1-8]";
		Pattern tableIdPattern = Pattern.compile(tableRegExp);

		// To be used for data validation
		String digit = "\\d+";
		String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

		// dealing with entry of reservation date
        Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date todaysdate = new Date();
        LocalTime currentTime = LocalTime.now();

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
        /*
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
        */

        
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");

        do {
            System.out.println("Enter Reservation Time (hh:mm): ");

            resTime = sc.nextLine();
			resTime = resTime + ":00";
			//reservationTime = sdf2.parse(resTime);
			reservationTime = LocalTime.parse(resTime);
			System.out.println(reservationTime);
			
			if (reservationTime.isBefore(currentTime)) {
			    System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
			} else {
			    checker2 = true;
			}
        } while (!checker2);
        

        // entry of table pax
        System.out.println("Please enter the number of pax: ");
        numOfPax = sc.nextInt();
        sc.nextLine();

        // check whether a table for the requested number of pax is available for the particular date and time and assign accordingly. If not available, print error message informing no tables available. If available, get tableId value
        //boolean tableAvailable = true;
        System.out.println("Checking table availability, please wait...");
        tableId = TableController.checkTableAvailableForPax(numOfPax);
        if (tableId == "No table available") {
            System.out.println("No table available");

            return;
        }
        TableController.updateTableStatus(tableId, "RESERVED");
        // entry of guest particulars
        System.out.println("Please enter the guest's first name: ");
        guestFirstName = sc.nextLine();
        System.out.println("Please enter the guest's last name: ");
        guestLastName = sc.nextLine();
        
        //assigning value to status variable
        status = "Confirmed";

        // assign reservation number (I hope this works in assigning unique id's to each new reservation object)
        reservationNum = String.valueOf(idCount);
        idCount++;

        //save the newly created reservation to the database
        Reservation newReservation = new Reservation(reservationNum, reservationDate, reservationTime, numOfPax, guestFirstName, guestLastName, tableId, status);
        reservationList.add(newReservation);
        ReservationDB reservationDB = new ReservationDB();
        reservationDB.save(FILENAME, reservationList);

        //sc.close();

    }

    // Retrieve reservation by either guest's first name or guest's last name
    public Reservation retrieveReservationByGuestFirstName(String guestFirstName) {

        for (Reservation reservation : reservationList) {

            if (reservation.getGuestFirstName() == guestFirstName) {
                return reservation;
            }
        }

        return null;

    }

    public Reservation retrieveReservationByGuestLastName(String guestLastName) {

        for (Reservation reservation : reservationList) {

            if (reservation.getGuestLastName() == guestLastName) {
                return reservation;
            }
        }

        return null;
    }

    // I've included this third function in case the former 2 functions dont give the correct reservation entry since there is a possibility that someone shares a first name, or a last name, but the odds of them sharing both are VERY slim (could make the above 2 functions redundant?)
    public Reservation retrieveReservationByName(String guestFirstName, String guestLastName) {

        for (Reservation reservation : reservationList) {

            if ((reservation.getGuestFirstName() == guestFirstName) && (reservation.getGuestLastName() == guestLastName)) {
                return reservation;
            }
        }

        System.out.println("No reservation exists for the given customer");
        return null;
    }

    // Updating an existing reservation
    public void updateReservation(String guestFirstName, String guestLastName) {

        Reservation toBeUpdated = retrieveReservationByName(guestFirstName, guestLastName);

        int option;
        Date todaysdate = new Date();
        Scanner sc = new Scanner(System.in);

        System.out.println("What would you like to update?");
        System.out.println("(1) Guest First Name");
        System.out.println("(2) Guest Last Name");
        System.out.println("(3) Reservation Date");
        System.out.println("(4) Reservation Time");
        System.out.println("(5) Number of Pax");
        
        option = sc.nextInt();

        switch(option) {

            case 1:
                String newFirstName;
                System.out.println("Please enter the new first name: ");
                newFirstName = sc.next();
                toBeUpdated.setGuestFirstName(newFirstName);
                this.saveToDB();
                System.out.println("Guest First Name updated!");
                break;

            case 2:
                String newLastName;
                System.out.println("Please enter the new last name: ");
                newLastName = sc.next();
                toBeUpdated.setGuestLastName(newLastName);
                this.saveToDB();
                System.out.println("Guest Last Name updated!");
                break;

            case 3:
                String newResDate = "";
                Date newReservationDate = null;
                //Date todaysdate = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";
                boolean checker1 = false;

                do {
                    System.out.println("Please enter New Reservation Date (dd/mm/yyyy):");
                    try {
                        newResDate = sc.nextLine();
                        newReservationDate = sdf.parse(newResDate);
        
                        if (newReservationDate.before(todaysdate) || !newResDate.matches(dateValidation)) {
                            System.out.println("Invalid date entered! Please enter a future date and please use the correct format, E.g. (24/12/2021)");
                        } else {
                            checker1 = true;
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid date entered! Please enter a future date and please use the correct format, E.g. (24/12/2021)");
                    }
                } while (!checker1 || !newResDate.matches(dateValidation));

                toBeUpdated.setReservationDate(newReservationDate);
                this.saveToDB();
                System.out.println("Reservation Date updated!");
                break;

            case 4:
                String newResTime = "";
                Date newReservationTime = null;
                SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
                //Date todaysdate = new Date();
                boolean checker2 = false;


                do {
                    System.out.println("Please enter New Reservation Time (hh:mm): ");
        
                    try {
                        newResTime = sc.nextLine();
                        newResTime = newResTime + ":00";
                        newReservationTime = sdf2.parse(newResTime);
        
                        if (newReservationTime.before(todaysdate)) {
                            System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
                        } else {
                            checker2 = true;
                        }
                    } catch (ParseException e) {
                        System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
                    }
                } while (!checker2);

                //toBeUpdated.setReservationTime(newReservationTime);
                this.saveToDB();
                System.out.println("Reservation Time updated!");
                break;

            case 5:
                int newNumOfPax;
                String tableId;

                System.out.println("Please enter the new number of pax for your reservation: ");
                newNumOfPax = sc.nextInt();

                System.out.println("Checking table availability, please wait...");
                tableId = TableController.checkTableAvailableForPax(newNumOfPax);
                if (tableId == "No table available") {
                    System.out.println("No table available");
                    break;
                }
                System.out.println("Number of pax updated for your reservation!");
                break;

        }
    
    }

    //delete a cancelled reservation
    public void deleteCancelledReservation(String guestFirstName, String guestLastName) {

        Reservation toBeCancelled = retrieveReservationByName(guestFirstName, guestLastName);

        for (Reservation reservation : reservationList) { // is all of this necessary to delete a reservation, like must I loop through the reservationList or can I straight away call .remove?

            if (reservation == toBeCancelled) {
                reservationList.remove(toBeCancelled);
                System.out.println("The reservation under " + guestFirstName + " " + guestLastName + " has been cancelled.");
            } else {
                System.out.println("The reservation could not be cancelled.");
            }
        }
    }

    public void deleteExpiredReservations() {

    }

    public void loadInDB() {
        ReservationDB reservationDB = new ReservationDB();

        try {
            this.reservationList = reservationDB.read(FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveToDB() {
        ReservationDB reservationDB = new ReservationDB();

        try {
            reservationDB.save(FILENAME, reservationList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
