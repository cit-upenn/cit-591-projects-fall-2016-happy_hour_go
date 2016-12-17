import static org.junit.Assert.*;

import java.io.FileNotFoundException;
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

public class DataSenderTest {
	private ArrayList<Bar> searchResult;
	private DataSender ds;
	Calendar now = Calendar.getInstance();

	private ArrayList<Double> addrLon = new ArrayList<Double>();
	private ArrayList<Double> addrLat = new ArrayList<Double>();
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<String> startTime = new ArrayList<>();
	private ArrayList<String> endTime = new ArrayList<>();
	private ArrayList<String> description = new ArrayList<String>();
	
	@Before
	public void setUp() throws FileNotFoundException{
		now.set(2016, 12, 6, 11, 0, 0);
    	FileFetcher ff = new FileFetcher(now.get(Calendar.DAY_OF_WEEK));
		BarData bd = new BarData(ff);
		BarFinder bf = new BarFinder(now, bd);
		searchResult = bf.find();
		ds = new DataSender(searchResult);
	}
	@Test
	public void numberTest(){
		assertEquals("number of the searched result should be 8", 8, ds.getAddrLat().size());
	}
	
	@Test
	public void dataSenderLonTest(){
		Double lon = -75.1573385;
		addrLon = ds.getAddrLon();
		assertEquals("result of the first longitude should equals to -75.1573385", lon, addrLon.get(0));

	}
	
	@Test
	public void dataSenderLatTest(){
		Double lat = 39.9389584;
		addrLat = ds.getAddrLat();
		assertEquals("result of the first latitude should equals to 39.9389584", lat, addrLat.get(0));
	}
	

	@Test
	public void dataSenderNameTest(){
		names = ds.getName();
		assertEquals("result of the first bar's name should equals to 12 Steps Down", "12 Steps Down", names.get(0));
	}
	
	@Test
	public void dataSenderStartTest(){
		startTime = ds.getStartTime();
		assertEquals("result of the first bar's start time should equals to 11:00 am", "11:00 am", startTime.get(0));
	}
	@Test
	public void dataSenderEndTest(){
		endTime = ds.getEndTime();
		assertEquals("result of the first bar's end time should equals to 3:00 pm", "3:00 pm", endTime.get(0));
	}
	@Test
	public void dataSenderDescriptionTest(){
		description = ds.getDescription();
		assertEquals("result of the first bar's description should equals to $3 Bloody Mary's & Mimosas", "$3 Bloody Mary's & Mimosas", description.get(0));
	}


}
