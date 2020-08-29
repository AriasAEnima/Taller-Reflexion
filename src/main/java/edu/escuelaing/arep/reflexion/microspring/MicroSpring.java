/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.escuelaing.arep.reflexion.microspring;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author J. Eduardo Arias
 */
public class MicroSpring implements URIProcessor{
    private static Map<String,Method> webservices;
    
    @Override
    public void mapService(String componentName) throws Exception {
      // java -cp target/classes edu.escuelaing.arep.reflexionlab.RunTests edu.escuelaing.arep.reflexionlab.Foo
      int nMethod =0 ;
      webservices=new HashMap<>();

        for (Method m : Class.forName(componentName).getMethods()) {

            if (m.isAnnotationPresent(RequestMapping.class)) {                
                RequestMapping rm=m.getAnnotation(RequestMapping.class);
                webservices.put(rm.value(),m);
                nMethod++;
                
            }
        }

      System.out.printf("Metodos encontrados: %d \n", nMethod);

   }
    
    @Override
   public String executeService(String theuri){
       String a="error";
        try {
            a= webservices.get(theuri).invoke(null).toString();
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(MicroSpring.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
   }

   

 
}
