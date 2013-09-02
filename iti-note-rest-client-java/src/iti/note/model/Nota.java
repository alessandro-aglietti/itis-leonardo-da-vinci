package iti.note.model;

import java.io.Serializable;

public class Nota implements Serializable {

	private static final long serialVersionUID = 7741312373326432357L;

	private long id;

	private String testo;

	public Nota() {
		// TODO Auto-generated constructor stub
	}

	public Nota(String testo) {
		super();
		this.testo = testo;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}