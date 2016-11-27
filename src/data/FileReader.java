package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads an html file as a single string, splits it by "barItem", and stores the pieces in an ArrayList of String, bars.
 * @author Han Zhu
 *
 */
public class FileReader {
	private String outPage;
//	read a webpage as a single string.
	private ArrayList<String> bars;
	private String fileName;
	
	public FileReader(String fileName) throws FileNotFoundException {
		outPage = "";
		bars = new ArrayList<String>();
		this.fileName = fileName;
		readFile();
		splitBars();
	}
	
	/**
	 * Read a file line by line, append each line to a StringBuilder.
	 * @throws FileNotFoundException
	 */
	private void readFile() throws FileNotFoundException {
		File webpage = new File(fileName);
		Scanner in = new Scanner(webpage);
		StringBuilder sb = new StringBuilder();
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			
			if (line.trim().equals("<br style=\"clear:both;\"/>")) {
				break;
			}
			sb.append(line.trim() + " ");
		}
		
		outPage = sb.toString();
		
		in.close();
	}
	
	/**
	 * Split the webpage by "barItem". Ignore the first piece.
	 */
	private void splitBars() {
		String[] splitted = outPage.split("barItem");
		
		for (int i = 1; i < splitted.length; i++) {
			bars.add(splitted[i]);
		}
	}

	public String getOutPage() {
		return outPage;
	}

	public ArrayList<String> getBars() {
		return bars;
	}
}
