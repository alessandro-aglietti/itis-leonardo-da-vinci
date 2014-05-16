<?php

namespace Resources;

use Tonic\Response, Tonic\Resource;

/**
 * @uri /
 */
class RootResource extends Resource {

	/**
	 * @method GET
	 * @provides text/html
	 */
	function get() {

		$model = array(
				"ssid" => session_id()
		);

		$page = $this->container["twig"]->render('Root.html', $model);

		return new Response(Response::OK, $page, array(
				'content-type' => 'text/html'
		));
	}
}

?>