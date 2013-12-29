<?php

namespace ITI\Model;

/** @Entity **/
class Nota
{
    
    /** @Id @Column(type="integer") @GeneratedValue **/
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
    // ...

    public function __construct($testo = '') {
        $this->testo = $testo;
    }

    public function setTaccuino($taccuino) {
        $this->taccuino = $taccuino;
    }
}

?>
