<?php

namespace Resources;

use Tonic\Response, Tonic\Resource;

class FatalErrorResource extends Resource {

	/**
	 * @priority 1
	 */
	function get() {

		return new Response(Response::INTERNALSERVERERROR, null, array(
				'content-type' => 'text/html'
		));
	}
}

?>