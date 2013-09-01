<?php

namespace Client;

require 'vendor/autoload.php';

use Guzzle\Http\Client;

class TaccuinoClient {
	private $client;
	
	function __construct($url = 'http://localhost:8080/iti-note-model-service') {
		$this->client = new Client ( $url );
	}
	
	function retrive($path = 'taccuino') {
		$request = $this->client->get ( $path );
		
		$response = $request->send ();
		
		return $response->json ();
	}
	
	function create($taccuino, $path = 'taccuino') {
		$request = $this->client->post ( $path );
		
		$request->setBody ( json_encode ( $taccuino ), 'application/json' );
		
		$response = $request->send ();
		
		return $response->json ();
	}
}
?>