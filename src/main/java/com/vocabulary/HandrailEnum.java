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
public class HandrailEnum extends BaseDatatype{
    /**
     * The RDF-friendly version of the HandrailEnum namespace
 with trailing # character.
     */
    
    
    private static String HE="/Handrail";
    
    /** Resource URI for HandrailEnum:audioInformation */
    public static Resource none;
    
    /** Resource URI for HandrailEnum:audioForHearingImpaired */
    public static Resource oneSide;
    
    /** Resource URI for HandrailEnum:visualDisplays */
    public static Resource bothSide;

    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + HE;
    }
   
    // Initializer
    static {
        none = ResourceFactory.createResource(HE + "#none");
        oneSide = ResourceFactory.createResource(HE + "#oneSide");
        bothSide = ResourceFactory.createResource(HE + "#bothSide");
    }
    
    public HandrailEnum() {
        super(HE);
    }
}
