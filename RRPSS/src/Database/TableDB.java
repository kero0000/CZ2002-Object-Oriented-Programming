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
		ArrayList alr = new ArrayList();
		for (int i = 0; i < stringArray.size(); i++) {

			String st = (String) stringArray.get(i);
	
			StringTokenizer star = new StringTokenizer(st, SEPARATOR); 
			String tableId = star.nextToken().trim();
			String tableType = star.nextToken().trim();
			String tableStatus = star.nextToken().trim();


			Table t = new Table(tableId, tableType,tableStatus);

			alr.add(t);
		}
		return alr;
	}

	@Override
	public void save(String filename, List al) throws IOException {
		List alw = new ArrayList();

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