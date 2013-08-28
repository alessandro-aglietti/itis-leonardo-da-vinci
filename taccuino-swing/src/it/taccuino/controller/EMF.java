/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.taccuino.controller;

import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {   
    private static EntityManagerFactory emfInstance;

    private EMF() {
    }
    
    private static void generate(String persistenceUnitName, Map properties){
        if (emfInstance == null)
            emfInstance = Persistence.createEntityManagerFactory(persistenceUnitName, properties);
    }

    public static EntityManagerFactory get() {
        return emfInstance;
    }
    
    public static EntityManagerFactory get(String persistenceUnitName, Map properties) {
        generate(persistenceUnitName, properties);
        return emfInstance;
    }
    
    
}
