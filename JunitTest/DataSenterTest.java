import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import cit591hw6.dataPrep.FileReader;
import cit591hw6.mapMaker.DataSender;
import cit591hw6.search.Bar;
import cit591hw6.search.BarData;
import cit591hw6.search.BarFinder;
import cit591hw6.search.FileFetcher;

public class DataSenterTest {
	private ArrayList<Bar> bars;
	private DataSender ds;
	
	@Before
	public void setUp(){
		
    	FileFetcher ff = new FileFetcher(now.get(Calendar.DAY_OF_WEEK));
		BarData bd = new BarData(ff);
		BarFinder bf = new BarFinder(now, bd);
		searchResult = bf.find();
		ds = new DataSender(searchResult);
	}



}
