package iti.note.test.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import iti.note.dao.TaccuinoDAO;
import iti.note.jdbc.PostgreSQLConnection;
import iti.note.model.Taccuino;

import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;

public class TaccuinoTestCase {

	private static final Logger log = Logger.getLogger(TaccuinoTestCase.class
			.getName());

	@Test
	public void test() {

		try {
			PostgreSQLConnection.getConn().setAutoCommit(false);
		} catch (SQLException e) {
			log.severe(ExceptionUtils.getStackTrace(e));
			fail(e.getMessage());
		}

		Savepoint sp = null;
		try {
			sp = PostgreSQLConnection.getConn().setSavepoint();
		} catch (SQLException e) {
			log.severe(ExceptionUtils.getStackTrace(e));
			fail(e.getMessage());
		}

		assertNotNull(sp);

		Taccuino t = new Taccuino();
		t.setId(99l);
		t.setTitolo("Taccuino JDBC");

		try {
			TaccuinoDAO.create(t);
		} catch (SQLException e) {
			log.severe(ExceptionUtils.getStackTrace(e));
			fail(e.getMessage());
		}

		List<Taccuino> tt = new ArrayList<Taccuino>();
		try {
			tt = TaccuinoDAO.list();
		} catch (SQLException e) {
			log.severe(ExceptionUtils.getStackTrace(e));
			fail(e.getMessage());
		}

		assertFalse(tt.isEmpty());
	

		try {
			PostgreSQLConnection.getConn().rollback(sp);
		} catch (SQLException e) {
			log.severe(ExceptionUtils.getStackTrace(e));
			fail(e.getMessage());
		}

	}
}
