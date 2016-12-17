import static org.junit.Assert.*;

import org.junit.Test;

import cit591hw6.mapMaker.Go;

public class GoTest {
	
	@Test
	public void testGo() {
		Go go = new Go();
		assertNotNull("Go should not be null", go);
	}
}
