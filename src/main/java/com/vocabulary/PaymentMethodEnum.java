/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vocabulary;

import com.uris.URI_Access;
import org.apache.jena.datatypes.BaseDatatype;
import org.apache.jena.rdf.model.Resource;

/**
 *
 * @author elena
 */
public class PaymentMethodEnum extends BaseDatatype{
    /**
     * The RDF-friendly version of the PaymentMethodEnum namespace
 with trailing # character.
     */
    
    
    private static String PME="/PaymentMethod";
    
    /** Resource URI for PaymentMethodEnum:cash */
    public static Resource cash;
    
    /** Resource URI for PaymentMethodEnum:creditCard */
    public static Resource creditCard;
    
    /** Resource URI for PaymentMethodEnum:debitCard */
    public static Resource debitCard;

    /** Resource URI for PaymentMethodEnum:travelCard */
    public static Resource travelCard;
    
    /** Resource URI for PaymentMethodEnum:contactlessTravelCard */
    public static Resource contactlessTravelCard;
    
    /** Resource URI for PaymentMethodEnum:mobilePhone */
    public static Resource mobilePhone;
    
    /** Resource URI for PaymentMethodEnum:token */
    public static Resource token;
    
    /** Resource URI for PaymentMethodEnum:other */
    public static Resource other;
    
    public static String uri(){
        URI_Access uriAccess=new URI_Access();
        return uriAccess.get_URI("EDT") + PME;
    }
   
    // Initializer
    static {
//        cash = ResourceFactory.createResource(PME + "#cash");
//        creditCard = ResourceFactory.createResource(PME + "#creditCard");
//        debitCard = ResourceFactory.createResource(PME + "#debitCard");
//        travelCard = ResourceFactory.createResource(PME + "#travelCard");
//        contactlessTravelCard = ResourceFactory.createResource(PME + "#contactlessTravelCard");
//        mobilePhone = ResourceFactory.createResource(PME + "#mobilePhone");
//        token = ResourceFactory.createResource(PME + "#token");
//        other = ResourceFactory.createResource(PME + "#other");
    }
    
    public PaymentMethodEnum() {
        super(PME);
    }
}
