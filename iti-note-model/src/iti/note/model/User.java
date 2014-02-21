package iti.note.model;

import iti.note.jpa.EMF;
import iti.note.jpa.ModelHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TypedQuery;

@Entity
@Table
public class User implements Serializable {

	private static final long serialVersionUID = -8337410006399411303L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nome;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<Taccuino> taccuini = new ArrayList<Taccuino>();

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Taccuino> getTaccuini() {
		return taccuini;
	}

	public void setTaccuini(List<Taccuino> taccuini) {
		this.taccuini = taccuini;
	}
}