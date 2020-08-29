/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpwriters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author J. Eduardo Arias
 */
public class TextFileWriter implements ResourceWriter{    
    private final String type;

    public TextFileWriter(String type) {
        this.type = type;
    }    
    
    /**
     * Escribe un archivo de texto utilizando el socket del cliente
     * @param file Debe ser el path del archivo relativo a raiz
     * @param outputStream     * 
     */
    @Override
    public void write(String file, OutputStream outputStream) {
        PrintWriter out;
        try {
            String outputLine;
            out = new PrintWriter(outputStream, true);           
            BufferedReader bf = new BufferedReader(new FileReader(file));
            outputLine = "HTTP/1.1 200 OK\r\n";
            outputLine+="Content-Type: text/"+type+"\r\n";
            outputLine+="\r\n\r\n";
            String bfRead;
            while ((bfRead = bf.readLine()) != null) {
                outputLine+=bfRead;
            }
            out.println(outputLine);
            out.close();
        } catch (IOException ex) {
            new ErrorWriter("404 NOT FOUND").write("", outputStream);
        }         
    }     

    @Override
    public String exactType() {
        return "text/"+type;
    }
    
}
