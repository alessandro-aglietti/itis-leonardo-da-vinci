<?php

namespace Resources;

require_once 'src/Model/Nota.php';

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

		$model["notes"] = $this->container["em"]->getRepository("Nota")->findAll();

		$page = $this->container["twig"]->render('Notes.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}

	/**
	 * @method POST
	 */
	function post(){

		$data = array();
		parse_str($this->request->data, $data);

		$nota = new \Nota($data["titolo"], $data["testo"]);

		$this->container["em"]->persist($nota);
		$this->container["em"]->flush();

		$model["notes"] = $this->container["em"]->getRepository("Nota")->findAll();

		$page = $this->container["twig"]->render('notes-body.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}

/**
 * @uri /notes/:id
 */
class Note extends Resource {

	/**
	 * @method DELETE
	 * @provides text/html
	 * @see http://docs.doctrine-project.org/en/2.0.x/reference/working-with-objects.html#removing-entities
	 */
	function delete($id = null) {

	}


	/**
	 * @method GET
	 * @provides text/html
	 */
	function get($id = 1) {

		$model = array();
		$model["nota"] = $this->container["em"]->getRepository("Nota")->find($id);

		$page = $this->container["twig"]->render('Note.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}

?>