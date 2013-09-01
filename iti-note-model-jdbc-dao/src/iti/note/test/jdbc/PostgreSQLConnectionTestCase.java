package iti.note.test.jdbc;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import iti.note.jdbc.PostgreSQLConnection;

import java.sql.SQLException;

import org.junit.Test;

public class PostgreSQLConnectionTestCase {

	@Test
	public void test() {
		Boolean isClosed = true;

		System.out.println("Apertura connessione per la prima volta");
		try {
			isClosed = PostgreSQLConnection.getConn().isClosed();
			assertFalse(isClosed);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		System.out.println();
		System.out
				.println("Apertura connessione per la seconda volta, in questo caso viene riciclata la precedente istanza");
		System.out.println("Il metodo init() non viene richiamato");
		try {
			isClosed = PostgreSQLConnection.getConn().isClosed();
			assertFalse(isClosed);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		System.out.println();
		System.out.println("Chiusura della connessione");
		try {
			PostgreSQLConnection.getConn().close();
			isClosed = PostgreSQLConnection.isClosed();
			assertTrue(isClosed);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}

		System.out.println();
		System.out
				.println("Apertura connessione in questo caso viene reinizializzata tramite il metodo init");
		try {
			isClosed = PostgreSQLConnection.getConn().isClosed();
			assertFalse(isClosed);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}
}