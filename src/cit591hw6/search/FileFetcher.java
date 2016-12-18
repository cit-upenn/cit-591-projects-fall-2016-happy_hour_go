package cit591hw6.search;

import cit591hw6.dataPrep.FileReader;
/**
 * Find the file to search based on user input current time 
 * @author Han Zhu, Jill Gao
 *
 */
public class FileFetcher {
	private FileReader fr;
	private String fileName;
	
	public FileFetcher (int dayOfWeek) {
		switch (dayOfWeek) {
		case 1:
			fileName = "data/clean/sunday.csv";
			break;
		case 2:
			fileName = "data/clean/monday.csv";
			break;
		case 3:
			fileName = "data/clean/tuesday.csv";
			break;
		case 4:
			fileName = "data/clean/wednesday.csv";
			break;
		case 5:
			fileName = "data/clean/thursday.csv";
			break;
		case 6:
			fileName = "data/clean/friday.csv";
			break;
		case 7:
			fileName = "data/clean/saturday.csv";
			break;
		default:
			System.out.println("Error: dayOfWeek not in range 1 - 7.");
			break;
		}
		fr = new FileReader(fileName);
	}
	/**
	 * This method gets the FileReader of selected day's file
	 * @return
	 */
	public FileReader getFile() {
		return fr;
	}

}
