package iti.note.test;

import iti.note.model.Nota;
import iti.note.model.Taccuino;

import java.util.List;

public class PersistenceMethodTest {
	public static void main(String[] args) {
		Taccuino t = new Taccuino("Taccuino di alessandro 5");

		Nota nota = new Nota("Nota di alessanro");

		t.addNota(nota);

		t = t.save();

		t.setTitolo("Nuovo titolo");

		t = t.save();

		List<Taccuino> tt = Taccuino.findAll();
		System.out.println("Trovati " + tt.size() + " taccuini");

	}
}
