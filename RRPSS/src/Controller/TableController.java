package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import Database.ReadinFile;
import Database.TableDB;
import Entity.Table;

/**
 * The class which contains all the methods required to implement functionalities related to tables.
 */
public class TableController {
	
	/**
	 * seperator to divide each data variable.
	 */
	public static final String SEPARATOR = "|";
	/**
	 * filename to be accessed.
	 */
	private static final String FILENAME = "Table.txt";
	/**
	 * updates specific table's status.
	 * @param tableId the tableId of table that needs to be updated
	 * @param status the status to be updated (VACANT/RESERVED/OCCUPIED)
	 * @return checker
	 */
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
		} catch (

		IOException e){
			System.out.println("IOException > " + e.getMessage());
		}
		return checker;
	}
	
	/**
	 * retrieves all the tables in the table txt file
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
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();
	
			System.out.printf("%-8s %-13s %-19s", tableId, tableType, tableStatus);
			System.out.println("");
		}
	}
	/**
	 * retrieve a specific table instance.
	 * @param table is the table object to retrieve.
	 * @return table to be found
	 */
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
	
	/**
	 * retrieve an array of table objects from table txt.
	 * @return arraylist of all the tables
	 */
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
	
	/**
	 * checks whether any table available for a specific number of people
	 * @param pax is number of people who wants to reserve the table
	 * @return string of tableId that is vacant and can accomodate the number of people.
	 */
	public static String checkTableAvailableForPax(int pax) {
		ArrayList alr = null;
		alr = retrieveTableArrayList();
		int i;
		for(i=0;i<alr.size();i++) {
			Table tempTable = (Table) alr.get(i);
			try{
	            int tablepax = Integer.parseInt(tempTable.gettableType().substring(0, 2).trim());
	            if(pax <= tablepax && tempTable.gettableStatus().contentEquals("VACANT")) {
	            	String tableid = tempTable.gettableId();
					return tableid;
				}}
	        
	        catch (NumberFormatException ex){
	            ex.printStackTrace();
	        }
			
		}
		return("No table available");
	}
}