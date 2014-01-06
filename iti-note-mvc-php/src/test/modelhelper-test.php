<?php

require_once "vendor/autoload.php";
require_once "src/model/Taccuino.php";
require_once "src/model/Nota.php";
require_once "src/helper/ModelHelper.php";

$taccuino = new ITI\Model\Taccuino( 'primo taccuino' );
$nota = new ITI\Model\Nota( 'prima nota del primo taccuino' );

$nota->setTaccuino( $taccuino );
$taccuino->getNotes()->add( $nota );

$taccuino->create();
?>