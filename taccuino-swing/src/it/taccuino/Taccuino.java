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

		Home formHome = new Home();
		formHome.setVisible(true);
	}
}
