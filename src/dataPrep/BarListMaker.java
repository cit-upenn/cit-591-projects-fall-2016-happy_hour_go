package dataPrep;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class reads every file from data/clean and writes unique bars and their addresses into unique-bars.csv. There are in total 311 bars.
 * @author Han Zhu
 *
 */
public class BarListMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			ArrayList<String> printed = new ArrayList<>();

			FileReader fr2 = new FileReader("unique-bars.csv");
			fr2.readFile();
			
			for (int i = 0; i < fr2.getLines().size(); i++) {
				String line = fr2.getLines().get(i);
				String[] fields = line.split("\t");
				printed.add(fields[0]);
			}
			
			FileReader fr = new FileReader("data/clean/sunday.csv");
			fr.readFile();
			
//			Write to existing file without overwriting it.
			PrintWriter out = new PrintWriter(new FileWriter("unique-bars.csv", true));
			
			for (int i = 0; i < fr.getLines().size(); i++) {
				String line = fr.getLines().get(i);
				String[] fields = line.split("\t");
				
//				If unique-bars.csv already contains the bar, continue.
				if (printed.contains(fields[0])) {
					continue;
				}
				printed.add(fields[0]);
				out.println(fields[0] + '\t' + fields[1]);
			}
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
