/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpwriters;

import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author J. Eduardo Arias
 */
public class QueryUserDatabaseWriter implements ResourceWriter{

   

    @Override
    public String exactType() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void write(String file, OutputStream outputStream) {
        try (PrintWriter out = new PrintWriter(outputStream, true)) {
            String outputLine = "HTTP/1.1 200 OK\r\n"
                    + "Content-Type: text/html\r\n"
                    + "\r\n\r\n"
                    + "<!DOCTYPE html>"
                    + "<html>"
                    + "<head>"
                    + "<meta charset=\"UTF-8\">"
                    + "<title>" + "Hi " + "</title>\n"
                    + "</head>"
                    + "<body>"
                    + "<h1>Function " + "Hi " + "</h1>"
                    + "</body>"
                    + "</html>";
            out.println(outputLine);
            out.close();
        }
    }
    
}
