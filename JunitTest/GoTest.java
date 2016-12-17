import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import cit591hw6.mapMaker.Go;

public class GoTest {
	
	@Test
	public void testGo() {
		Go go = new Go();
		assertNotNull("Go should not be null", go);
	}
	
//	@Test
//	public void test() {
//		Go go = new Go();
//		go.mapInitialized();
//	}

}
