package iti.note.model;

import iti.note.client.ClientHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Taccuino implements Serializable {

	private static final long serialVersionUID = -8337410006399411303L;

	private long id;

	private String titolo;

	private List<Nota> note = new ArrayList<Nota>();

	public Taccuino() {
		// TODO Auto-generated constructor stub
	}

	public Taccuino(String titolo) {
		super();
		this.titolo = titolo;
	}

	public long getId() {
		return id;
	}

	public void addNota(Nota nota) {
		note.add(nota);
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getTitolo() {
		return titolo;
	}

	public List<Nota> getNote() {
		return note;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNote(List<Nota> note) {
		this.note = note;
	}

	public Taccuino create() {
		return ClientHelper.create(this);
	}

	public static List<Taccuino> retrieve() {
		return ClientHelper.retrieve(Taccuino.class);
	}
}