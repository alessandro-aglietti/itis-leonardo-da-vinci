<?php

namespace ITI\Model;
use Doctrine\Common\Collections\ArrayCollection;

/** @Entity **/
class Taccuino
{
    /** @Id @Column(type="integer") @GeneratedValue **/
    public $id;

    /** @Column(type="string") **/
    public $titolo;

    /**
     * @OneToMany(targetEntity="Nota", mappedBy="taccuino", cascade={"persist"})
     **/
    private $notes;
    // ...

    public function __construct($titolo = '') {
        $this->titolo    = $titolo;
        $this->notes = new ArrayCollection();
    }

    public function setNotes($notes) {
        $this->notes = $notes;
    }

    public function getNotes() {
        return $this->notes;
    }
}

?>
