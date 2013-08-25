package iti.note.test;

import java.util.List;

import iti.note.model.Nota;
import iti.note.model.Taccuino;

public class PersistenceMethodTest {
	public static void main(String[] args) {
		Taccuino t = new Taccuino("Taccuino di alessandro");

		Nota nota = new Nota("Nota di alessanro");

		t.addNota(nota);

		t.save();

		List<Taccuino> tt = Taccuino.findAll();
		System.out.println("Trovati " + tt.size() + " taccuini");

	}
}
