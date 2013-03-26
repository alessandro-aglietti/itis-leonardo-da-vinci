<?php

/**
 * @Entity @Table(name="notes")
 **/
class Nota {

	/** @Id @Column(type="integer") @GeneratedValue **/
	public $id;

	/** @Column(type="string") **/
	public $testo;

	/** @Column(type="string") **/
	public $titolo;

	function Nota($title, $text, $id = null){
		$this->testo = $text;
		$this->titolo = $title;

		if ( $id != null ) {
			$this->id = $id;
		}
	}
}

?>