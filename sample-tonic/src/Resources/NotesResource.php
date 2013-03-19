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

		$page = $this->container["twig"]->render('Notes.html', $this->getAllNotes());

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
	
	function getAllNotes() {
		$model = array();
		$model["notes"] = \Nota::findAll($this->container["db"]);
		
		return $model;
	}

	/**
	 * @method POST
	 */
	function post(){
	
		$data = array();
		parse_str($this->request->data, $data);
	
		$nota = new \Nota($data["titolo"], $data["testo"]);
	
		$nota->save($this->container["db"]);
		
		$page = $this->container["twig"]->render('notes-body.html', $this->getAllNotes());
		
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
	 * @method GET
	 * @provides text/html
	 */
	function get($id = 1) {

		$model = array();
		$model["nota"] = \Nota::find($this->container["db"], $id);

		$page = $this->container["twig"]->render('Note.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}

?>