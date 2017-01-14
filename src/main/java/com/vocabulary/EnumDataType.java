/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vocabulary;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;


/**
 *
 * @author elena
 */
public class EnumDataType {
    
    public static Resource acc_info;
    public static Resource pt_info_fac;
    public static Resource dir_of_use;
    public static Resource handrail;
    public static Resource ticketing;
    public static Resource ticket_type;
    public static Resource pay_met;
    public static Resource sanitary_fac;
    public static Resource gender_lim;
    public static Resource fare_class;
        
    
    static {
        acc_info = ResourceFactory.createResource(AccessibilityInfoEnum.uri());
        pt_info_fac = ResourceFactory.createResource(PTInfoFacilityEnum.uri());
        dir_of_use = ResourceFactory.createResource(DirectionOfUseEnum.uri());
        handrail = ResourceFactory.createResource(HandrailEnum.uri());
        ticketing = ResourceFactory.createResource(TicketingEnum.uri());
        ticket_type = ResourceFactory.createResource(TicketTypeEnum.uri());
        pay_met = ResourceFactory.createResource(PaymentMethodEnum.uri());
        sanitary_fac = ResourceFactory.createResource(SanitaryFacilityEnum.uri());
        gender_lim = ResourceFactory.createResource(GenderLimitationEnum.uri());
        fare_class = ResourceFactory.createResource(FareClassEnum.uri());
        
    }
}
