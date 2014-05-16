<?php

namespace Resources;

use Tonic\Response, Tonic\Resource;

/**
 * @uri /taccuino
 * @uri /taccuino/(.*+)
 */
class Taccuini extends Resource {

	/**
	 * @method GET
	 * @provides text/html
	 */
	function get() {

		$page = $this->container["twig"]->render('Taccuini.html');

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}
?>