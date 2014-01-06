<?php

namespace ITI\Model;
use Doctrine\Common\Collections\ArrayCollection;

/** @Entity **/
class Taccuino
{
    /**
     * @Id @Column(type="integer")
     * @GeneratedValue(strategy="SEQUENCE")
	 * @SequenceGenerator(sequenceName="hibernate_sequence")
     **/
    public $id;

    /** @Column(type="string") **/
    public $titolo;

    /**
     * @OneToMany(targetEntity="Nota", mappedBy="taccuino", cascade={"persist"})
     **/
    private $notes;

    public function __construct($titolo = '') {
        $this->titolo = $titolo;
        $this->notes  = new ArrayCollection();
    }

    public function setNotes($notes) {
        $this->notes = $notes;
    }

    public function getNotes() {
        return $this->notes;
    }
    
    public function create() {
    	\ITI\Helper\ModelHelper::create($this);
    }
    
    public static function retrieve(){
    	return \ITI\Helper\ModelHelper::retrieve('taccuino');
    } 
}

?>
