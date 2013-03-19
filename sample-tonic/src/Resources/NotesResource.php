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

		array_push($notes, new \Note("Nota 1 by costruct"));
		array_push($notes, new \Note("Nota 2"));

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