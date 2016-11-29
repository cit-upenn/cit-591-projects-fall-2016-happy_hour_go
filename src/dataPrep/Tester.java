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
			fReader.splitBars();
			ArrayList<String> barsHTML = fReader.getBars();
			Parser parser = new Parser(barsHTML);
//			write output as output/***day.csv
			PrintWriter out = new PrintWriter(new OutputStreamWriter(new FileOutputStream
					("data/clean/" + fileName.substring(9, fileName.length() - 4) + "csv")));
			
			ArrayList<Bar> bars = parser.getBars();
			
//			Iterate every Bar object.
			for (Bar bar : bars) {
//				for every Bar object, iterate every happy hour.
//				columns are separated by tab, because data contain commas and semicolons. Please open the output files in text editor for a better view.
				for (int i = 0; i < bar.description.size(); i++) {
					out.println(bar.name + "\t" + bar.address + "\t" + bar.phone + "\t" + bar.startTime.get(i) + "\t" + bar.endTime.get(i) + "\t" + bar.description.get(i));
				}
			}
			
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		in.close();
	}

}
