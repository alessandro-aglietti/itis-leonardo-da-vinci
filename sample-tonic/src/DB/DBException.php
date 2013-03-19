<?php

namespace DB;

class DBException extends \Exception {
	protected $code;
	protected $message;

	function __construct($code, $message) {
		$this->code = $code;
		$this->message = $message;
	}
}

?>