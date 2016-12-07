package dataPrep;

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
	private String webpageString;
//	read a webpage as a single string.
	private ArrayList<String> lines;
	private String fileName;
	
	public FileReader(String fileName) {
		webpageString = "";
		lines = new ArrayList<String>();
		this.fileName = fileName;
	}
	
	/**
	 * Read a file line by line, append each line to a StringBuilder.
	 * @throws FileNotFoundException
	 */
	public void readFileWhole() throws FileNotFoundException {
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
		
		webpageString = sb.toString();
		
		in.close();
	}
	
	/**
	 * Read file line by line and store each line in an ArrayList.
	 * @throws FileNotFoundException
	 */
	public void readFile() throws FileNotFoundException {
		File inputFile = new File(fileName);
		Scanner in = new Scanner(inputFile);
		
		while (in.hasNextLine()) {
			String line = in.nextLine();
			lines.add(line);
		}
		in.close();
	}
	
	public String getFileName() {
		return fileName;
	}

	public String getWebpageString() {
		return webpageString;
	}

	public ArrayList<String> getLines() {
		return lines;
	}
}
