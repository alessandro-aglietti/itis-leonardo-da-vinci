<?php

namespace Model;

require 'vendor/autoload.php';

use Guzzle\Http\Client;

class Taccuino {
	
	private $client;

	function __construct($url = 'http://localhost:8080/iti-note-model-service'){
		$this->client = new Client( $url );
	}
	
	function retrive($path = 'taccuino') {
		$request = $this->client->get($path);
		$response = $request->send ();
		return $response->json();
	}
}
?>