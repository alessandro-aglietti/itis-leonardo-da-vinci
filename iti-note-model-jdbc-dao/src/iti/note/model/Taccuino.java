package iti.note.model;


public class Taccuino {
	private long id;

	private String titolo;

	public Taccuino() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public Taccuino(long id, String titolo) {
		super();
		this.id = id;
		this.titolo = titolo;
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
	
	@Override
	public String toString() {
		return "{ 'id' : " + this.getId() + ", 'titolo' : '" + this.getTitolo() + "' }";
	}
}
