<?php
namespace DB;

require_once 'DBException.php';

class MySQLDriver {

	protected $link;

	function __construct($host, $user, $pwd, $db) {
		$this->link = mysql_connect($host, $user, $pwd);
		if ( !$this->link ) {
			throw new DBException(500, mysql_error());
		}

		if (!mysql_select_db($db, $this->link)) {
			throw new DBException(500, mysql_error());
		}
	}

	function execute($query){
		$result = mysql_query($query, $this->link);

		if (!$result) {
			throw new DBException(500, mysql_error());
		} elseif ($result === TRUE) {
			// nel caso di inserimenti
			return array();
		}

		if (mysql_num_rows($result) == 0) {
			return array();
		}

		$fetchedResult = array();
		while ($row = mysql_fetch_assoc($result)) {
			array_push($fetchedResult, $row);
		}

		return $fetchedResult;
	}
}
?>