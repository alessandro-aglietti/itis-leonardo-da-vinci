package iti.note.test.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import iti.note.model.Taccuino;

import java.util.List;

import org.junit.Test;

public class TaccuinoClientTest {

	@Test
	public void test() {
		Taccuino t = new Taccuino("Taccuino by Jersey");

		t.create();

		assertNotNull(t.getId());

		List<Taccuino> tt = Taccuino.retrieve();

		assertFalse(tt.isEmpty());
	}

}
