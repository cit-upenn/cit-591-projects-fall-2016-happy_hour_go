import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import cit591hw6.dataPrep.FileReader;
import cit591hw6.dataPrep.Parser;

public class ParserTest {
	private String webpage;
	
	@Before
	public void setup() {
		FileReader fr = new FileReader("data/raw/sunday.html");
		try {
			fr.readFileWhole();
			webpage = fr.getWebpageString();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testParser() {
		Parser parser = new Parser(webpage);
		
		assertNotNull(parser.getBarHTMLItems());
		assertNotNull(parser.getHappyHours());
	}
}
