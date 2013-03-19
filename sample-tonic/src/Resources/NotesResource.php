<?php

namespace Resources;

require_once 'src/Model/Note.php';

use Tonic\Response, Tonic\Resource;

/**
 * @uri /notes
 */
class Notes extends Resource {

	/**
	 * @method GET
	 * @provides text/html
	 */
	function get() {

		$notes = array();

		$nota = new \Note();
		$nota->text = "Nota 1";

		array_push($notes, $nota);

		$model = array(
				"notes" => $notes
		);

		$page = $this->container["twig"]->render('Notes.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}

?>