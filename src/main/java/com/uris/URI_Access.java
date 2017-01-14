/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uris;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elena
 */
public class URI_Access {
    
    public static String LANG = "en";
    /**
     *
     * @param name
     * @return
     */
    public String get_URI(String name){
        Properties urls = new Properties();
        String uri = null;
//        String propFileName = "uri.properties";
//        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
//        
//        if (inputStream != null) {
        
//            try (InputStream in = new FileInputStream("C:\\Users\\elena.salcedo\\Dropbox\\AA-Clase\\TFGesalcedoo\\esalcedooTFG\\metroAccesibilityTFG\\src\\main\\java\\com\\uris\\uri.properties")){
            try (InputStream in = new FileInputStream("C:\\Users\\elena\\Dropbox\\AA-Clase\\TFGesalcedoo\\esalcedooTFG\\metroAccesibilityTFG\\src\\main\\java\\com\\uris\\uri.properties")){
                urls.load(in);
                uri=  urls.getProperty(name);
        
            } catch (IOException ex) {
                Logger.getLogger(URI_Access.class.getName()).log(Level.SEVERE, null, ex);
            }
//        } else {
//            try {
//                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath"
//                        + getClass().getClassLoader());
//            } catch (FileNotFoundException ex) {
//                Logger.getLogger(URI_Access.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        return uri;
    }
}
