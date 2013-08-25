package iti.note.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String testo;

	public Nota() {
		// TODO Auto-generated constructor stub
	}

	public Nota(String testo) {
		super();
		this.testo = testo;
	}
}