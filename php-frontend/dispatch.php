<?php

// inizializzo la sessione
session_start();

// BASE URL APPLICATIVO
$baseUrl = "/iti-note-model-service/";

// Carica tutte le classi necessarie a Tonic
require_once 'src/Tonic/Autoloader.php';

// Twig
require_once 'src/Twig/Autoloader.php';
Twig_Autoloader::register();
$loader = new Twig_Loader_Filesystem('src/Views');
$twig = new Twig_Environment($loader);


// Carico I18n
$locale = "";

// verifico se si vuole forzare la lingua
if ( isset($_REQUEST["locale"]) ) {
	$locale = $_REQUEST["locale"];
} else {
	$headers = getallheaders();

	if ( array_key_exists("Accept-Language", $headers) ) {
		// 	utilizzo l'http Accept-Language header per intercettare la lingua
		$header = $headers["Accept-Language"];

		if ( strlen($header) >= 2) {
			$locale = substr($header, 0, 2);
		}
	}
}

// costruisco il nome del file
$i18nfile = "src/i18n/i18n_" . $locale . ".json";
$i18n = json_decode("{}");

// verifico se il file esiste
if ( file_exists($i18nfile) ) {
	$i18njson = file_get_contents($i18nfile);
} else {
	// se non trovo nessun file di default carico quello italiano
	$i18njson = file_get_contents("src/i18n/i18n_it.json");
}

//decodifico il json
$i18n = json_decode($i18njson);

// aggiungo l'i18n per tutti i template
$twig->addGlobal("i18n", $i18n);
$twig->addGlobal("burl", $baseUrl);

// classe di utility per chiamate json verso tomcat

class TomcatClient {

	function model() {

		$ch = curl_init("http://" . $_SERVER['HTTP_HOST']. $_SERVER['REQUEST_URI']);
		
		$headers = getallheaders();
		
		$headers['Accept'] = 'application/json';
		
		$curlHeaders = array();
		
		foreach ($headers as $key => $value) {
			array_push($curlHeaders, $key . ": " . $value);
		}
		
		$options = array(
				CURLOPT_RETURNTRANSFER => true,
				CURLOPT_HTTPHEADER => $curlHeaders,
				CURLOPT_HEADER => 1,
				CURLOPT_CONNECTTIMEOUT_MS => 2000,
				CURLOPT_TIMEOUT_MS => 5000
		);

		curl_setopt_array( $ch, $options );
		
		$exec = curl_exec($ch);
		
		$body = array("error" => "risposta vuota");
		
		if(!curl_errno($ch))
		{
			$httpStatusCode = curl_getinfo($ch, CURLINFO_HTTP_CODE);
			if ($httpStatusCode < 400 ) {
				$header_size = curl_getinfo($ch, CURLINFO_HEADER_SIZE);
			
				$body = json_decode(substr($exec, $header_size));
			
				$respHeader = substr($exec, 0, $header_size);
			
				preg_match('/^Set-Cookie:\s*([^;]*)/mi', $respHeader, $m);
			
				header($m[0]);
			} else {
				$body = array("error" => $httpStatusCode);
			}
		} else {
			$body = array("error" => "errore di comunicazione con il backend");
		}
		
		curl_close($ch);
		
		return $body;
	}
}

$tc = new TomcatClient();

$model = $tc->model();

$twig->addGlobal("model", $model);

// container disponibile da tutti i controller
$container = array(
		"twig" => $twig
);

// Tonic options
$opt = array(
		'load' => 'src/Resources/*.php'
);

// Inizializzo l'applicazione indicando dove sono le risorse
$app = new Tonic\Application($opt);

// recupero la request HTTP
$request = new Tonic\Request();

//decode JSON data received from HTTP request
if ($request->contentType == 'application/json') {
	$request->data = json_decode($request->data);
}

// gestisco l'errore nel caso la risorsa non esista
try {
	$resource = $app->getResource($request);
} catch(Tonic\NotFoundException $e) {
	$resource = new Resources\NotFoundResource($app, $request, array());
}

$resource->container = $container;

// gestisco l'errore nel caso esploda il mio controller
try {
	$response = $resource->exec();
} catch(Tonic\Exception $e) {
	$resource = new Resources\FatalErrorResource($app, $request, array());
	$response = $resource->exec();
}

// encode output
if ($response->contentType == 'application/json') {
	$response->body = json_encode($response->body);
}

$response->output();
?>