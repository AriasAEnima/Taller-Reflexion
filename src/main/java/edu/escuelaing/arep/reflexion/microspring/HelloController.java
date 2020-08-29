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
public class HelloController {

 

    @RequestMapping("/hello")
    public static String greetings() {
        return "Greetings from Spring Boot!";
    }
        
    @RequestMapping("/pi")
    public static String theValueOfPi() {
        return "PI: " + Math.PI;
    }
        
        @RequestMapping("/webapp")
    public static String pagina() {
        String outputLine = 
                  "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Valor de EULER</h1>\n"
                + "<p>" + Math.E + "</p>"      
                + "</body>\n"
                + "</html>\n";
                return outputLine;
    }
}