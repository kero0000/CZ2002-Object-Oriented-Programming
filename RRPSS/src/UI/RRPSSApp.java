package UI;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import Controller.GuestController;
import Controller.MenuController;
import Controller.OrderController;
import Controller.ReservationController;
import Controller.TableController;
import Database.ReservationDB;
import Entity.Reservation;

public class HRPSApp {

	public static void main(String[] args) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Date d = new Date();
		System.out.println(d);
		Timer timer = new Timer();
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 00);
        Date time = calendar.getTime();
        timer.schedule(new checkExpired(), time);
		MenuController.retrieveInstance().loadinDB();
		OrderController.getInstance().loadinDB();

		int main_menu_choice;
		Scanner sc = new Scanner(System.in);
		do {

			System.out.println("\n _    _ _____  _____   _____ ");
			System.out.println("| |  | |  __ \\|  __ \\ / ____|");
			System.out.println("| |__| | |__) | |__) | (___  ");
			System.out.println("|  __  |  _  /|  ___/ \\___ \\ ");
			System.out.println("| |  | | | \\ \\| |     ____) |");
			System.out.println("|_|  |_|_|  \\_\\_|    |_____/    v1.0");

			System.out.println("==================================================");
			System.out.println(" Please select one of the following functions: ");
			System.out.println("==================================================");
			System.out.println("(1) Restaurant Management\t\t(2) Administration");
			System.out.println("(3) Sales Revenue Report end of day\t(4) Save and exit");

			System.out.println("\nEnter your choice:");
			main_menu_choice = sc.nextInt();
			sc.nextLine();

			switch (main_menu_choice) {
			case 1:
				int restaurant_mgt_choice;
				do {
					System.out.println("\n==================================================");
					System.out.println(" Restaurant Management: ");
					System.out.println("==================================================");
					System.out.println("(1) Guest Details Management\t(2) Reservations");
					System.out.println("(3) Back");
					restaurant_mgt_choice = sc.nextInt();
					switch (restaurant_mgt_choice) {
					case 1:
						int guest_mgt_choice;
						do {
							System.out.println("\n==================================================");
							System.out.println(" Guest Details Management: ");
							System.out.println("==================================================");
							System.out.println("(1) Create Guest\t(2) Update Guest");
							System.out.println("(3) Remove Guest\t(4) View Guests");
							System.out.println("(5) Search Guest\t(6) Back");
							guest_mgt_choice = sc.nextInt();
							switch (guest_mgt_choice) {
							case 1:
								// Create new guest function
								GuestController.createGuest();
								break;
							case 2:
								GuestController.updateGuestById();
								break;
							case 3:
								GuestController.deleteGuestById();
								break;
							case 4:
								GuestController.retrieveAllGuest();
								break;
							case 5:
								GuestController.retrieveGuestbyId();
								break;
							case 6:
								guest_mgt_choice = 7;
								break;
							default:
								System.out.println("Please enter a valid option.");
								guest_mgt_choice = 0;
							}
						} while (guest_mgt_choice < 7);

						break;
					case 2:

						int reservation_choice;
						do {
							System.out.println("\n==================================================");
							System.out.println(" Reservation Management: ");
							System.out.println("==================================================");
							System.out.println("(1) Create Reservation\t\t(2) Update Reservation");
							System.out.println("(3) Remove Reservation\t\t(4) Print Reservation");
							System.out.println("(5) View All Reservations \t(6) Back");
							reservation_choice = sc.nextInt();
							switch (reservation_choice) {
							case 1:
								ReservationController.createReservation(); // function to create Reservation
								break;
							case 2:
								ReservationController.updateReservation(); // function to update Reservation
								break;
							case 3:
								System.out.println("Enter Reservation Number: ");
								String cancelReservation_id = sc.next();
								ReservationController.updateReservationToCancelled(cancelReservation_id);
								break;
							case 4:
								ReservationController.retrieveAllReservations();
								System.out.println("");
								System.out.println("Enter Reservation Number: ");
								String printReservation_id = sc.next();
								ReservationController.printReservation(printReservation_id);
								break;
							case 5:
								ReservationController.retrieveAllReservations();
								break;
							case 6:
								reservation_choice = 6;
								break;
							default:
								System.out.println("Please enter a valid option.");
								reservation_choice = 0;
							}
						} while (reservation_choice < 6);

						break;
					case 3:
						restaurant_mgt_choice = 4;
						break;
					default:
						System.out.println("Please enter a valid option.");
						restaurant_mgt_choice = 0;
					}
				} while (restaurant_mgt_choice < 4);

				break;
			case 2:
				int admin_choice;
				do {
					System.out.println("\n==================================================");
					System.out.println(" Administration: ");
					System.out.println("==================================================");
					System.out.println("(1) Table Management\t\t(2) Table Order");
					System.out.println("(3) Back");
					admin_choice = sc.nextInt();
					switch (admin_choice) {
					case 1:
						int table_mgt_choice;
						do {
							System.out.println("\n==================================================");
							System.out.println(" Table Management: ");
							System.out.println("==================================================");
							System.out.println("(1) Create Table\t\t\t(2) Update Table details");
							System.out.println("(3) Update Table Status\t\t(4) View All Table details");
							System.out.println("(5) View a Table detail\t\t(6) View All Table Status");
							System.out.println("(7) Back");
							table_mgt_choice = sc.nextInt();
							switch (table_mgt_choice) {
							case 1:
								// Create table
								TableController.retrieveAllTable();
								TableController.createTable();
								break;
							case 2:
								// Retrieve table and update by table id
								TableController.updateTable();
								break;
							case 3:
								// Retrieve table and update table status only by table id
								TableController.updateTableStatusOnly();
								break;
							case 4:
								// Retrieve and print all room details
								TableController.retrieveAllTable();
								break;
							case 5:

								TableController.retrieveOneTable();
								break;
							case 6:

								TableController.retrievetableStatus();
								break;
							case 7:
								table_mgt_choice = 8;
								break;

							default:
								System.out.println("Please enter a valid option.");
								table_mgt_choice = 0;
							}

						} while (table_mgt_choice < 8);

						break;
					case 2:

						int table_choice;
						do {
							System.out.println("\n==================================================");
							System.out.println(" Table Service: ");
							System.out.println("==================================================");
							System.out.println("(1) Table Order\t\t(2) Menu Management");
							System.out.println("(3) Back");
							table_choice = sc.nextInt();
							switch (table_choice) {
							case 1:
								OrderUI.getInstance().displayOptions();
								OrderController.getInstance().savetoDB();
								break;
							case 2:
								MenuUI.getInstance().displayOptions();
								MenuController.retrieveInstance().savetoDB();
								break;
							case 3:
								break;
							default:
								System.out.println("Please enter a valid option.");
								table_choice = 0;
							}

						} while (table_choice < 3);

						break;
					case 3:
						admin_choice = 4;
						break;
					default:
						System.out.println("Please enter a valid option.");
						admin_choice = 0;
					}
				} while (admin_choice < 4);
				break;
			
			case 3:
				// GENERATE SALES REVENUE REPORT
			
				break;
			case 4:
				System.out.println("Saving all data... Program terminating ..."); // Save "database into file"
				main_menu_choice = 7;
				break;
			default:
				System.out.println("Please enter a valid option.");
				main_menu_choice = 0;
			}
		} while (main_menu_choice < 7);
		MenuController.retrieveInstance().savetoDB();
		OrderController.getInstance().savetoDB();
		sc.close();
	}
	
	// this is to remove reservation after a certain period of time has passed
	static class checkExpired extends TimerTask {
        public void run() {

        	String fileName = "Reservation.txt";
        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    		Date today = new Date();
    		Date cid;
    		ArrayList<Reservation> reservationList = ReservationController.retrieveReservation();
    		for (int i = 0; i < reservationList.size(); i++) {
    			cid = reservationList.get(i).getDate();
    			if (cid.before(today) && reservationList.get(i).getStatus().equalsIgnoreCase("CONFIRMED")) {
    				reservationList.get(i).setStatus("EXPIRED");
    				// change table status to vacant
    				String tableId = reservationList.get(i).gettableId();
    				TableController.updateTableStatus(tableId, "VACANT");
    			}
    		}
    		// Write Reservation records to file
    		ReservationDB reservationDB = new ReservationDB();
    		try {
    			reservationDB.save(fileName, reservationList);
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			System.out.println("IOException > " + e.getMessage());
    		}
        }
	}
}