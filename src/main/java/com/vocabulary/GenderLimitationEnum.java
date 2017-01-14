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
public class GenderLimitationEnum extends BaseDatatype{
        /**
     * The RDF-friendly version of the GenderLimitationEnum namespace
 with trailing # character.
     */
    
    
    private static String GLE="/GenderLimitation";
    
    /** Resource URI for GenderLimitationEnum:maleOnly */
    public static Resource maleOnly;
    
    /** Resource URI for GenderLimitationEnum:femaleOnly */
    public static Resource femaleOnly;
    
    /** Resource URI for GenderLimitationEnum:both */
    public static Resource both;
    
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + GLE;
    }
   
    // Initializer
    static {
        maleOnly = ResourceFactory.createResource(GLE + "#maleOnly");
        femaleOnly = ResourceFactory.createResource(GLE + "#femaleOnly");
        both = ResourceFactory.createResource(GLE + "#both");     
    }
    
    public GenderLimitationEnum() {
        super(GLE);
    }
}
