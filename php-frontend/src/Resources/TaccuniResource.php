<?php

namespace Resources;

use Tonic\Response, Tonic\Resource;

/**
 * @uri /taccuino
 */
class Taccuini extends Resource {

	/**
	 * @method GET
	 * @provides text/html
	 */
	function get() {
		
		$model["taccuini"] = $this->container["tc"]->get("/taccuino");

		$page = $this->container["twig"]->render('Taccuini.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}
?>