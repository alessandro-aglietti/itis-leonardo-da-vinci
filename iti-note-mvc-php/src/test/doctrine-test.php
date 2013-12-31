<?php

use Doctrine\ORM\Tools\Setup;
use Doctrine\ORM\EntityManager;
//use Doctrine\Common\ClassLoader;

require_once "vendor/autoload.php";
require_once "src/model/Taccuino.php";
require_once "src/model/Nota.php";

//$loader = new ClassLoader("Doctrine", '../src/');
//$loader->register();

// Create a simple "default" Doctrine ORM configuration for Annotations
$isDevMode = true;
//$modelDir = __DIR__."/src/model";
$config = Setup::createAnnotationMetadataConfiguration(array('../src/model'), $isDevMode);
// or if you prefer yaml or XML
//$config = Setup::createXMLMetadataConfiguration(array(__DIR__."/config/xml"), $isDevMode);
//$config = Setup::createYAMLMetadataConfiguration(array(__DIR__."/config/yaml"), $isDevMode);

// database configuration parameters
$conn = array(
		'driver' => 'pdo_pgsql',
		'user' => 'postgres',
		'password' => 'postgres',
		'host' => '127.0.0.01',
		'port' => '5432',
		'dbname' => 'iti_note'
);

$entityManager = EntityManager::create($conn, $config);



//    INSERIMENTO TACCUINO/NOTA

/*
$taccuino = new ITI\Model\Taccuino('primo taccuino');
$nota = new ITI\Model\Nota('prima nota del primo taccuino');

$nota->setTaccuino($taccuino);
$taccuino->getNotes()->add($nota);

$entityManager->persist($taccuino);
$entityManager->flush();
*/



// VISUALIZZAZIONE TACCUINI/NOTE

/*
$taccuini = $entityManager->getRepository('ITI\Model\Taccuino')->findAll();

foreach($taccuini as $t) {
    echo "Taccuino: " . $t->titolo . "\n";
    echo "Note: " . count($t->getNotes()) . "\n";
} 
*/


echo "Yeah";

?>
