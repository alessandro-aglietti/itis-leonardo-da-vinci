<?php

namespace ITI\Model;

/**
 * @Entity
 * @Table(name="nota")
 **/
class Nota {

	/** @Id
	 * @Column(type="integer") 
	 * @GeneratedValue(strategy="SEQUENCE")
	 * @SequenceGenerator(sequenceName="hibernate_sequence")
	**/
	public $id;

	/**
	 * @Column(type="string")
	**/
	public $testo;

	/**
     * @Column(type="integer")
     * @ManyToOne(targetEntity="ITI\Model\Taccuino", inversedBy="id")
     **/
	public $taccuino_id;

	function __construct($testo, $id = null){
		$this->testo = $testo;
		
		$this->id = $id;
	}
	
	function setTaccuino_id($taccuino_id){
		$this->taccuino_id = $taccuino_id;
	}
}

?>