package iti.note.dao;

import iti.note.jdbc.PostgreSQLConnection;
import iti.note.model.Taccuino;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class TaccuinoDAO {

	public static void create(Taccuino t) throws SQLException {
		create(t, PostgreSQLConnection.getConn());
	}

	public static List<Taccuino> list() throws SQLException {
		return list(PostgreSQLConnection.getConn());
	}

	private static void create(Taccuino t, Connection conn) throws SQLException {
		// comando SQL parametrico
		String sql = "INSERT INTO Taccuino (id, titolo) VALUES (?,?)";

		// completo il comando aggiungendo le parti parametriche
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setLong(1, t.getId());
		preparedStatement.setString(2, t.getTitolo());

		System.out.println("SQL: " + preparedStatement.toString());

		// eseguo l'inserimento
		preparedStatement.executeUpdate();

		System.out
				.println("Inserimento completato con successo per l'oggetto: "
						+ t.toString());
	}

	private static List<Taccuino> list(Connection conn) throws SQLException {
		List<Taccuino> ms = new ArrayList<Taccuino>();

		// comando SQL parametrico
		String sql = "SELECT * FROM Taccuino";

		// completo il comando aggiungendo le parti parametriche
		PreparedStatement preparedStatement = conn.prepareStatement(sql);

		ResultSet rs = preparedStatement.executeQuery();

		// scorro tutti i risultati
		while (rs.next()) {
			// aggiungo alla lista di risultati la riga corrente
			ms.add(new Taccuino(rs.getLong("id"), rs.getString("titolo")));
		}

		return ms;
	}
}
