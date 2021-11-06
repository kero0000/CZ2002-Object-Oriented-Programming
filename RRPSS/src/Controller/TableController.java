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
			ArrayList alr = retrieveTableArrayList(); 
			for (int i = 0; i < alr.size(); i++) {
				Table searchTable = (Table) alr.get(i); 

				if(updateTable.gettableId().equals(searchTable.gettableId())) {
					alr.set(i, updateTable);
				}
			}

			TableDB tableDB = new TableDB();
			tableDB.save(FILENAME, alr);

			System.out.println("Table details has been successfully updated!");
		} catch (

		IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	public static Boolean updateTableStatus(String tableId, String status) {
		Table table = new Table();
		Table checkTableId = new Table();
		Boolean checker = false;
		
		do {
			table.settableId(tableId);
			checkTableId = retrieveTableInstance(table);
			if(checkTableId == null) {
				System.out.println("Table ID does not exist.");
			}
			
		} while (checkTableId != null && checker == true);
		
		
		try{
			ArrayList alr = retrieveTableArrayList();
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

	public static void retrieveAllTable() throws IOException {

		ArrayList stringArray = (ArrayList) ReadinFile.read(FILENAME);

		System.out.println("\n==================================================");
		System.out.println(" Table Details ");
		System.out.println("==================================================");
		System.out.printf("%-8s %-13s %-19s", "tableID", "Table Type", "Table Status");
	    System.out.println();
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();
	
			System.out.printf("%-8s %-13s %-19s", tableId, tableType, tableStatus);
			System.out.println("");
		}
	}
	
	public static Table retrieveTableInstance(Table table) {
		ArrayList alr = retrieveTableArrayList();
		for (int i = 0; i < alr.size(); i++) {
			Table searchTable = (Table) alr.get(i);

			if (table.gettableId().equals(searchTable.gettableId())) {
				table = searchTable;
				return table;
			}
		}
		return null;
	}

	public static ArrayList retrieveTableArrayList() {
		ArrayList alr = null;
		try {
			TableDB tableDB = new TableDB();
			alr = tableDB.read(FILENAME);

		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return alr;
	}

    public static Table retrieveTableDetails() {
        String tableId;
        Scanner sc = new Scanner(System.in);
        ArrayList alr = retrieveTableArrayList();
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
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
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
	
	public static String checkTableAvailableForPax(int pax) {
		ArrayList alr = null;
		int tablepax;
		alr = retrieveTableArrayList();
		int i;
		for(i=0;i<alr.size();i++) {
			Table tempTable = (Table) alr.get(i);
			try{
                if(tempTable.gettableType().compareTo("9") > 0) {
                     tablepax = Integer.parseInt(tempTable.gettableType().substring(0,2));
                	  System.out.println("here");
                }
                else
                    tablepax = Integer.parseInt(tempTable.gettableType().substring(0,1));
                if(tablepax == pax && tempTable.gettableStatus().contentEquals("VACANT")) {
                    String tableid = tempTable.gettableId();
                    return tableid;
                }
            }
	        catch (NumberFormatException ex){
	            ex.printStackTrace();
	        }
			
		}
		return("No table available");
	}
}