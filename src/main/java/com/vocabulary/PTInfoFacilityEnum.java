/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vocabulary;

/**
 *
 * @author elena
 */

import com.uris.URI_Access;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

/**
 * Defines Jena resources corresponding to the URIs for 
 * the XSD primitive datatypes which are known to Jena. 
 */
public class PTInfoFacilityEnum extends BaseDatatype{    
    /**
     * The RDF-friendly version of the PTInfoFacilityEnum namespace
 with trailing # character.
     */
    
    
    private static String PTIFE="/PTInfoFacility";
    
    /** Resource URI for PTInfoFacilityEnum:timetablePoster */
    public static Resource timetablePoster;
    
    /** Resource URI for PTInfoFacilityEnum:fareInoformation */
    public static Resource fareInoformation;
    
    /** Resource URI for PTInfoFacilityEnum:lineNetworkPlan */
    public static Resource lineNetworkPlan;
    
    /** Resource URI for PTInfoFacilityEnum:lineTimeTable */
    public static Resource lineTimeTable;
       
    /** Resource URI for PTInfoFacilityEnum:stopTimetable */
    public static Resource stopTimetable;
       
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + PTIFE;
    }
   
    // Initializer
    static {
        timetablePoster = ResourceFactory.createResource(PTIFE + "#timetablePoster");
        fareInoformation = ResourceFactory.createResource(PTIFE + "#fareInoformation");
        lineNetworkPlan = ResourceFactory.createResource(PTIFE + "#lineNetworkPlan");
        lineTimeTable = ResourceFactory.createResource(PTIFE + "#lineTimeTable");
        stopTimetable = ResourceFactory.createResource(PTIFE + "#stopTimetable");
    }

    public PTInfoFacilityEnum() {
        super(PTIFE);
    }
}

