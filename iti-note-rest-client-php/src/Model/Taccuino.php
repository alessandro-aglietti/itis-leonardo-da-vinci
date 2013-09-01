<?php

namespace Model;

class Taccuino {
	
	public $id;
	
	public $titolo;
	
	public $note;
	
	function __construct($titolo){
		$this->titolo = $titolo;
	}
}
?>