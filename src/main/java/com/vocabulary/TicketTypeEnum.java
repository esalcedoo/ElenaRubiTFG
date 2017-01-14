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
public class TicketTypeEnum extends BaseDatatype{
        /**
     * The RDF-friendly version of the TicketTypeEnum namespace
 with trailing # character.
     */
    
    
    private static String TTE="/TicketType";
    
    /** Resource URI for TicketTypeEnum:standard */
    public static Resource standard;
    
    /** Resource URI for TicketTypeEnum:concession */
    public static Resource concession;
    
    /** Resource URI for TicketTypeEnum:promotion */
    public static Resource promotion;

    /** Resource URI for TicketTypeEnum:group */
    public static Resource group;
    
    /** Resource URI for TicketTypeEnum:season */
    public static Resource season;
    
    /** Resource URI for TicketTypeEnum:travelCard */
    public static Resource travelCard;
    
    /** Resource URI for TicketTypeEnum:other */
    public static Resource other;
    
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + TTE;
    }
   
    // Initializer
    static {
        standard = ResourceFactory.createResource(TTE + "#standard");
        concession = ResourceFactory.createResource(TTE + "#concession");
        promotion = ResourceFactory.createResource(TTE + "#promotion");
        group = ResourceFactory.createResource(TTE + "#group");
        season = ResourceFactory.createResource(TTE + "#season");
        travelCard = ResourceFactory.createResource(TTE + "#travelCard");
        other = ResourceFactory.createResource(TTE + "#other");
    }
    
    public TicketTypeEnum() {
        super(TTE);
    }
}