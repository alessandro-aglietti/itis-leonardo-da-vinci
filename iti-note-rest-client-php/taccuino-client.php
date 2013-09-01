<?php
	require 'src/Model/Taccuino.php';
	
	use Model\Taccuino;
	
	$t = new Taccuino();
	
	echo json_encode($t->retrive());
?>