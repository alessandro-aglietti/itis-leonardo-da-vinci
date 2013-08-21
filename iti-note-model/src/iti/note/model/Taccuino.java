package iti.note.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Taccuino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String titolo;

	@OneToMany
	private List<Nota> note;

	public Taccuino() {
		// TODO Auto-generated constructor stub
	}

	public Taccuino(String titolo) {
		super();
		this.titolo = titolo;
		this.note = new ArrayList<Nota>();
	}

	public void addNota(Nota nota) {
		note.add(nota);
	}
}