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

// obtaining the entity manager
$entityManager = EntityManager::create($conn, $config);

/**
$t = new ITI\Model\Taccuino("by doctrine");

$n = new ITI\Model\Nota("nota by doctrine");

$t->note = array($n);

$entityManager->persist($t);

$entityManager->flush();

*/

$tRepo = $entityManager->getRepository('ITI\Model\Taccuino');
$tt = $tRepo->findAll();

echo "Totale taccuini: " . count($tt);

$t = $tt[0];
echo "Il taccuino " . $t->titolo . " ha " . count($t->note) . " note salvate";

//var_dump($tt);
?>