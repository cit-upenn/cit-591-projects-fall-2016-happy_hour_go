package search;

import util.FileReader;

public class Search {
	private FileReader fr;
	private String fileName;
	
	public Search (int dayOfWeek) {
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
}
