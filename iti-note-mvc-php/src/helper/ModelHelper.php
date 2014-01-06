<?php

namespace ITI\Helper;

class ModelHelper {
	
	private static function getEntityManager(){
		$isDevMode = true;
		$config = \Doctrine\ORM\Tools\Setup::createAnnotationMetadataConfiguration(array('../src/model'), $isDevMode);
		$conn = array(
				'driver' => 'pdo_pgsql',
				'user' => 'postgres',
				'password' => 'postgres',
				'host' => '127.0.0.01',
				'port' => '5432',
				'dbname' => 'iti_note'
		);
		
		$entityManager = \Doctrine\ORM\EntityManager::create($conn, $config);
		
		return $entityManager;
	}
	
	public static function create($entity) {
		$em = ModelHelper::getEntityManager();
		
		$em->persist($entity);
		$em->flush();
		
		$em->close();
	}
}
?>