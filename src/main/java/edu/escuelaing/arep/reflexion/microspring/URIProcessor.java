/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.microspring;

/**
 *
 * @author J. Eduardo Arias
 */
public interface URIProcessor {
    public void mapService(String command) throws Exception;
    
    public String executeService(String theuri);
    
}
