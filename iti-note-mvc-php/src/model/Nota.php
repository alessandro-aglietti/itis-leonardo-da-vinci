<?php

namespace ITI\Model;

/** @Entity **/
class Nota
{
    
    /**
     * @Id @Column(type="integer")
     * @GeneratedValue(strategy="SEQUENCE")
	 * @SequenceGenerator(sequenceName="hibernate_sequence")
     * **/
    public $id;

    /**
     * @Column(type="string")
    **/
    public $testo;
    
    /**
     * @ManyToOne(targetEntity="Taccuino", inversedBy="notes")
     * @JoinColumn(name="taccuino_id", referencedColumnName="id")
     **/
    private $taccuino;

    public function __construct($testo = '') {
        $this->testo = $testo;
    }

    public function setTaccuino($taccuino) {
        $this->taccuino = $taccuino;
    }
    
    public function create() {
    	\ITI\Helper\ModelHelper::create($this);
    }
}

?>
