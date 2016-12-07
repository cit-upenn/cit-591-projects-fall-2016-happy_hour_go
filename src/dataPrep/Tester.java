package dataPrep;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class writes the bar data into csv files. One files per weekday. 
 * @author Han Zhu
 *
 */
public class Tester {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter file name: ");
		
		Scanner in = new Scanner(System.in);
		String fileName = in.nextLine();
				
		try {
			FileReader fReader = new FileReader(fileName);
			fReader.readFileWhole();
			Parser parser = new Parser(fReader.getWebpageString());
			
			ArrayList<String> happyHours = parser.getHappyHours();
			
//				write output as data/clean/***day.csv
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream
					("data/clean/" + fileName.substring(9, fileName.length() - 4) + "csv")));
			
			Geocoder geocoder = new Geocoder();
			
			for (int i = 0; i < happyHours.size(); i++) {
				String[] fields = happyHours.get(i).split("\t");
				ArrayList<String> geocodes = geocoder.geocode(fields[0]);
				
				out.println(fields[0] + '\t' + geocodes.get(0) + '\t' + geocodes.get(1) + '\t' + fields[1] + '\t' + fields[2] + '\t' + fields[3]
						+ '\t' + fields[4]);
			}				
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}

}
