/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.taccuino.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * @author gabrieleposca
 */
@Entity
public class Nota implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String testo;

	@ManyToOne
	@JoinColumn(name = "taccuino_id")
	private Taccuino taccuino;

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Taccuino getTaccuino() {
		return taccuino;
	}

	public void setTaccuino(Taccuino taccuino) {
		this.taccuino = taccuino;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Nota)) {
			return false;
		}
		Nota other = (Nota) object;
		if ((this.id == null && other.id != null)
				|| (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "it.taccuino.model.Note[ id=" + id + " ]";
	}

}
