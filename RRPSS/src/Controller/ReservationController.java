package Controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.time.*;

import Database.ReservationDB;
import Entity.Reservation;

/**
 * Class containing the methods required to implement the functionalities related to reservations
 */

public class ReservationController {
    
    /**
     * Separator to divide each data variable
     */
    public static final String SEPARATOR = "|";

    /**
     * Filename to be accessed to store all Reservation data
     */
	private static String FILENAME = "Reservation.txt";

    private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
    private static int idCount = 1;
    
    /**
     * Restaurant opening time and closing time
     */
    private static final LocalTime OPENING_TIME = LocalTime.parse("11:00:00");
	private static final LocalTime CLOSING_TIME = LocalTime.parse("22:00:00");

    /**
     * Creating a new reservation
     * @throws IOException
     */
    public void createReservation() throws IOException {

        String reservationNum;
        String guestFirstName;
        String guestLastName;
        String tableId;
        String resTime = "";
        LocalTime reservationTime = null;
        String resDate = "";
        Date reservationDate = null; 
        int numOfPax;
        String status;

        boolean checker1 = false;
        boolean checker2 = false;

        String tableRegExp = "[0][2-7][-][0][1-8]";
		Pattern tableIdPattern = Pattern.compile(tableRegExp);

		// To be used for data validation
		String digit = "\\d+";
		String dateValidation = "^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([0-9][0-9])?[0-9][0-9]$";

		// Entry of reservation date
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
        
        // Entry of reservation time
        do {
            System.out.println("Enter Reservation Time (Opening hours: 11:00 - 22:00): ");

            resTime = sc.nextLine();
			resTime = resTime + ":00";
			reservationTime = LocalTime.parse(resTime);
			
			if (!reservationTime.isBefore(CLOSING_TIME) || !reservationTime.isAfter(OPENING_TIME)) {
			    System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
			} else {
			    checker2 = true;
			}
        } while (!checker2);
        

        // Entry of table pax
        System.out.println("Please enter the number of pax: ");
        numOfPax = sc.nextInt();
        while (numOfPax <= 0) {
            System.out.println("Invalid number of pax entered! ( Number of pax > 0)");
            numOfPax = sc.nextInt();
        }
        sc.nextLine();

        // Check whether a table for the requested number of pax is available for the particular date and time and assign accordingly. Update tables accordingly.
        System.out.println("Checking table availability, please wait...");
        tableId = TableController.checkTableAvailableForPax(numOfPax);
        if (tableId == "No table available") {
            System.out.println("No table available");

            return;
        }
        TableController.updateTableStatus(tableId, "RESERVED");

        // Entry of guest particulars
        System.out.println("Please enter the guest's first name: ");
        guestFirstName = sc.nextLine();
        System.out.println("Please enter the guest's last name: ");
        guestLastName = sc.nextLine();
        
        // Assigning value to status variable
        status = "Confirmed";

        // Assign reservation number
        reservationNum = String.valueOf(idCount);
        idCount++;

        // Save the newly created reservation to the database
        Reservation newReservation = new Reservation(reservationNum, reservationDate, reservationTime, numOfPax, guestFirstName, guestLastName, tableId, status);
        reservationList.add(newReservation);
        this.saveToDB();
        System.out.println("New reservation created!");

    }

	/**
     * Retrieves a reservation stored in the database
     * @param guestFirstName
     * @param guestLastName
     * @return Reservation
     */
    public Reservation retrieveReservationByName(String guestFirstName, String guestLastName) {

		ReservationDB reservationDB = new ReservationDB();

    	try {
			this.reservationList = (ArrayList<Reservation>) reservationDB.read(FILENAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        for (Reservation reservation : reservationList) {

            if ((reservation.getGuestFirstName().contentEquals(guestFirstName)) && (reservation.getGuestLastName().contentEquals(guestLastName))) {
                return reservation;
            }
        }

        System.out.println("No reservation exists for the given customer");
        return null;
    }

    /**
     * Updates an existing reservation
     * @param guestFirstName
     * @param guestLastName
     * @throws IOException
     */
    public void updateReservation(String guestFirstName, String guestLastName) throws IOException {

        Reservation toBeUpdated = retrieveReservationByName(guestFirstName, guestLastName);
        
        if(toBeUpdated == null) {
        	return;
        }
        
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
                	sc = new Scanner(System.in);
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
            	sc.nextLine();
                String newResTime = "";
                LocalTime newReservationTime = null;
                boolean checker2 = false;


                do {
                    System.out.println("Please enter New Reservation Time (hh:mm): ");
        
                    newResTime = sc.nextLine();
					newResTime = newResTime + ":00";
					newReservationTime = LocalTime.parse(newResTime);

					if (!newReservationTime.isBefore(CLOSING_TIME) || !newReservationTime.isAfter(OPENING_TIME)) {
					    System.out.println("Invalid time entered! Please enter a future time and please use the correct format, E.g. (20:00)");
					} else {
					    checker2 = true;
					}
                } while (!checker2);

                toBeUpdated.setReservationTime(newReservationTime);
                this.saveToDB();
                System.out.println("Reservation Time updated!");
                break;

            case 5:
                int newNumOfPax;
                String tableId,tableIdOld;
                
                System.out.println("Please enter the new number of pax for your reservation: ");
                newNumOfPax = sc.nextInt();
                
                System.out.println("Checking table availability, please wait...");
                tableId = TableController.checkTableAvailableForPax(newNumOfPax);
                if (tableId == "No table available") {
                    System.out.println("No table available");
                    break;
                }
                tableIdOld = toBeUpdated.getTableId();
                TableController.updateTableStatus(tableIdOld, "VACANT");
                TableController.updateTableStatus(tableId, "RESERVED");
                toBeUpdated.setTableId(tableId);
                toBeUpdated.setNumOfPax(newNumOfPax);              
                this.saveToDB();
                
                System.out.println("Number of pax updated for your reservation!");
                break;

        }
    
    }

    /**
     * Removes a reservation from the system that is cancelled
     * @param guestFirstName
     * @param guestLastName
     */
    public void deleteCancelledReservation(String guestFirstName, String guestLastName) {
    	
    	ReservationDB reservationDB = new ReservationDB();
    	
        try {
			this.reservationList = (ArrayList<Reservation>) reservationDB.read(FILENAME);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        Reservation toBeCancelled = retrieveReservationByName(guestFirstName, guestLastName);
        
        if(toBeCancelled == null) {
        	return;
        }

        reservationList.remove(toBeCancelled);

        try {
			reservationDB.save(FILENAME, reservationList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        TableController.updateTableStatus(toBeCancelled.getTableId(), "VACANT");
        
        System.out.println("Removed reservation");
    }

    /**
     * Load in database that stores reservation information
     */
    public void loadInDB() {
        ReservationDB reservationDB = new ReservationDB();

        try {
            this.reservationList = reservationDB.read(FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(reservationList.size()>=1)
        	idCount = Integer.valueOf(reservationList.get(reservationList.size()-1).getReservationNum())+1;
    }

    /**
     * Save to database that stores reservation information
     */
    public void saveToDB() {
        ReservationDB reservationDB = new ReservationDB();

        try {
            reservationDB.save(FILENAME, reservationList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
