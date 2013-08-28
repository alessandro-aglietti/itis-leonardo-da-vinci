/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.taccuino;

import it.taccuino.controller.EMF;
import it.taccuino.view.Home;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

/**
 * 
 * @author gabrieleposca
 */
public class Taccuino {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		persistenceSet();

		Home formHome = new Home();
		formHome.setVisible(true);
	}

	private static void persistenceSet() {
		String username, pass, server, port, dbName;

		username = "root";
		pass = "toor";
		server = "192.168.56.101";
		port = "3306";
		dbName = "iti_note_swing";

		Map emfp = new HashMap();
		emfp.put("hibernate.connection.username", username);
		emfp.put("hibernate.connection.password", pass);
		emfp.put("hibernate.connection.url", "jdbc:mysql://" + server + ":"
				+ port + "/" + dbName + "?zeroDateTimeBehavior=convertToNull");
		EntityManagerFactory emf = EMF.get("taccuinoPU", emfp);
	}
}
