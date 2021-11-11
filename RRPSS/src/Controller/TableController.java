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