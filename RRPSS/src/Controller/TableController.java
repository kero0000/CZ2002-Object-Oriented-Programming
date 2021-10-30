package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Database.ReadinFile;
import Database.TableDB;
import Entity.Table;


public class TableController {
	
	public static final String SEPARATOR = "|";
	private static final String FILENAME = "Table.txt";

	public static void updateTable() throws IOException {
		Scanner sc = new Scanner(System.in);
		String tableType;
		String tableStatus;

		int option = 0;
		// To be used for data validation
		
		String regExp = "[0-9]+([.][0-9]{2})";
		String tableRegExp = "[0][2-7]";
		
		Pattern pattern = Pattern.compile(regExp);
		Pattern tableIdPattern = Pattern.compile(tableRegExp);
		
		retrieveAllTable(); // print out all the current tables and attributes
		System.out.println("");
		Table updateTable = new Table();
		updateTable = retrieveTableDetails(); // assign a current table to variable updateTable
		System.out.println("\nPlease choose Table details to update \n(1) Table Type \n"
				+ "(2) Table Status \n(3) All details");
		option = sc.nextInt();
		sc.nextLine();
		
		switch(option) {
			case 1:
				// Table Type
				do {
					System.out.println("Please enter a new Table Type:");
					System.out.println("(1) 2 pax");
					System.out.println("(2) 4 pax");
					System.out.println("(3) 6 pax");
					System.out.println("(4) 8 pax");
					System.out.println("(5) 10 pax");
					tableType = sc.nextLine();
	
					if (!tableType.equals("1") && !tableType.equals("2") && !tableType.equals("3") && !tableType.equals("4")&& !tableType.equals("5")) {
						System.out.println("Please select a valid option.");
					} else {
						switch (tableType) {
							case "1":
								updateTable.settableType("2 pax");
								break;
							case "2":
								updateTable.settableType("4 pax");
								break;
							case "3":
								updateTable.settableType("6 pax");
								break;
							case "4":
								updateTable.settableType("8 pax");
								break;
							case "5":
								updateTable.settableType("10 pax");
								break;
						}
					}
				} while (!tableType.equals("1") && !tableType.equals("2")&& !tableType.equals("3") && !tableType.equals("4") && !tableType.equals("5"));
				break;
				
			case 2:
				// Room Status
				do {
					System.out.println("Please enter Table Status: ");
					System.out.println("(1) Vacant");
					System.out.println("(2) Reserved");
					System.out.println("(3) Occupied");
					tableStatus = sc.nextLine();
	
					if (!tableStatus.equals("1") && !tableStatus.equals("2") && !tableStatus.equals("3")) {
						System.out.println("Please select a valid option.");
					} else {
						switch (tableStatus) {
							case "1":
								updateTable.settableStatus("VACANT");
								break;
							case "2":
								updateTable.settableStatus("RESERVED");
								break;
							case "3":
								updateTable.settableStatus("OCCUPIED");
								break;
						}
					}
				} while (!tableStatus.equals("1") && !tableStatus.equals("2") && !tableStatus.equals("3"));
				break;
			case 3:	
				// Table Type
				do {
					System.out.println("Please enter a new Table Type:");
					System.out.println("(1) 2 pax");
					System.out.println("(2) 4 pax");
					System.out.println("(3) 6 pax");
					System.out.println("(4) 8 pax");
					System.out.println("(5) 10 pax");
					tableType = sc.nextLine();
	
					if (!tableType.equals("1") && !tableType.equals("2") && !tableType.equals("3") && !tableType.equals("4")&& !tableType.equals("5")) {
						System.out.println("Please select a valid option.");
					} else {
						switch (tableType) {
							case "1":
								updateTable.settableType("2 pax");
								break;
							case "2":
								updateTable.settableType("4 pax");
								break;
							case "3":
								updateTable.settableType("6 pax");
								break;
							case "4":
								updateTable.settableType("8 pax");
								break;
							case "5":
								updateTable.settableType("10 pax");
								break;
						}
					}
				} while (!tableType.equals("1") && !tableType.equals("2")&& !tableType.equals("3") && !tableType.equals("4") && !tableType.equals("5"));


				do {
					System.out.println("Please enter Table Status: ");
					System.out.println("(1) Vacant");
					System.out.println("(2) Reserved");
					System.out.println("(3) Occupied");
					tableStatus = sc.nextLine();
	
					if (!tableStatus.equals("1") && !tableStatus.equals("2") && !tableStatus.equals("3")) {
						System.out.println("Please select a valid option.");
					} else {
						switch (tableStatus) {
							case "1":
								updateTable.settableStatus("VACANT");
								break;
							case "2":
								updateTable.settableStatus("RESERVED");
								break;
							case "3":
								updateTable.settableStatus("OCCUPIED");
								break;
						}
					}
				} while (!tableStatus.equals("1") && !tableStatus.equals("2") && !tableStatus.equals("3"));
				
		}
		
		try{
			ArrayList alr = retrieveTable(); // alr is the arraylist of all table records
			for (int i = 0; i < alr.size(); i++) {
				Table searchTable = (Table) alr.get(i); // searchTable is an instance of each table in record so can compare below

				if(updateTable.gettableId().equals(searchTable.gettableId())) {// replace the desired table record with the updateTable 
					alr.set(i, updateTable);
				}
			}
			// Write Table records to file
			TableDB tableDB = new TableDB();
			tableDB.save(FILENAME, alr);

			System.out.println("Table details has been successfully updated!");
		} catch (

		IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	/**
	 * Update Table Status only by using tableId
	 * @throws IOException 
	 * 
	 */
	public static void updateTableStatusOnly() throws IOException {
		String newStatus;
		String tableId;
		TableController.retrieveTableStatus();
		Scanner sc = new Scanner(System.in);
		Table table = new Table();
		Table checkTableId = new Table();
		Boolean checker = false;
		
		String digit = "\\d+";
		String alpha = "[a-zA-Z.*\\s+.]+";
		
		do {
			System.out.println("Please enter a Table Id for updating(E.g 02):");
			tableId = sc.nextLine();
			
			table.settableId(tableId);
			checkTableId = retrieveTable(table);
			if(checkTableId == null) {
				System.out.println("Table Id does not exist.");
			}
			
		} while (checkTableId != null && checker == true);
		
		do {
			System.out.println("Please enter Table Status: ");
			System.out.println("(1) Vacant");
			System.out.println("(2) Reserved");
			System.out.println("(3) Occupied");
			newStatus = sc.nextLine();

			if (!newStatus.equals("1") && !newStatus.equals("2") && !newStatus.equals("3")) {
				System.out.println("Please select a valid option.");
			} else {
				switch (newStatus) {
					case "1":
						checkTableId.settableStatus("VACANT");
						break;
					case "2":
						checkTableId.settableStatus("RESERVED");
						break;
					case "3":
						checkTableId.settableStatus("OCCUPIED");
						break;
				}
			}
		} while (newStatus.equals("") || !newStatus.matches(digit));
		
		try{
			ArrayList alr = retrieveTable(); // alr is list of all current tables
			for (int i = 0; i < alr.size(); i++) {
				Table searchTable = (Table) alr.get(i);
				if(checkTableId.gettableId().equals(searchTable.gettableId())) {
					alr.set(i, checkTableId);
				}
			}
			// Write table records to file
			TableDB tableDB = tableDB = new TableDB();
			tableDB.save(FILENAME, alr);

			System.out.println("Table Status has been successfully updated!");
		} catch (

		IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
	}
	/**
	 * Update table Status only by using tableId
	 * @throws IOException
	 * @param tableId
	 * 				Specifies the tableId
	 * @param status
	 * 				Specifies the status of the table
	 * @return Boolean value
	 */
	public static Boolean updateTableStatus(String tableId, String status) {
		Table table = new Table();
		Table checkTableId = new Table();
		Boolean checker = false;
		
		do {
			table.settableId(tableId);
			checkTableId = retrieveTable(table);
			if(checkTableId == null) {
				System.out.println("Table ID does not exist.");
			}
			
		} while (checkTableId != null && checker == true);
		
		
		try{
			ArrayList alr = retrieveTable();
			for (int i = 0; i < alr.size(); i++) {
				Table searchTable = (Table) alr.get(i);
				if(checkTableId.gettableId().equals(searchTable.gettableId())) {
						checkTableId.settableStatus(status);
						alr.set(i, checkTableId);
				}
			}
			// Write table records to file
			TableDB tableDB = new TableDB();
			tableDB.save(FILENAME, alr);
			checker = true;
			System.out.println("Table Status has been successfully updated!");
		} catch (

		IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
		return checker;
	}
	
	/**
	 * Retrieval of all table details
	 * @throws IOException
	 */
	public static void retrieveAllTable() throws IOException {

		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);

		System.out.println("\n==================================================");
		System.out.println(" Table Details ");
		System.out.println("==================================================");
		System.out.printf("%-8s %-13s %-19s", "tableID", "Table Type", "Table Status");
	    System.out.println();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();
	
			System.out.printf("%-8s %-13s %-19s", tableId, tableType, tableStatus);
			System.out.println("");
		}
	}
	
	/**
	 * Retrieval of specific table's details.
	 * @throws IOException
	 * @param room
	 *            Parameter to search for table details.
	 *            
	 * @return Table if found else return null.
	 */
	public static Table retrieveTable(Table table) {
		ArrayList alr = retrieveTable();
		for (int i = 0; i < alr.size(); i++) {
			Table searchTable = (Table) alr.get(i);

			if (table.gettableId().equals(searchTable.gettableId())) {
				table = searchTable;
				return table;
			}
		}
		return null;
	}
	
	/**
	 * Retrieval of table details.
	 * 
	 * @return ArrayList of all table.
	 */
	public static ArrayList retrieveTable() {
		ArrayList alr = null;
		try {
			// read file containing Table records
			TableDB tableDB = new TableDB();
			alr = tableDB.read(FILENAME);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return alr;
	}

	/**
     * Retrieval of table's details by tableId.
     * 
     * @return table details.
     */
    public static Table retrieveTableDetails() {
        String tableId;
        Scanner sc = new Scanner(System.in);
        ArrayList alr = retrieveTable();
        Table table = null;
        do {
            System.out.println("Please enter Table Id (E.g 01-05): ");
            tableId = sc.nextLine();

            for (int i = 0; i < alr.size(); i++) {
                Table searchTable = (Table) alr.get(i);

                if (searchTable.gettableId().equalsIgnoreCase(tableId)) {
                    table = searchTable;
                }
            }

            if (table == null) {
                System.out.println("Table Id does not exist.");
            }

        } while (table == null);

        return table;
    }
	
	/**
	 * Retrieval of table details
	 * @throws IOException
	 */
	public static void retrieveOneTable() throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		Scanner sc = new Scanner(System.in);
		String checkTableId;
		System.out.print("Enter the Table Id: ");
		checkTableId = sc.nextLine();
		
		System.out.println("\n==================================================");
		System.out.println(" Table Details ");
		System.out.println("==================================================");
		System.out.printf("%-8s %-13s %-19s", "tableId", "tableType", "tableStatus");
	    System.out.println();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();
			
			if(tableId.contains(checkTableId)) {
				System.out.printf("%-8s %-13s %-19s ", tableId, tableType, tableStatus);
				System.out.println("");
			}
		}
	}
	/**
	 * Retrieval of room type by using roomId
	 * @throws IOException
	 * @param id
	 *            Parameter to search for room details and retrieve room type
	 * @return type
	 */
	public static String retrieveTableType(String id) throws IOException {
		String type = null;
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String roomId = star.nextToken().trim();
			String roomType = star.nextToken().trim();

			if(roomId.contains(id)) {
				type = roomType;
			}
		}
		return type;
	}

	/**
	 * Retrieval of table details by using tableId
	 * @throws IOException
	 * @param checkTableId
	 *            Parameter to search for table details.
	 */
	public static void retrieveOneTable(String checkTableId) throws IOException {

		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		
		System.out.println("\n==================================================");
		System.out.println(" Table Details ");
		System.out.println("==================================================");
		System.out.printf("%-8s %-13s %-19s", "tableId", "tableType","tableStatus");
	    System.out.println();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			Table table = new Table();
			table.settableId(star.nextToken().trim());
			table.settableType(star.nextToken().trim());
			table.settableStatus(star.nextToken().trim());


			
			if(table.gettableId().contains(checkTableId)) {
				System.out.printf("%-8s %-13s %-19s", table.gettableId(), table.gettableType(), table.gettableStatus());
				System.out.println("");
			}
		}
	}
	
	/**
	 * Retrieval of all table's status
	 * @throws IOException
	 * 
	 */
	public static void retrieveTableStatus() throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		String vacantString = "";
		String occupiedString = "";
		String reservedString = "";
		int countVacant = 0;
		int countOccupied = 0;
		int countReserved = 0;
		System.out.println("\n==================================================");
		System.out.println(" All Table Status ");
		System.out.println("==================================================");
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();

			if(tableStatus.contentEquals("VACANT")) {
				if(vacantString.equals("")) {
					vacantString = tableId;
				}else {
					vacantString = vacantString + ", " + tableId;
				}
				countVacant += 1;
			}else if(tableStatus.contentEquals("OCCUPIED")) {
				if(occupiedString.equals("")) {
					occupiedString = tableId;
				}else {
					occupiedString = occupiedString + ", " + tableId;
				}
				countOccupied += 1;
			}else if(tableStatus.contentEquals("RESERVED")) {
				if(reservedString.equals("")) {
					reservedString = tableId;
				}else {
					reservedString = reservedString + ", " + tableId;
				}
				countReserved += 1;
			}
		}
		
		System.out.println("\nVacant(" + countVacant + "): ");
		System.out.println("\t\tRooms: " + vacantString);
		System.out.println("\nOccupied(" + countOccupied + "): ");
		System.out.println("\t\tRooms: " + occupiedString);
		System.out.println("\nReserved(" + countReserved + "): ");
		System.out.println("\t\tRooms: " + reservedString);
		System.out.println("");
	}
	
	/**
	 * Formatting of table id into readable format
	 * @param tableList
	 * 				An ArrayList of Table to search
	 * @param tableType
	 * 				String table type used to search for table details
	 */
	public static void formatPrintTables(ArrayList<Table> tableList, String tableType) {
		System.out.print("\t\ttable: ");
		ArrayList<String> tableTypeList = new ArrayList<String>();
		for(int i = 0; i < tableList.size(); i++) {
			if(tableList.get(i).gettableType().equals(tableType)) {
				tableTypeList.add(tableList.get(i).gettableId());
			}
		}
		String toPrint = String.join(", ", tableTypeList);
		System.out.println(toPrint);
	}


	public static ArrayList<Table> retrieveTableIdByType(String inputTableType) throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		ArrayList<Table> tableIdList = new ArrayList<Table>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			Table table = new Table();
			table.settableId(star.nextToken().trim());
			table.settableType(star.nextToken().trim());
			table.settableStatus(star.nextToken().trim());

			
			if(table.gettableType().equals(inputTableType)) {
				tableIdList.add(table);
			}
		}
		
		return tableIdList;
	}
	
	/**
	 * Retrieve a list of table ids by table type
	 * @throws IOException
	 * 
	 * @param inputRoomType
	 * 					String input of room type used to search for room
	 * 
	 * @return An array of Room
	 * 					
	 */
	public static ArrayList<Table> retrieveTableIdByTableType(String inputTableType) throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		ArrayList<Table> tableIdList = new ArrayList<Table>();

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","

			Table table = new Table();
			table.settableId(star.nextToken().trim());
			table.settableType(star.nextToken().trim());
			table.settableStatus(star.nextToken().trim());
			
			if(table.gettableType().equals(inputTableType)) {
				tableIdList.add(table);
			}
		}
		
		return tableIdList;
	}

	/**
	 * Retrieve room status by room id
	 * 
	 * @param retrieveRoomId
	 * 					String input of room id to retrieve room details
	 * 
	 * @return A String of room status
	 * 					
	 */
	public static String retrieveTableStatus(String retrieveTableId) {

		Table table = new Table();
		Table checkTableId = new Table();
		
		table.settableId(retrieveTableId);
		checkTableId = retrieveTable(table);
		if(checkTableId == null) {
			System.out.println("Table Id does not exist.");
			return null;
		}
		
		return checkTableId.gettableStatus();
	}
	
	/**
	 * Retrieve and calculate the number of available room types
	 * @throws IOException
	 * 
	 * @return An ArrayList of string
	 */
	public static ArrayList<String> retrieveAllAvailableTableTypes() throws IOException {
		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);
		ArrayList<Table> tableList = new ArrayList<Table>();
		ArrayList<String> vacantList = new ArrayList<String>();
		
		String vacantString = "";
		int vacant2pax = 0;
		int vacant4pax = 0;
		int vacant6pax = 0;
		int vacant8pax = 0;
		int vacant10pax = 0;
		int pax2Total = 0;
		int pax4Total = 0;
		int pax6Total = 0;
		int pax8Total = 0;
		int pax10Total = 0;

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			Table table = new Table();
			table.settableId(star.nextToken().trim());
			table.settableType(star.nextToken().trim());
			table.settableStatus(star.nextToken().trim());
			
			if(table.gettableType().equals("2 pax")) {
				pax2Total += 1;
				if(table.gettableStatus().contentEquals("VACANT")) {
					vacant2pax += 1;
				}
			}
			if(table.gettableType().equals("4 pax")) {
				pax4Total += 1;
				if(table.gettableStatus().contentEquals("VACANT")) {
					vacant4pax += 1;
				}
			}
			if(table.gettableType().equals("6 pax")) {
				pax6Total += 1;
				if(table.gettableStatus().contentEquals("VACANT")) {
					vacant6pax += 1;
				}
			}
			if(table.gettableType().equals("8 pax")) {
				pax8Total += 1;
				if(table.gettableStatus().contentEquals("VACANT")) {
					vacant8pax += 1;
				}
			}
			if(table.gettableType().equals("10 pax")) {
				pax10Total += 1;
				if(table.gettableStatus().contentEquals("VACANT")) {
					vacant10pax += 1;
				}
			}
		}
		vacantList.add(String.valueOf(vacant2pax));
		vacantList.add(String.valueOf(vacant4pax));
		vacantList.add(String.valueOf(vacant6pax));
		vacantList.add(String.valueOf(vacant8pax));
		vacantList.add(String.valueOf(vacant10pax));
		return vacantList;
	}
	checkTableAvailable(int pax){
		//test
	}
	
}