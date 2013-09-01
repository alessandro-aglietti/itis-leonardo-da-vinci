package iti.note.jdbc;

import iti.note.test.model.TaccuinoTestCase;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class PostgreSQLConnection {

	private static final Properties JDBC_PROPERTIES = new Properties();
	static {
		try {
			JDBC_PROPERTIES.load(PostgreSQLConnection.class.getClassLoader()
					.getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			System.err.println("Errore durante la lettura di jdbc.properties");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	private static Connection conn;

	/**
	 * Inizializzazione della connessione JDBC
	 */
	private static void init() {
		try {
			Class.forName(JDBC_PROPERTIES.getProperty("DB_DRIVER"));
		} catch (ClassNotFoundException e) {
			System.err.println("Errore durante il caricamento del driver JDBC");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		System.out.println("PostgreSQLConnection");
		System.out.println("DB_DRIVER: "
				+ JDBC_PROPERTIES.getProperty("DB_DRIVER"));
		System.out
				.println("DB_USER: " + JDBC_PROPERTIES.getProperty("DB_USER"));
		System.out.println("DB_PASSWORD: "
				+ JDBC_PROPERTIES.getProperty("DB_PASSWORD"));
		System.out.println("DB_CONNECTION: "
				+ JDBC_PROPERTIES.getProperty("DB_CONNECTION"));

		try {
			conn = DriverManager.getConnection(
					JDBC_PROPERTIES.getProperty("DB_CONNECTION"),
					JDBC_PROPERTIES.getProperty("DB_USER"),
					JDBC_PROPERTIES.getProperty("DB_PASSWORD"));
		} catch (SQLException e) {
			System.err.println("Errore durante la connessione al database");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * Utilizza il pattern Singleton - http://it.wikipedia.org/wiki/Singleton
	 * 
	 * @return l'istanza della connessione
	 */
	public static Connection getConn() {
		if (conn == null) {
			init();
			return conn;
		} else {
			try {
				if (conn.isClosed()) {
					init();
					return conn;
				} else {
					return conn;
				}
			} catch (SQLException e) {
				System.err
						.println("Errore durante la verifica della connessione JDBC");
				throw new RuntimeException(e);
			}
		}
	}

	public static Boolean isClosed() throws SQLException {
		return conn.isClosed();
	}
}