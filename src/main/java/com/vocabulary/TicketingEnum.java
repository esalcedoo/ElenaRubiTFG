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
public class TicketingEnum extends BaseDatatype{
    /**
     * The RDF-friendly version of the TicketingEnum namespace
 with trailing # character.
     */
    
    
    private static String TE="/Ticketing";
    
    /** Resource URI for TicketingEnum:purchase */
    public static Resource purchase;
    
    /** Resource URI for TicketingEnum:collection */
    public static Resource collection;
    
    /** Resource URI for TicketingEnum:cardTopUp */
    public static Resource cardTopUp;

    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + TE;
    }
   
    // Initializer
    static {
        purchase = ResourceFactory.createResource(TE + "#purchase");
        collection = ResourceFactory.createResource(TE + "#collection");
        cardTopUp = ResourceFactory.createResource(TE + "#cardTopUp");
    }
    
    public TicketingEnum() {
        super(TE);
    }
}
