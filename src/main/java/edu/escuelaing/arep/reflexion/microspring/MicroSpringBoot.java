/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.microspring;

import edu.escuelaing.arep.reflexion.httpserver.ServerHttp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class MicroSpringBoot {
    public static void main(String[] args) {
        try {
            URIProcessor mspring=new MicroSpring();
            mspring.mapService(args[0]);
            ServerHttp server=new ServerHttp(mspring);
            server.start();
            
        } catch (Exception ex) {
            Logger.getLogger(MicroSpringBoot.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    
    }
    
}
