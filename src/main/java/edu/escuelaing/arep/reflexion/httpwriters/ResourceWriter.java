/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpwriters;

import java.io.OutputStream;

/**
 *
 * @author J. Eduardo Arias
 */
public interface ResourceWriter {
    
    /**
     * Permite responder el recurso solicitado.
     * @param file path del archivo
     * @param clientSocket para escribir ahi
     */
    public void write(String file,OutputStream outputStream);
    /**
     * @return Devuelve el tipo exacto de ResourceWriter
     */
    public String exactType();
}
