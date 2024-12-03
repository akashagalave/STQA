package programminghub;

import static org.junit.Assert.*;

import org.junit.Test;

public class AddTest {

	@Test
	public void testAdd() {
		JunitClass junit= new JunitClass();
		int result=junit.add(300, 300);
		assertEquals(600,result);
	}

}
