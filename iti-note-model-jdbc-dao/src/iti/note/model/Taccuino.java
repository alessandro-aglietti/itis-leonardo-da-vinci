package iti.note.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Taccuino {
	private long id;

	private String titolo;

	public Taccuino() {
		// TODO Auto-generated constructor stub
	}

	public Taccuino(long id, String titolo) {
		super();
		this.id = id;
		this.titolo = titolo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public void create(Connection conn) throws SQLException {
		// comando SQL parametrico
		String sql = "INSERT INTO Taccuino (id, titolo) VALUES (?,?)";

		// completo il comando aggiungendo le parti parametriche
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setLong(1, this.getId());
		preparedStatement.setString(2, this.getTitolo());

		System.out.println("SQL: " + preparedStatement.toString());

		// eseguo l'inserimento
		preparedStatement.executeUpdate();

		System.out
				.println("Inserimento completato con successo per l'oggetto: "
						+ this.toString());
	}

	public static List<Taccuino> list(Connection conn) throws SQLException {
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
