package Database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Scanner;
import Entity.Table;

public class TableDB implements DB{
	public static final String SEPARATOR = "|";

	@Override
	public ArrayList read(String fileName) throws IOException {

		ArrayList stringArray = (ArrayList) ReadinFile.read(fileName);
		ArrayList alr = new ArrayList();// to store customer data

		for (int i = 0; i < stringArray.size(); i++) {

			String st = (String) stringArray.get(i);
			// get individual 'fields' of the string separated by SEPARATOR
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); // pass in the string to the string tokenizer
																		// using delimiter ","
			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();

			// create Guest object from file data
			Table t = new Table(tableId, tableType,tableStatus);
			// add to Guest list
			alr.add(t);
		}
		return alr;
	}

	@Override
	public void save(String filename, List al) throws IOException {
		// ArrayList<String> alw = new ArrayList<String>();
		List alw = new ArrayList();// to store Guest data

		for (int i = 0; i < al.size(); i++) {
			Table t = (Table) al.get(i);
			StringBuilder st = new StringBuilder();
			st.append(t.gettableId().trim());
			st.append(SEPARATOR);
			st.append(t.gettableType().trim());
			st.append(SEPARATOR);
			st.append(t.gettableStatus().trim().toUpperCase());
			st.append(SEPARATOR);
			alw.add(st.toString());
		}

		ReadinFile.write(filename, alw);
	}
}