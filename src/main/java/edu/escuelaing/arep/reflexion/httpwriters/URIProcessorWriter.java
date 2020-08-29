/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpwriters;

import edu.escuelaing.arep.reflexion.microspring.URIProcessor;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author J. Eduardo Arias
 */
public class URIProcessorWriter implements ResourceWriter {
    
    
    URIProcessor up;

    public URIProcessorWriter(URIProcessor up) {
        this.up = up;
    }
     
     

    @Override
    public void write(String theuri, OutputStream out) {
        try (PrintWriter pr = new PrintWriter(out,true)) {
            String response = up.executeService(theuri);
            String header = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n";
            pr.println(header + response);
            pr.close();
        }
    }

    @Override
    public String exactType() {
        return "text/html";
    }

   
    
}
