/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vocabulary;

import com.uris.URI_Access;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

/**
 *
 * @author elena
 */
public class FareClassEnum extends BaseDatatype{
        /**
     * The RDF-friendly version of the FareClassEnum namespace
 with trailing # character.
     */
    
    
    private static String FCE="/FareClass";
    
    /** Resource URI for FareClassEnum:firstClass */
    public static Resource firstClass;
    
    /** Resource URI for FareClassEnum:secondClass */
    public static Resource secondClass;
    
    /** Resource URI for FareClassEnum:thirdClass */
    public static Resource thirdClass;
    
    /** Resource URI for FareClassEnum:economyClass */
    public static Resource economyClass;
    
    /** Resource URI for FareClassEnum:businessClass */
    public static Resource businessClass;
    
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + FCE;
    }
   
    // Initializer
    static {
        firstClass = ResourceFactory.createResource(FCE + "#firstClass");
        secondClass = ResourceFactory.createResource(FCE + "#secondClass");
        thirdClass = ResourceFactory.createResource(FCE + "#thirdClass");
        economyClass = ResourceFactory.createResource(FCE + "#economyClass");
        businessClass = ResourceFactory.createResource(FCE + "#businessClass");
    }
    
    public FareClassEnum() {
        super(FCE);
    }
}
