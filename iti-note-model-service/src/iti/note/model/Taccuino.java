package iti.note.model;

import iti.note.jpa.EMF;
import iti.note.jpa.ModelHelper;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table
public class Taccuino {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String titolo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Nota> note = new ArrayList<Nota>();

	public Taccuino() {
		// TODO Auto-generated constructor stub
	}

	public Taccuino(String titolo) {
		super();
		this.titolo = titolo;
	}

	public Taccuino create() {
		return ModelHelper.create(this);
	}

	public Taccuino update() {
		return ModelHelper.update(this);
	}

	public static Taccuino retrieve(long id) {
		return ModelHelper.retrieve(id, Taccuino.class);
	}

	public static List<Taccuino> retrieve() {
		return ModelHelper.retrieve(Taccuino.class);
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

	public static List<Taccuino> searchByTitolo(String titolo) {
		final String sql = "SELECT e FROM " + Taccuino.class.getSimpleName()
				+ " e WHERE e.titolo LIKE :t";

		List<Taccuino> tt = new ArrayList<Taccuino>();
		EntityManager em = null;

		try {
			em = EMF.get();

			TypedQuery<Taccuino> q = em.createQuery(sql, Taccuino.class);

			q.setParameter("t", "%" + titolo + "%");

			tt = q.getResultList();
		} finally {
			ModelHelper.closeEm(em);
		}

		return tt;
	}
}