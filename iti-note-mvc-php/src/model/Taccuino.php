<?php

namespace ITI\Model;

/**
 * @Entity
 * @Table(name="taccuino")
 **/
class Taccuino {
	
	/** @Id
	 * @Column(type="integer") 
	 * @GeneratedValue(strategy="SEQUENCE")
	 * @SequenceGenerator(sequenceName="hibernate_sequence")
	**/
	public $id;
	
	/** @Column(type="string") **/
	public $titolo;
	
	/**
	 * @OneToMany(targetEntity="ITI\Model\Nota", mappedBy="taccuino_id", cascade={"all"})
	 * @var Nota[]
	 **/
	public $note;
	
	function __construct($titolo, $id = null){
		$this->titolo = $titolo;
		$this->id = $titolo;
	}
	
	function setNote($note) {
		$this->note = $note;
	}
}
?>