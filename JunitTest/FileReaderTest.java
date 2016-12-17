import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cit591hw6.dataPrep.FileReader;

public class FileReaderTest {
	
	private String fileName;

	@Before
	public void setup() {
		fileName = "data/raw/sunday.html";
	}
	
	@Test
	public void testReadFileWhole() {
		FileReader fr = new FileReader(fileName);
		try {
			fr.readFileWhole();
			assertNotNull("webpageString shouldn't be null.", fr.getWebpageString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testReadFile() {
		FileReader fr = new FileReader(fileName);
		try {
			fr.readFile();
			assertNotNull("lines shouldn't be null.", fr.getLines());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFileName() {
		FileReader fr = new FileReader(fileName);
		assertEquals("data/raw/sunday.html", fr.getFileName());
	}
}
