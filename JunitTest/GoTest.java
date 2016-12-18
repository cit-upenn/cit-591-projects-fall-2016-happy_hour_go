import static org.junit.Assert.*;
import java.io.FileNotFoundException;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

import org.junit.Test;

import cit591hw6.mapMaker.Go;


public class GoTest {
	
	@Test
	public void testGo() throws FileNotFoundException {
		Go go = new Go();
		go.getSearchResult();
		assertNotNull("Go should not be null", go);

	}
	


}