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
public class DirectionOfUseEnum extends BaseDatatype{
    /**
     * The RDF-friendly version of the DirectionOfUseEnum namespace
 with trailing # character.
     */
    
    
    private static String DOUE="/DirectionOfUse";
    
    /** Resource URI for DirectionOfUseEnum:audioInformation */
    public static Resource up;
    
    /** Resource URI for DirectionOfUseEnum:audioForHearingImpaired */
    public static Resource down;
    
    /** Resource URI for DirectionOfUseEnum:visualDisplays */
    public static Resource level;
    
    /** Resource URI for DirectionOfUseEnum:displaysForVisuallyImpaired */
    public static Resource both;

    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + DOUE;
    }
   
    // Initializer
    static {
        up = ResourceFactory.createResource(DOUE + "#up");
        down = ResourceFactory.createResource(DOUE + "#down");
        level = ResourceFactory.createResource(DOUE + "#level");
        both = ResourceFactory.createResource(DOUE + "#both");
    }
    
    public DirectionOfUseEnum() {
        super(DOUE);
    }
}
