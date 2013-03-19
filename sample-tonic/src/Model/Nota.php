<?php

class Nota {

	public $id;
	public $testo;
	public $titolo;

	function Nota($title, $text, $id = null){
		$this->testo = $text;
		$this->titolo = $title;

		if ( $id != null ) {
			$this->id = $id;
		}
	}

	function save($driver) {

		$sql = "insert into notes(titolo, testo) values ('" . $this->titolo . "','" . $this->testo . "')";

		$driver->execute($sql);
	}

	function update($driver) {

		$sql = "update notes set titolo = '" . $this->titolo . "', test = '" . $this->testo . "' where id = " . $this->id;

		$driver->execute($sql);
	}

	function delete($driver) {
		$sql = "delete from notes where id = " . $this->id;

		$driver->execute($sql);
	}

	static function findAll($driver) {
		$sql = "select * from notes";

		$result = $driver->execute($sql);

		$notes = array();

		foreach ($result as $r) {
			array_push($notes, new Nota($r["titolo"], $r["testo"], $r["id"]));
		}

		return $notes;
	}

	static function find($driver, $id) {
		$sql = "select * from notes where id = " . $id;

		$result = $driver->execute($sql);
		
		$result = $result[0];

		$nota = new Nota($result["titolo"], $result["testo"], $result["id"]);

		return $nota;
	}
}

?>