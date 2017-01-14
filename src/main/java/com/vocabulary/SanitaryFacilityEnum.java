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
public class SanitaryFacilityEnum extends BaseDatatype{
        /**
     * The RDF-friendly version of the SanitaryFacilityEnum namespace
 with trailing # character.
     */
    
    
    private static String SFE="/SanitaryFacility";
    
    /** Resource URI for SanitaryFacilityEnum:toilet */
    public static Resource toilet;
    
    /** Resource URI for SanitaryFacilityEnum:wheelchairAccessToilet */
    public static Resource wheelchairAccessToilet;
    
    /** Resource URI for SanitaryFacilityEnum:shower */
    public static Resource shower;

    /** Resource URI for SanitaryFacilityEnum:babychange */
    public static Resource babychange;
    
    /** Resource URI for SanitaryFacilityEnum:wheelchairBabyChange */
    public static Resource wheelchairBabyChange;
    
    /** Resource URI for SanitaryFacilityEnum:other */
    public static Resource other;
    
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + SFE;
    }
   
    // Initializer
    static {
        toilet = ResourceFactory.createResource(SFE + "#toilet");
        wheelchairAccessToilet = ResourceFactory.createResource(SFE + "#wheelchairAccessToilet");
        shower = ResourceFactory.createResource(SFE + "#shower");
        babychange = ResourceFactory.createResource(SFE + "#babychange");
        wheelchairBabyChange = ResourceFactory.createResource(SFE + "#wheelchairBabyChange");
        other = ResourceFactory.createResource(SFE + "#other");
    }
    
    public SanitaryFacilityEnum() {
        super(SFE);
    }
}
