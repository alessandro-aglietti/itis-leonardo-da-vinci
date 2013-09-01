<?php
	require 'src/Model/Taccuino.php';
	require 'src/Client/TaccuinoClient.php';
	
	use Model\Taccuino, Client\TaccuinoClient;
	
	$t = new Taccuino('Taccuino by Guzzle');

	$tc = new TaccuinoClient();
	
	$tc->create($t);
	
	echo json_encode($tc->retrive());
?>