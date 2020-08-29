/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.httpserver;

import edu.escuelaing.arep.reflexion.httpwriters.ResourceChooser;
import edu.escuelaing.arep.reflexion.httpwriters.ResourceWriter;
import edu.escuelaing.arep.reflexion.httpwriters.URIProcessorWriter;
import edu.escuelaing.arep.reflexion.microspring.URIProcessor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author J. Eduardo Arias
 */
public class ServerHttp {
    
    
    URIProcessor up;

    public ServerHttp(URIProcessor up) {
        this.up = up;
    }    
    

    public void start() {
        try {
            ServerSocket serverSocket = null;

            try {
                serverSocket = new ServerSocket(getPort());
            } catch (IOException e) {
                System.err.println("Could not listen on port: " + getPort());
                System.exit(1);
            }

            boolean running = true;
            while (running) {
                try {
                    Socket clientSocket = null;
                    try {
                        System.out.println("Listo para recibir en puerto: "+  getPort()+" ...");
                        clientSocket = serverSocket.accept();
                    } catch (IOException e) {
                        System.err.println("Accept failed.");
                        System.exit(1);
                    }

                    processRequest(clientSocket);

                    clientSocket.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerHttp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerHttp.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
 
    private void processRequest(Socket clientSocket) throws IOException {
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()))) {
            String inputLine;
            Map<String, String> request = new HashMap<>();
            boolean requestLineReady = false;
            while ((inputLine = in.readLine()) != null) {
                if (!requestLineReady) {
                    request.put("requestLine", inputLine);
                    requestLineReady = true;
                } else {
                    String[] entry = createEntry(inputLine);
                    if (entry.length > 1) {
                        request.put(entry[0], entry[1]);
                    }
                }
                if (!in.ready()) {
                    break;
                }
            }
            Request req = new Request(request.get("requestLine"));
            
            System.out.println("RequestLine: " + req);
            
            createResponse(req, clientSocket.getOutputStream());
        }
    }

    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    private void createResponse(Request req, OutputStream out) { 
        String finalresource;         
        URI theuri = req.getTheuri();
        ResourceWriter rw;
        try {
            if (theuri.getPath().startsWith("/Apps")) {
                finalresource = theuri.getPath().substring(5);
                rw = new URIProcessorWriter(up);
            } else {
                finalresource = "examples"+theuri.getPath();
                rw = ResourceChooser.choose(finalresource);
            }
            System.out.println("Path final: "+finalresource);
           rw.write(finalresource, out);
        } catch (Exception ex) {
            Logger.getLogger(ServerHttp.class.getName()).log(Level.SEVERE, null, ex);
        }       
        
    }   
   
     static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 35000;
    }
}
