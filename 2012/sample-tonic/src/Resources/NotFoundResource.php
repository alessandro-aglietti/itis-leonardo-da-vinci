<?php

namespace Resources;

use Tonic\Response, Tonic\Resource;

class NotFoundResource extends Resource {

	/**
	 * @priority 1
	 */
	function get() {

		return new Response(Response::NOTFOUND, null, array(
				'content-type' => 'text/html'
		));
	}
}

?>