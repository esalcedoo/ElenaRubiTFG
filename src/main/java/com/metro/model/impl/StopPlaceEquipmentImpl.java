/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model.impl;

import com.metro.model.StopPlaceEquipment;
import com.vocabulary.EnumDataType;
import java.util.HashMap;
import java.util.Map;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.XSD;
import org.springframework.stereotype.Service;

/**
 *
 * @author elena.salcedo
 */
@Service
public class StopPlaceEquipmentImpl implements StopPlaceEquipment {

    private OntClass stopPlaceEquipment_class;
    private OntClass passengerInfoEquipment_class;
    private OntClass sign_class;
    private OntClass stopPlaceSign_class;
    private OntClass headingSign_class;
    private OntClass otherSign_class;
    private OntClass accessEquipment_class;
    private OntClass queueingEquipment_class;
    private OntClass crossingEquipment_class;
    private OntClass stairEquipment_class;
    private OntClass travelatorEquipment_class;
    private OntClass escalatorEquipment_class;
    private OntClass staircaseEquipment_class;
    private OntClass liftEquipment_class;
    private OntClass rampEquipment_class;
    private OntClass entranceEquipment_class;
    private OntClass ticketingEquipment_class;
    private OntClass luggageLockerEquipment_class;
    private OntClass waitingEquipment_class;
    OntModel currentOntology;
    
    private Individual stopPlaceEquipment;
    private Map<String,OntProperty> properties = new HashMap<>();

    public StopPlaceEquipmentImpl(){}
    
    public StopPlaceEquipmentImpl(OntModel ontology, String name) {
        currentOntology = ontology;

        // Stop Place Ontology Classes
        
        stopPlaceEquipment_class = ontology.createClass(uriAccess.get_URI("SPE"));
        stopPlaceEquipment_class.addSuperClass(RDFS.Resource);

        stopPlaceEquipment = ontology.createIndividual(uriAccess.get_URI("SPE")+"#"+name, stopPlaceEquipment_class);
        stopPlaceEquipment.addLabel(name, lang);

        // Stop Place Ontology Classes
        passengerInfoEquipment_class = ontology.createClass(uriAccess.get_URI("PIE"));
        passengerInfoEquipment_class.addSuperClass(stopPlaceEquipment_class);

        sign_class = ontology.createClass(uriAccess.get_URI("S"));
        sign_class.addSuperClass(stopPlaceEquipment_class);

        stopPlaceSign_class = ontology.createClass(uriAccess.get_URI("SPS"));
        stopPlaceSign_class.addSuperClass(sign_class);

        headingSign_class = ontology.createClass(uriAccess.get_URI("HS"));
        headingSign_class.addSuperClass(sign_class);

        otherSign_class = ontology.createClass(uriAccess.get_URI("OS"));
        otherSign_class.addSuperClass(sign_class);

        accessEquipment_class = ontology.createClass(uriAccess.get_URI("AC"));
        accessEquipment_class.addSuperClass(stopPlaceEquipment_class);

        queueingEquipment_class = ontology.createClass(uriAccess.get_URI("QE"));
        queueingEquipment_class.addSuperClass(accessEquipment_class);

        crossingEquipment_class = ontology.createClass(uriAccess.get_URI("CE"));
        crossingEquipment_class.addSuperClass(accessEquipment_class);

        stairEquipment_class = ontology.createClass(uriAccess.get_URI("SE"));
        stairEquipment_class.addSuperClass(accessEquipment_class);

        travelatorEquipment_class = ontology.createClass(uriAccess.get_URI("TE"));
        travelatorEquipment_class.addSuperClass(stairEquipment_class);

        escalatorEquipment_class = ontology.createClass(uriAccess.get_URI("EE"));
        escalatorEquipment_class.addSuperClass(stairEquipment_class);

        staircaseEquipment_class = ontology.createClass(uriAccess.get_URI("SCE"));
        staircaseEquipment_class.addSuperClass(stairEquipment_class);

        liftEquipment_class = ontology.createClass(uriAccess.get_URI("LE"));
        liftEquipment_class.addSuperClass(accessEquipment_class);

        rampEquipment_class = ontology.createClass(uriAccess.get_URI("LE"));
        rampEquipment_class.addSuperClass(accessEquipment_class);

        entranceEquipment_class = ontology.createClass(uriAccess.get_URI("EE"));
        entranceEquipment_class.addSuperClass(accessEquipment_class);

        ticketingEquipment_class = ontology.createClass(uriAccess.get_URI("TIE"));
        ticketingEquipment_class.addSuperClass(accessEquipment_class);

        luggageLockerEquipment_class = ontology.createClass(uriAccess.get_URI("TIE"));
        luggageLockerEquipment_class.addSuperClass(accessEquipment_class);

        waitingEquipment_class = ontology.createClass(uriAccess.get_URI("WE"));
        waitingEquipment_class.addSuperClass(accessEquipment_class);

        waitingEquipment_class = ontology.createClass(uriAccess.get_URI("PSE"));
        waitingEquipment_class.addSuperClass(accessEquipment_class);
        
    }

    @Override
    public boolean isAccesibleForHearingImpaired() {
        return true;
    }

    @Override
    public boolean isAccesibleForVisuallyImpaired() {
        return true;
    }

    @Override
    public boolean isAccesibleForPhysicallyHandicapped() {
        return true;
    }

    @Override
    public void addLiteralPassengerInfoFacilityType_property(Literal data/*, String label*/) {
        System.out.println("Entra en addLiteralPassengerInfoFacilityType_property");
        String label = "Passenger Information Facility Type";
        OntProperty passengerInfoFacilityType;
        // Specific passengerInfoEquipment`s properties ------------------------------------------------------------
        passengerInfoFacilityType = currentOntology.createOntProperty(uriAccess.get_URI("PIE_PIFT"));
        passengerInfoFacilityType.addComment(label, lang);
        passengerInfoFacilityType.addLabel(label, lang);
        passengerInfoFacilityType.addDomain(passengerInfoEquipment_class);
        passengerInfoFacilityType.addRange(EnumDataType.acc_info);
        this.stopPlaceEquipment.addLiteral(passengerInfoFacilityType,
                data.getString().substring(data.getString().lastIndexOf("#") + 1));
        properties.put(label, passengerInfoFacilityType);
        System.out.println("Sale de addLiteralPassengerInfoFacilityType_property");
    }
    
    @Override
    public void addLiteralInfoFacility_property(Literal data/*, String label*/) {
        String label = "Information Facility";
        // Specific passengerInfoEquipment`s properties ------------------------------------------------------------
        OntProperty infoFacility_property = currentOntology.createOntProperty(uriAccess.get_URI("PIE_IF"));
        infoFacility_property.addComment(label, lang);
        infoFacility_property.addLabel(label, lang);
        infoFacility_property.addDomain(passengerInfoEquipment_class);
        infoFacility_property.addRange(EnumDataType.pt_info_fac);
        this.stopPlaceEquipment.addLiteral(infoFacility_property, data);
        properties.put(label, infoFacility_property);
    }
    
    @Override
    public void addLiteralBrandGraphic_property(Literal data/*, String label*/) {
        String label = "Band graphic sign";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty brandGraphic_property = currentOntology.createOntProperty(uriAccess.get_URI("S_BG"));
        brandGraphic_property.addComment(label, lang);
        brandGraphic_property.addLabel(label, lang);
        brandGraphic_property.addDomain(sign_class);
        brandGraphic_property.addRange(XSD.anyURI);
        this.stopPlaceEquipment.addLiteral(brandGraphic_property, data);
        properties.put(label, brandGraphic_property);
    }
    
    @Override
    public void addLiteralSignGraphic_property(Literal data/*, String label*/) {
        String label = "Sign graphic";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty signGraphic_property = currentOntology.createOntProperty(uriAccess.get_URI("S_SG"));
        signGraphic_property.addComment(label, lang);
        signGraphic_property.addLabel(label, lang);
        signGraphic_property.addDomain(sign_class);
        signGraphic_property.addRange(XSD.anyURI);
        this.stopPlaceEquipment.addLiteral(signGraphic_property, data);
        properties.put(label, signGraphic_property);
    }
    
    @Override
    public void addLiteralPlacement_property(Literal data/*, String label*/) {
        String label = "Placement";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty placement_property = currentOntology.createOntProperty(uriAccess.get_URI("S_P"));
        placement_property.addComment(label, lang);
        placement_property.addLabel(label, lang);
        placement_property.addDomain(sign_class);
        placement_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(placement_property, data);
        properties.put(label, placement_property);
    }

    @Override
    public void addLiteralAsBraille_property(Literal data/*, String label*/) {
        String label = "As Braille";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty asBraille_property = currentOntology.createOntProperty(uriAccess.get_URI("S_AB"));
        asBraille_property.addComment(label, lang);
        asBraille_property.addLabel(label, lang);
        asBraille_property.addDomain(sign_class);
        asBraille_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(asBraille_property, data);
        properties.put(label, asBraille_property);
    }
    
    @Override
    public void addLiteralMachineReadable_property(Literal data/*, String label*/) {
        String label = "Machine Readable";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty machineReadable_property = currentOntology.createOntProperty(uriAccess.get_URI("S_MR"));
        machineReadable_property.addComment(label, lang);
        machineReadable_property.addLabel(label, lang);
        machineReadable_property.addDomain(sign_class);
        machineReadable_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(machineReadable_property, data);
        properties.put(label, machineReadable_property);
    }

    @Override
    public void addLiteralHeightSign_property(Literal data/*, String label*/) {
        String label = "Height";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty heightSign_property = currentOntology.createOntProperty(uriAccess.get_URI("S_H"));
        heightSign_property.addComment(label, lang);
        heightSign_property.addLabel(label, lang);
        heightSign_property.addDomain(sign_class);
        heightSign_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(heightSign_property, data);
        properties.put(label, heightSign_property);
    }

    @Override
    public void addLiteralWidthSign_property(Literal data/*, String label*/) {
        String label = "Width";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty widthSign_property = currentOntology.createOntProperty(uriAccess.get_URI("S_W"));
        widthSign_property.addComment(label, lang);
        widthSign_property.addLabel(label, lang);
        widthSign_property.addDomain(sign_class);
        widthSign_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(widthSign_property, data);
        properties.put(label, widthSign_property);
    }

    @Override
    public void addLiteralHeightFromFloor_property(Literal data/*, String label*/) {
        String label = "Height From Floor";
        // Specific sign`s properties ------------------------------------------------------------
        OntProperty heightFromFloor_property = currentOntology.createOntProperty(uriAccess.get_URI("S_HFF"));
        heightFromFloor_property.addComment(label, lang);
        heightFromFloor_property.addLabel(label, lang);
        heightFromFloor_property.addDomain(sign_class);
        heightFromFloor_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(heightFromFloor_property, data);
        properties.put(label, heightFromFloor_property);
    }

    @Override
    public void addLiteralPlaceName_property(Literal data/*, String label*/) {
        String label = "Place Name";
        // Specific stopPlaceSign`s properties -------------------------------------------------------
        OntProperty placeName_property = currentOntology.createOntProperty(uriAccess.get_URI("SPS_PN"));
        placeName_property.addComment(label, lang);
        placeName_property.addLabel(label, lang);
        placeName_property.addDomain(stopPlaceSign_class);
        placeName_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(placeName_property, data);
        properties.put(label, placeName_property);
    }

    @Override
    public void addLiteralDirectionName_property(Literal data/*, String label*/) {
        String label = "Direction name";
        // Specific headingSign_property`s properties ------------------------------------------------
        OntProperty directionName_property = currentOntology.createOntProperty(uriAccess.get_URI("HS_DN"));
        directionName_property.addComment(label, lang);
        directionName_property.addLabel(label, lang);
        directionName_property.addDomain(headingSign_class);
        directionName_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(directionName_property, data);
        properties.put(label, directionName_property);
    }

    @Override
    public void addLiteralLineName_property(Literal data/*, String label*/) {
        String label = "Line Name";        
        // Specific headingSign_property`s properties ------------------------------------------------
        OntProperty lineName_property = currentOntology.createOntProperty(uriAccess.get_URI("HS_LN"));
        lineName_property.addComment(label, lang);
        lineName_property.addLabel(label, lang);
        lineName_property.addDomain(headingSign_class);
        lineName_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(lineName_property, data);
        properties.put(label, lineName_property);
    }

    @Override
    public void addLiteralLineCode_property(Literal data/*, String label*/) {
        String label = "Line Code";        
        // Specific headingSign_property`s properties ------------------------------------------------
        OntProperty lineCode_property = currentOntology.createOntProperty(uriAccess.get_URI("HS_LC"));
        lineCode_property.addComment(label, lang);
        lineCode_property.addLabel(label, lang);
        lineCode_property.addDomain(headingSign_class);
        lineCode_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(lineCode_property, data);
        properties.put(label, lineCode_property);
    }

    @Override
    public void addLiteralLineMap_property(Literal data/*, String label*/) {
        String label = "Line Map";        
        // Specific headingSign_property`s properties ------------------------------------------------
        OntProperty lineMap_property = currentOntology.createOntProperty(uriAccess.get_URI("HS_LM"));
        lineMap_property.addComment("Line Map", lang);
        lineMap_property.addLabel(label, lang);
        lineMap_property.addDomain(headingSign_class);
        lineMap_property.addRange(XSD.anyURI);        
        this.stopPlaceEquipment.addLiteral(lineMap_property, data);
        properties.put(label, lineMap_property);
    }

    @Override
    public void addLiteralLineMode_property(Literal data/*, String label*/) {
        String label = "Line Mode";        
        // Specific headingSign_property`s properties ------------------------------------------------
        OntProperty lineMode_property = currentOntology.createOntProperty(uriAccess.get_URI("HS_LMO"));
        lineMode_property.addComment("Line Mode", lang);
        lineMode_property.addLabel(label, lang);
        lineMode_property.addDomain(headingSign_class);
        lineMode_property.addRange(XSD.anyURI);//TRANSPORT MODE
        this.stopPlaceEquipment.addLiteral(lineMode_property, data);
        properties.put(label, lineMode_property);
    }

    @Override
    public void addLiteralContent_property(Literal data/*, String label*/) {
        String label = "Direction Name";        
        // Specific otherSign_property`s properties ---------------------------------------------
        OntProperty content_property = currentOntology.createOntProperty(uriAccess.get_URI("OS_C"));
        content_property.addComment("Direction Name", lang);
        content_property.addLabel(label, lang);
        content_property.addDomain(otherSign_class);
        content_property.addRange(XSD.xstring);
        this.stopPlaceEquipment.addLiteral(content_property, data);
        properties.put(label, content_property);
    }

    @Override
    public void addLiteralWidthAccess_property(Literal data/*, String label*/) {
        String label = "Width";        
        // Specific accessEquipment`s properties ---------------------------------------------
        OntProperty widthAccess_property = currentOntology.createOntProperty(uriAccess.get_URI("AC_W"));
        widthAccess_property.addComment("Width", lang);
        widthAccess_property.addLabel(label, lang);
        widthAccess_property.addDomain(accessEquipment_class);
        widthAccess_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(widthAccess_property, data);
        properties.put(label, widthAccess_property);
    }

    @Override
    public void addLiteralPathDirection_property(Literal data/*, String label*/) {
        String label = "Path Direction";        
        // Specific accessEquipment`s properties ---------------------------------------------
        OntProperty pathDirection_property = currentOntology.createOntProperty(uriAccess.get_URI("AC_PD"));
        pathDirection_property.addComment("Path Direction", lang);
        pathDirection_property.addLabel(label, lang);
        pathDirection_property.addDomain(accessEquipment_class);
        pathDirection_property.addRange(EnumDataType.dir_of_use);
        this.stopPlaceEquipment.addLiteral(pathDirection_property, data);
        properties.put(label, pathDirection_property);
    }

    @Override
    public void addLiteralPassengersPerMinute_property(Literal data/*, String label*/) {
        String label = "Passengers Per Minute";        
        // Specific accessEquipment`s properties ---------------------------------------------
        OntProperty passengersPerMinute_property = currentOntology.createOntProperty(uriAccess.get_URI("AC_PPM"));
        passengersPerMinute_property.addComment("Passengers Per Minute", lang);
        passengersPerMinute_property.addLabel(label, lang);
        passengersPerMinute_property.addDomain(accessEquipment_class);
        passengersPerMinute_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(passengersPerMinute_property, data);
        properties.put(label, passengersPerMinute_property);
    }

    @Override
    public void addLiteralRelativeWeighting_property(Literal data/*, String label*/) {
        String label = "Relative Weighting";        
        // Specific accessEquipment`s properties ---------------------------------------------
        OntProperty relativeWeighting_property = currentOntology.createOntProperty(uriAccess.get_URI("AC_RW"));
        relativeWeighting_property.addComment("Relative Weighting", lang);
        relativeWeighting_property.addLabel(label, lang);
        relativeWeighting_property.addDomain(accessEquipment_class);
        relativeWeighting_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(relativeWeighting_property, data);
        properties.put(label, relativeWeighting_property);
    }

    @Override
    public void addLiteralRailedQueue_property(Literal data/*, String label*/) {
        String label = "Railed Queue";        
        // Specific queueingEquipment`s properties ---------------------------------------------
        OntProperty railedQueue_property = currentOntology.createOntProperty(uriAccess.get_URI("QE_RQ"));
        railedQueue_property.addComment("Railed Queue", lang);
        railedQueue_property.addLabel(label, lang);
        railedQueue_property.addDomain(queueingEquipment_class);
        railedQueue_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(railedQueue_property, data);
        properties.put(label, railedQueue_property);
    }

    @Override
    public void addLiteralNumberOfServers_property(Literal data/*, String label*/) {
        String label = "Number Of Servers";        
        // Specific queueingEquipment`s properties ---------------------------------------------
        OntProperty numberOfServers_property = currentOntology.createOntProperty(uriAccess.get_URI("QE_NOS"));
        numberOfServers_property.addComment("Number Of Servers", lang);
        numberOfServers_property.addLabel(label, lang);
        numberOfServers_property.addDomain(queueingEquipment_class);
        numberOfServers_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(numberOfServers_property, data);
        properties.put(label, numberOfServers_property);
    }
    
    @Override
    public void addLiteralTicketedQueue_property(Literal data/*, String label*/) {
        String label = "Ticketed Queue";        
        // Specific queueingEquipment`s properties ---------------------------------------------
        OntProperty ticketedQueue_property = currentOntology.createOntProperty(uriAccess.get_URI("QE_TQ"));
        ticketedQueue_property.addComment("Ticketed Queue", lang);
        ticketedQueue_property.addLabel(label, lang);
        ticketedQueue_property.addDomain(queueingEquipment_class);
        ticketedQueue_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(ticketedQueue_property, data);
        properties.put(label, ticketedQueue_property);
    }

    @Override
    public void addLiteralZebraCrossing_property(Literal data/*, String label*/) {
        String label = "Zebra Crossing";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty zebraCrossing_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_ZC"));
        zebraCrossing_property.addComment("Zebra Crossing", lang);
        zebraCrossing_property.addLabel(label, lang);
        zebraCrossing_property.addDomain(crossingEquipment_class);
        zebraCrossing_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(zebraCrossing_property, data);
        properties.put(label, zebraCrossing_property);
    }

    @Override
    public void addLiteralPedestrianLights_property(Literal data/*, String label*/) {
        String label = "Pedestrian Lights";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty pedestrianLights_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_PL"));
        pedestrianLights_property.addComment("Pedestrian Lights", lang);
        pedestrianLights_property.addLabel(label, lang);
        pedestrianLights_property.addDomain(crossingEquipment_class);
        pedestrianLights_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(pedestrianLights_property, data);
        properties.put(label, pedestrianLights_property);
    }

    @Override
    public void addLiteralAcousticDeviceSensors_property(Literal data/*, String label*/) {
        String label = "Acoustic Device Sensors";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty acousticDeviceSensors_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_ADS"));
        acousticDeviceSensors_property.addComment("Acoustic Device Sensors", lang);
        acousticDeviceSensors_property.addLabel(label, lang);
        acousticDeviceSensors_property.addDomain(crossingEquipment_class);
        acousticDeviceSensors_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(acousticDeviceSensors_property, data);
        properties.put(label, acousticDeviceSensors_property);
    }

    @Override
    public void addLiteralAcousticCrossingAid_property(Literal data/*, String label*/) {
        String label = "Acoustic Crossing Aid";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty acousticCrossingAid_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_ADS"));
        acousticCrossingAid_property.addComment("Acoustic Crossing Aid", lang);
        acousticCrossingAid_property.addLabel(label, lang);
        acousticCrossingAid_property.addDomain(crossingEquipment_class);
        acousticCrossingAid_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(acousticCrossingAid_property, data);
        properties.put(label, acousticCrossingAid_property);
    }

    @Override
    public void addLiteralVisualGuianceBands_property(Literal data/*, String label*/) {
        String label = "Visual Guiance Bands";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty visualGuianceBands_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_ADS"));
        visualGuianceBands_property.addComment("Visual Guiance Bands", lang);
        visualGuianceBands_property.addLabel(label, lang);
        visualGuianceBands_property.addDomain(crossingEquipment_class);
        visualGuianceBands_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(visualGuianceBands_property, data);
        properties.put(label, visualGuianceBands_property);
    }
    
    @Override
    public void addLiteralTactileGuideStripsCrossing_property(Literal data/*, String label*/) {
        String label = "Tactile Guide Strips";        
        // Specific crossingEquipment`s properties ---------------------------------------------
        OntProperty tactileGuideStrips_property = currentOntology.createOntProperty(uriAccess.get_URI("CE_ADS"));
        tactileGuideStrips_property.addComment("Tactile Guide Strips", lang);
        tactileGuideStrips_property.addLabel(label, lang);
        tactileGuideStrips_property.addDomain(crossingEquipment_class);
        tactileGuideStrips_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(tactileGuideStrips_property, data);
        properties.put(label, tactileGuideStrips_property);
    }

    @Override
    public void addLiteralDepthStair_property(Literal data/*, String label*/) {
        String label = "Depth Stair";        
        // Specific stairEquipment`s properties ---------------------------------------------
        OntProperty depthStair_property = currentOntology.createOntProperty(uriAccess.get_URI("SE_D"));
        depthStair_property.addComment("Depth Stair", lang);
        depthStair_property.addLabel(label, lang);
        depthStair_property.addDomain(stairEquipment_class);
        depthStair_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(depthStair_property, data);
        properties.put(label, depthStair_property);
    }

    @Override
    public void addLiteralNumberOfSteps_property(Literal data/*, String label*/) {
        String label = "Number Of Steps";        
        // Specific stairEquipment`s properties ---------------------------------------------
        OntProperty numberOfSteps_property = currentOntology.createOntProperty(uriAccess.get_URI("SE_NOS"));
        numberOfSteps_property.addComment("Number Of Steps", lang);
        numberOfSteps_property.addLabel(label, lang);
        numberOfSteps_property.addDomain(stairEquipment_class);
        numberOfSteps_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(numberOfSteps_property, data);
        properties.put(label, numberOfSteps_property);
    }

    @Override
    public void addLiteralTactileActuatorsStair_property(Literal data/*, String label*/) {
        String label = "Tactile Actuators Stair";        
        // Specific stairEquipment`s properties ---------------------------------------------
        OntProperty tactileActuatorsStair_property = currentOntology.createOntProperty(uriAccess.get_URI("SE_NOS"));
        tactileActuatorsStair_property.addComment("Tactile Actuators Stair", lang);
        tactileActuatorsStair_property.addLabel(label, lang);
        tactileActuatorsStair_property.addDomain(stairEquipment_class);
        tactileActuatorsStair_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(tactileActuatorsStair_property, data);
        properties.put(label, tactileActuatorsStair_property);
    }

    @Override
    public void addLiteralEnergySavingTravelator_property(Literal data/*, String label*/) {
        String label = "Energy Saving";
        // Specific travelatorEquipment`s properties ---------------------------------------------
        OntProperty energySavingTravelator_property = currentOntology.createOntProperty(uriAccess.get_URI("TE_ES"));
        energySavingTravelator_property.addComment("Energy Saving", lang);
        energySavingTravelator_property.addLabel(label, lang);
        energySavingTravelator_property.addDomain(travelatorEquipment_class);
        energySavingTravelator_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(energySavingTravelator_property, data);
        properties.put(label, energySavingTravelator_property);
    }

    @Override
    public void addLiteralTactileActuatorsTravelator_property(Literal data/*, String label*/) {
        String label = "Tactile Actuators";
        // Specific travelatorEquipment`s properties ---------------------------------------------
        OntProperty tactileActuators_property = currentOntology.createOntProperty(uriAccess.get_URI("TE_TA"));
        tactileActuators_property.addComment("Tactile Actuators", lang);
        tactileActuators_property.addLabel(label, lang);
        tactileActuators_property.addDomain(travelatorEquipment_class);
        tactileActuators_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(tactileActuators_property, data);
        properties.put(label, tactileActuators_property);
    }

    @Override
    public void addLiteralSpeed_property(Literal data/*, String label*/) {
        String label = "Speed";
        // Specific travelatorEquipment`s properties ---------------------------------------------
        OntProperty speed_property = currentOntology.createOntProperty(uriAccess.get_URI("TE_S"));
        speed_property.addComment("Speed", lang);
        speed_property.addLabel(label, lang);
        speed_property.addDomain(travelatorEquipment_class);
        speed_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(speed_property, data);
        properties.put(label, speed_property);
    }

    @Override
    public void addLiteralEnergySavingEscalator_property(Literal data/*, String label*/) {
        String label = "Energy Saving";
        // Specific escalatorEquipment`s properties ---------------------------------------------
        OntProperty energySavingEscalator_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_ES"));
        energySavingEscalator_property.addComment("Energy Saving", lang);
        energySavingEscalator_property.addLabel(label, lang);
        energySavingEscalator_property.addDomain(escalatorEquipment_class);
        energySavingEscalator_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(energySavingEscalator_property, data);
        properties.put(label, energySavingEscalator_property);
    }

    @Override
    public void addLiteralHandRailTypeStaircase_property(Literal data/*, String label*/) {
        String label = "Hand Rail Type";
        // Specific stairCaseEquipment`s properties ---------------------------------------------
        OntProperty handRailTypeStaircase_property = currentOntology.createOntProperty(uriAccess.get_URI("SCE_HT"));
        handRailTypeStaircase_property.addComment("Hand Rail Type", lang);
        handRailTypeStaircase_property.addLabel(label, lang);
        handRailTypeStaircase_property.addDomain(escalatorEquipment_class);
        handRailTypeStaircase_property.addRange(EnumDataType.handrail);
        this.stopPlaceEquipment.addLiteral(handRailTypeStaircase_property, data);
        properties.put(label, handRailTypeStaircase_property);
    }

    @Override
    public void addLiteralHandRailHeightStaircase_property(Literal data/*, String label*/) {
        String label = "Hand Rail Height";
        // Specific stairCaseEquipment`s properties ---------------------------------------------
        OntProperty handRailHeightStaircase_property = currentOntology.createOntProperty(uriAccess.get_URI("SCE_HH"));
        handRailHeightStaircase_property.addComment("Hand Rail Height", lang);
        handRailHeightStaircase_property.addLabel(label, lang);
        handRailHeightStaircase_property.addDomain(escalatorEquipment_class);
        handRailHeightStaircase_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(handRailHeightStaircase_property, data);
        properties.put(label, handRailHeightStaircase_property);
    }

    @Override
    public void addLiteralSpiralStair_property(Literal data/*, String label*/) {
        String label = "Spiral Stair";
        // Specific stairCaseEquipment`s properties ---------------------------------------------
        OntProperty SpiralStair_property = currentOntology.createOntProperty(uriAccess.get_URI("SCE_SS"));
        SpiralStair_property.addComment("Spiral Stair", lang);
        SpiralStair_property.addLabel(label, lang);
        SpiralStair_property.addDomain(escalatorEquipment_class);
        SpiralStair_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(SpiralStair_property, data);
        properties.put(label, SpiralStair_property);
    }

    @Override
    public void addLiteralDepthLift_property(Literal data/*, String label*/) {
        String label = "Depth Lift";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty depthLift_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_D"));
        depthLift_property.addComment("Depth Lift", lang);
        depthLift_property.addLabel(label, lang);
        depthLift_property.addDomain(liftEquipment_class);
        depthLift_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(depthLift_property, data);
        properties.put(label, depthLift_property);
    }

    @Override
    public void addLiteralMaximumLoad_property(Literal data/*, String label*/) {
        String label = "Maximum Load";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty maximumLoad_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_ML"));
        maximumLoad_property.addComment("Maximum Load", lang);
        maximumLoad_property.addLabel(label, lang);
        maximumLoad_property.addDomain(liftEquipment_class);
        maximumLoad_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(maximumLoad_property, data);
        properties.put(label, maximumLoad_property);
    }

    @Override
    public void addLiteralThroughLoader_property(Literal data/*, String label*/) {
        String label = "Through Loader";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty throughLoader_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_TL"));
        throughLoader_property.addComment("Through Loader", lang);
        throughLoader_property.addLabel(label, lang);
        throughLoader_property.addDomain(liftEquipment_class);
        throughLoader_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(throughLoader_property, data);
        properties.put(label, throughLoader_property);
    }

    @Override
    public void addLiteralMirrorOnOppositeSide_property(Literal data/*, String label*/) {
        String label = "Mirror On Opposite Side";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty mirrorOnOppositeSide_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_MOOS"));
        mirrorOnOppositeSide_property.addComment("Mirror On Opposite Side", lang);
        mirrorOnOppositeSide_property.addLabel(label, lang);
        mirrorOnOppositeSide_property.addDomain(liftEquipment_class);
        mirrorOnOppositeSide_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(mirrorOnOppositeSide_property, data);
        properties.put(label, mirrorOnOppositeSide_property);
    }

    @Override
    public void addLiteralAttendant_property(Literal data/*, String label*/) {
        String label = "Attendant";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty attendant_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_A"));
        attendant_property.addComment("Attendant", lang);
        attendant_property.addLabel(label, lang);
        attendant_property.addDomain(liftEquipment_class);
        attendant_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(attendant_property, data);
        properties.put(label, attendant_property);
    }

    @Override
    public void addLiteralAutomatic_property(Literal data/*, String label*/) {
        String label = "Automatic";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty automatic_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_AU"));
        automatic_property.addComment("Automatic", lang);
        automatic_property.addLabel(label, lang);
        automatic_property.addDomain(liftEquipment_class);
        automatic_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(automatic_property, data);
        properties.put(label, automatic_property);
    }

    @Override
    public void addLiteralTactileActuatorsLift_property(Literal data/*, String label*/) {
        String label = "Tactile Actuators Lift";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty tactileActuatorsLift_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_TA"));
        tactileActuatorsLift_property.addComment("Tactile Actuators Lift", lang);
        tactileActuatorsLift_property.addLabel(label, lang);
        tactileActuatorsLift_property.addDomain(liftEquipment_class);
        tactileActuatorsLift_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(tactileActuatorsLift_property, data);
        properties.put(label, tactileActuatorsLift_property);
    }

    @Override
    public void addLiteralAcousticAnnouncements_property(Literal data/*, String label*/) {
        String label = "Acoustic Announcements";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty acousticAnnouncements_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_AA"));
        acousticAnnouncements_property.addComment("Acoustic Announcements", lang);
        acousticAnnouncements_property.addLabel(label, lang);
        acousticAnnouncements_property.addDomain(liftEquipment_class);
        acousticAnnouncements_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(acousticAnnouncements_property, data);
        properties.put(label, acousticAnnouncements_property);
    }

    @Override
    public void addLiteralWheelchairTurningCircle_property(Literal data/*, String label*/) {
        String label = "Wheelchair Turning Circle";
        // Specific liftEquipment`s properties ---------------------------------------------
        OntProperty wheelchairTurningCircle_property = currentOntology.createOntProperty(uriAccess.get_URI("LE_WTC"));
        wheelchairTurningCircle_property.addComment("Wheelchair Turning Circle", lang);
        wheelchairTurningCircle_property.addLabel(label, lang);
        wheelchairTurningCircle_property.addDomain(liftEquipment_class);
        wheelchairTurningCircle_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(wheelchairTurningCircle_property, data);
        properties.put(label, wheelchairTurningCircle_property);
    }

    @Override
    public void addLiteralRampLenght_property(Literal data/*, String label*/) {
        String label = "Lenght";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty lenght_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_L"));
        lenght_property.addComment("Lenght", lang);
        lenght_property.addLabel(label, lang);
        lenght_property.addDomain(rampEquipment_class);
        lenght_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(lenght_property, data);
        properties.put(label, lenght_property);
    }

    @Override
    public void addLiteralGradient_property(Literal data/*, String label*/) {
        String label = "Gradient";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty gradient_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_G"));
        gradient_property.addComment("Gradient", lang);
        gradient_property.addLabel(label, lang);
        gradient_property.addDomain(rampEquipment_class);
        gradient_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(gradient_property, data);
        properties.put(label, gradient_property);
    }

    @Override
    public void addLiteralPedestal_property(Literal data/*, String label*/) {
        String label = "Pedestal";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty pedestal_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_P"));
        pedestal_property.addComment("Pedestal", lang);
        pedestal_property.addLabel(label, lang);
        pedestal_property.addDomain(rampEquipment_class);
        pedestal_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(pedestal_property, data);
        properties.put(label, pedestal_property);
    }

    @Override
    public void addLiteralHandrailTypeRamp_property(Literal data/*, String label*/) {
        String label = "Handrail Type";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty handrailTypeRamp_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_HT"));
        handrailTypeRamp_property.addComment("Handrail Type", lang);
        handrailTypeRamp_property.addLabel(label, lang);
        handrailTypeRamp_property.addDomain(rampEquipment_class);
        handrailTypeRamp_property.addRange(EnumDataType.handrail);
        this.stopPlaceEquipment.addLiteral(handrailTypeRamp_property, data);
        properties.put(label, handrailTypeRamp_property);
    }

    @Override
    public void addLiteralHandrailHeightRamp_property(Literal data/*, String label*/) {
        String label = "Handrail Height";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty handrailHeight_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_HH"));
        handrailHeight_property.addComment("Handrail Height", lang);
        handrailHeight_property.addLabel(label, lang);
        handrailHeight_property.addDomain(rampEquipment_class);
        handrailHeight_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(handrailHeight_property, data);
        properties.put(label, handrailHeight_property);
    }

    @Override
    public void addLiteralTactileGuideStripsRamp_property(Literal data/*, String label*/) {
        String label = "Tactile Guiance Strips";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty tactileGuianceStrips_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_TGS"));
        tactileGuianceStrips_property.addComment("Tactile Guiance Strips", lang);
        tactileGuianceStrips_property.addLabel(label, lang);
        tactileGuianceStrips_property.addDomain(rampEquipment_class);
        tactileGuianceStrips_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(tactileGuianceStrips_property, data);
        properties.put(label, tactileGuianceStrips_property);
    }

    @Override
    public void addLiteralTemporary_property(Literal data/*, String label*/) {
        String label = "Tactile Guiance Strips";
        // Specific rampEquipment`s properties ---------------------------------------------
        OntProperty temporary_property = currentOntology.createOntProperty(uriAccess.get_URI("RE_T"));
        temporary_property.addComment("Tactile Guiance Strips", lang);
        temporary_property.addLabel(label, lang);
        temporary_property.addDomain(rampEquipment_class);
        temporary_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(temporary_property, data);
        properties.put(label, temporary_property);
    }

    @Override
    public void addLiteralDoor_property(Literal data/*, String label*/) {
        String label = "Door";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty door_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_D"));
        door_property.addComment("Door", lang);
        door_property.addLabel(label, lang);
        door_property.addDomain(entranceEquipment_class);
        door_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(door_property, data);
        properties.put(label, door_property);
    }

    @Override
    public void addLiteralRevolvingDoor_property(Literal data/*, String label*/) {
        String label = "Revolving Door";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty revolvingDoor_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_RD"));
        revolvingDoor_property.addComment("Revolving Door", lang);
        revolvingDoor_property.addLabel(label, lang);
        revolvingDoor_property.addDomain(entranceEquipment_class);
        revolvingDoor_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(revolvingDoor_property, data);
        properties.put(label, revolvingDoor_property);
    }

    @Override
    public void addLiteralBarrier_property(Literal data/*, String label*/) {
        String label = "Barrier";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty barrier_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_B"));
        barrier_property.addComment("Barrier", lang);
        barrier_property.addLabel(label, lang);
        barrier_property.addDomain(entranceEquipment_class);
        barrier_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(barrier_property, data);
        properties.put(label, barrier_property);
    }

    @Override
    public void addLiteralNumberOfGates_property(Literal data/*, String label*/) {
        String label = "Number Of Gates";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty numberOfGates_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_NOG"));
        numberOfGates_property.addComment("Number Of Gates", lang);
        numberOfGates_property.addLabel(label, lang);
        numberOfGates_property.addDomain(entranceEquipment_class);
        numberOfGates_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(numberOfGates_property, data);
        properties.put(label, numberOfGates_property);
    }

    @Override
    public void addLiteralEntranceRequiresTicket_property(Literal data/*, String label*/) {
        String label = "Entrance Requires Ticket";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty entranceRequiresTicket_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_ERT"));
        entranceRequiresTicket_property.addComment("Entrance Requires Ticket", lang);
        entranceRequiresTicket_property.addLabel(label, lang);
        entranceRequiresTicket_property.addDomain(entranceEquipment_class);
        entranceRequiresTicket_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(entranceRequiresTicket_property, data);
        properties.put(label, entranceRequiresTicket_property);
    }

    @Override
    public void addLiteralEntranceRequiresPassport_property(Literal data/*, String label*/) {
        String label = "Entrance Requires Passport";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty entranceRequiresPassport_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_ERP"));
        entranceRequiresPassport_property.addComment("Entrance Requires Passport", lang);
        entranceRequiresPassport_property.addLabel(label, lang);
        entranceRequiresPassport_property.addDomain(entranceEquipment_class);
        entranceRequiresPassport_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(entranceRequiresPassport_property, data);
        properties.put(label, entranceRequiresPassport_property);
    }

    @Override
    public void addLiteralAcousticSensor_property(Literal data/*, String label*/) {
        String label = "Acoustic Sensor";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty acousticSensor_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_AS"));
        acousticSensor_property.addComment("Acoustic Sensor", lang);      
        acousticSensor_property.addLabel(label, lang);        
        acousticSensor_property.addDomain(entranceEquipment_class);
        acousticSensor_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(acousticSensor_property, data);
        properties.put(label, acousticSensor_property);
    }

    @Override
    public void addLiteralAutomaticDoor_property(Literal data/*, String label*/) {
        String label = "Automatic Door";
        // Specific entranceEquipment`s properties ---------------------------------------------
        OntProperty automaticDoor_property = currentOntology.createOntProperty(uriAccess.get_URI("EE_AD"));
        automaticDoor_property.addComment("Automatic Door", lang);       
        automaticDoor_property.addLabel(label, lang);
        automaticDoor_property.addDomain(entranceEquipment_class);
        automaticDoor_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(automaticDoor_property, data);
        properties.put(label, automaticDoor_property);
    }

    @Override
    public void addLiteralTicketMachines_property(Literal data/*, String label*/) {
        String label = "Ticket Machines";
        // Specific ticketingEquipment`s properties ---------------------------------------------
        OntProperty ticketMachines_property = currentOntology.createOntProperty(uriAccess.get_URI("TIE_TM"));
        ticketMachines_property.addComment("Ticket Machines", lang);       
        ticketMachines_property.addLabel(label, lang);
        ticketMachines_property.addDomain(ticketingEquipment_class);
        ticketMachines_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(ticketMachines_property, data);
        properties.put(label, ticketMachines_property);
    }

    @Override
    public void addLiteralPaymentMethodTicketing_property(Literal data/*, String label*/) {
        String label = "Payment Method";
        // Specific ticketingEquipment`s properties ---------------------------------------------
        OntProperty paymentMethodTicketing_property = currentOntology.createOntProperty(uriAccess.get_URI("TIE_PM"));
        paymentMethodTicketing_property.addComment("Payment Method", lang);       
        paymentMethodTicketing_property.addLabel(label, lang);
        paymentMethodTicketing_property.addDomain(ticketingEquipment_class);
        paymentMethodTicketing_property.addRange(EnumDataType.pay_met);
        this.stopPlaceEquipment.addLiteral(paymentMethodTicketing_property, data);
        properties.put(label, paymentMethodTicketing_property);
    }

    @Override
    public void addLiteralTicketTypesAvaiable_property(Literal data/*, String label*/) {
        String label = "Ticket Types Avaiable";
        // Specific ticketingEquipment`s properties ---------------------------------------------
        OntProperty ticketTypesAvaiable_property = currentOntology.createOntProperty(uriAccess.get_URI("TIE_TTA"));
        ticketTypesAvaiable_property.addComment("Ticket Types Avaiable", lang);       
        ticketTypesAvaiable_property.addLabel(label, lang);
        ticketTypesAvaiable_property.addDomain(ticketingEquipment_class);
        ticketTypesAvaiable_property.addRange(EnumDataType.ticket_type);
        this.stopPlaceEquipment.addLiteral(ticketTypesAvaiable_property, data);
        properties.put(label, ticketTypesAvaiable_property);
    }

    @Override
    public void addLiteralLowCounterAccess_property(Literal data/*, String label*/) {
        String label = "Low Counter Access";
        // Specific ticketingEquipment`s properties ---------------------------------------------
        OntProperty lowCounterAccess_property = currentOntology.createOntProperty(uriAccess.get_URI("TIE_LCA"));
        lowCounterAccess_property.addComment("Low Counter Access", lang);  
        lowCounterAccess_property.addLabel(label, lang);
        lowCounterAccess_property.addDomain(ticketingEquipment_class);
        lowCounterAccess_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(lowCounterAccess_property, data);
        properties.put(label, lowCounterAccess_property);
    }

    @Override
    public void addLiteralNumberOfLockers_property(Literal data/*, String label*/) {
        String label = "Number Of Lockers";
        // Specific luggageLockerEquipment`s properties ---------------------------------------------
        OntProperty numberOfLockers_property = currentOntology.createOntProperty(uriAccess.get_URI("LLE_NOL"));
        numberOfLockers_property.addComment("Number Of Lockers", lang);  
        numberOfLockers_property.addLabel(label, lang);
        numberOfLockers_property.addDomain(luggageLockerEquipment_class);
        numberOfLockers_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(numberOfLockers_property, data);
        properties.put(label, numberOfLockers_property);
    }

    @Override
    public void addLiteralLockerHeight_property(Literal data/*, String label*/) {
        String label = "Locker Height";
        // Specific luggageLockerEquipment`s properties ---------------------------------------------
        OntProperty lockerHeight_property = currentOntology.createOntProperty(uriAccess.get_URI("LLE_LH"));
        lockerHeight_property.addComment("Locker Height", lang);          
        lockerHeight_property.addLabel(label, lang);
        lockerHeight_property.addDomain(luggageLockerEquipment_class);
        lockerHeight_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(lockerHeight_property, data);
        properties.put(label, lockerHeight_property);
    }

    @Override
    public void addLiteralLockerDepth_property(Literal data/*, String label*/) {
        String label = "Locker Depth";
        // Specific luggageLockerEquipment`s properties ---------------------------------------------
        OntProperty lockerDepth_property = currentOntology.createOntProperty(uriAccess.get_URI("LLE_LD"));
        lockerDepth_property.addComment("Locker Depth", lang);      
        lockerDepth_property.addLabel(label, lang);
        lockerDepth_property.addDomain(luggageLockerEquipment_class);
        lockerDepth_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(lockerDepth_property, data);
        properties.put(label, lockerDepth_property);
    }

    @Override
    public void addLiteralLockerWidth_property(Literal data/*, String label*/) {
        String label = "Locker Width";
        // Specific luggageLockerEquipment`s properties ---------------------------------------------
        OntProperty lockerWidth_property = currentOntology.createOntProperty(uriAccess.get_URI("LLE_LW"));
        lockerWidth_property.addComment("Locker Width", lang);      
        lockerWidth_property.addLabel(label, lang);
        lockerWidth_property.addDomain(luggageLockerEquipment_class);
        lockerWidth_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(lockerWidth_property, data);
        properties.put(label, lockerWidth_property);
    }

    @Override
    public void addLiteralPaymentMethodLuggage_property(Literal data/*, String label*/) {
        String label = "Payment Method Luggage";
        // Specific luggageLockerEquipment`s properties ---------------------------------------------
        OntProperty paymentMethodLuggage_property = currentOntology.createOntProperty(uriAccess.get_URI("LLE_PM"));
        paymentMethodLuggage_property.addComment("Payment Method Luggage", lang);
        paymentMethodLuggage_property.addDomain(luggageLockerEquipment_class);
        paymentMethodLuggage_property.addRange(EnumDataType.pay_met);
        this.stopPlaceEquipment.addLiteral(paymentMethodLuggage_property, data);
        properties.put(label, paymentMethodLuggage_property);
    }

    @Override
    public void addLiteralSeats_property(Literal data/*, String label*/) {
        String label = "Seats";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty seats_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_S"));
        seats_property.addComment("Seats", lang);        
        seats_property.addLabel(label, lang);
        seats_property.addDomain(waitingEquipment_class);
        seats_property.addRange(XSD.integer);
        this.stopPlaceEquipment.addLiteral(seats_property, data);
        properties.put(label, seats_property);
    }

    @Override
    public void addLiteralWidthWaiting_property(Literal data/*, String label*/) {
        String label = "Width";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty widthWaiting_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_W"));
        widthWaiting_property.addComment("Width", lang);        
        widthWaiting_property.addLabel(label, lang);
        widthWaiting_property.addDomain(waitingEquipment_class);
        widthWaiting_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(widthWaiting_property, data);
        properties.put(label, widthWaiting_property);
    }

    @Override
    public void addLiteralWaitingLength_property(Literal data/*, String label*/) {
        String label = "Length";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty lengthWaiting_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_L"));
        lengthWaiting_property.addComment("Length", lang);
        lengthWaiting_property.addLabel(label, lang);
        lengthWaiting_property.addDomain(waitingEquipment_class);
        lengthWaiting_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(lengthWaiting_property, data);
        properties.put(label, lengthWaiting_property);
    }

    @Override
    public void addLiteralStepFree_property(Literal data/*, String label*/) {
        String label = "Step Free";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty stepFree_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_SF"));
        stepFree_property.addComment("Step Free", lang);
        stepFree_property.addLabel(label, lang);
        stepFree_property.addDomain(waitingEquipment_class);
        stepFree_property.addRange(XSD.xboolean);
        this.stopPlaceEquipment.addLiteral(stepFree_property, data);
        properties.put(label, stepFree_property);
    }

    @Override
    public void addLiteralWheelchairAreaWidth_property(Literal data/*, String label*/) {
        String label = "Wheelchair Area Width";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty wheelchairAreaWidth_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_WAW"));
        wheelchairAreaWidth_property.addComment("Wheelchair Area Width", lang);
        wheelchairAreaWidth_property.addLabel(label, lang);
        wheelchairAreaWidth_property.addDomain(waitingEquipment_class);
        wheelchairAreaWidth_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(wheelchairAreaWidth_property, data);
        properties.put(label, wheelchairAreaWidth_property);
    }

    @Override
    public void addLiteralWheelchairAreaLength_property(Literal data/*, String label*/) {
        String label = "Wheelchair Area Length";
        // Specific passengerSaetyEquipment`s properties ---------------------------------------------
        OntProperty wheelchairAreaLength_property = currentOntology.createOntProperty(uriAccess.get_URI("WE_WAL"));
        wheelchairAreaLength_property.addComment("Wheelchair Area Length", lang);
        wheelchairAreaLength_property.addLabel(label, lang);
        wheelchairAreaLength_property.addDomain(waitingEquipment_class);
        wheelchairAreaLength_property.addRange(XSD.positiveInteger);
        this.stopPlaceEquipment.addLiteral(wheelchairAreaLength_property, data);
        properties.put(label, wheelchairAreaLength_property);
    }
    
    @Override
    public String getBrandGraphic() {
        return properties.get("brandGraphic").asLiteral().getString();
    }

    @Override
    public String getSignGraphic() {
        return properties.get("getSignGraphic").asLiteral().getString();
    }

    @Override
    public String getPlacement() {
        return properties.get("placement").asLiteral().getString();
    }

    @Override
    public Boolean getAsBraille() {
        return properties.get("asBraille").asLiteral().getBoolean();
    }

    @Override
    public Boolean getMachineReadable() {
        return properties.get("machineReadable").asLiteral().getBoolean();
    }

    @Override
    public Integer getHeightSign() {
        return properties.get("heightSign").asLiteral().getInt();
    }

    @Override
    public Integer getWidthSign() {
        return properties.get("widthSign").asLiteral().getInt();
    }

    @Override
    public Integer getHeightFromFloor() {
        return properties.get("weightFromFloor").asLiteral().getInt();
    }

    @Override
    public String getPlaceName() {
        return properties.get("placeName").asLiteral().getString();
    }

    @Override
    public String getDirectionName() {
        return properties.get("directionName").asLiteral().getString();
    }

    @Override
    public String getLineName() {
        return properties.get("lineName").asLiteral().getString();
    }

    @Override
    public String getLineCode() {
        return properties.get("lineCode").asLiteral().getString();
    }

    @Override
    public String getLineMap() {
        return properties.get("lineMap").asLiteral().getString();
    }

    @Override
    public String getLineMode() {
        return properties.get("lineMode").asLiteral().getString();
    }

    @Override
    public String getContent() {
        return properties.get("content").asLiteral().getString();
    }

    @Override
    public Integer getWidthAccess() {
        return properties.get("widthAccess").asLiteral().getInt();
    }

    @Override
    public String getPathDirection() {
        return properties.get("pathDirection").asLiteral().getString();
    }

    @Override
    public Integer getPassengersPerMinute() {
        return properties.get("passengersPerMinute").asLiteral().getInt();
    }

    @Override
    public Integer getRelativeWeighting() {
        return properties.get("relativeWeighting").asLiteral().getInt();
    }

    @Override
    public Boolean getRailedQueue() {
        return properties.get("railedQueue").asLiteral().getBoolean();
    }

    @Override
    public Integer getNumberOfServers() {
        return properties.get("numberOfServers").asLiteral().getInt();
    }

    @Override
    public Integer getTicketedQueue() {
        return properties.get("ticketedQueue").asLiteral().getInt();
    }

    @Override
    public Boolean getZebraCrossing() {
        return properties.get("zebraCrossing").asLiteral().getBoolean();
    }

    @Override
    public Boolean getPedestrianLights() {
        return properties.get("pedestrianLights").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAcousticDeviceSensors() {
        return properties.get("acousticDeviceSensors").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAcousticCrossingAid() {
        return properties.get("acousticCrossingAid").asLiteral().getBoolean();
    }

    @Override
    public Boolean getVisualGuianceBands() {
        return properties.get("visualGuianceBands").asLiteral().getBoolean();
    }

    @Override
    public Boolean getTactileGuideStrips() {
        return properties.get("tactileGuideStrips").asLiteral().getBoolean();
    }

    @Override
    public Integer getDepthStair() {
        return properties.get("depthStair").asLiteral().getInt();
    }

    @Override
    public Integer getNumberOfSteps() {
        return properties.get("numberOfSteps").asLiteral().getInt();
    }

    @Override
    public Boolean getTactileActuatorsStair() {
        return properties.get("tactileActuatorsStair").asLiteral().getBoolean();
    }

    @Override
    public Boolean getEnergySavingTravelator() {
        return properties.get("energySavingTravelator").asLiteral().getBoolean();
    }

    @Override
    public Boolean getTactileActuators() {
        return properties.get("tactileActuators").asLiteral().getBoolean();
    }

    @Override
    public Integer getSpeed() {
        return properties.get("widthSign").asLiteral().getInt();
    }

    @Override
    public Boolean getEnergySavingEscalator() {
        return properties.get("energySavingEscalator").asLiteral().getBoolean();
    }

    @Override
    public String getHandRailTypeStaircase() {
        return properties.get("handRailTypeStaircase").asLiteral().getString();
    }

    @Override
    public Integer getHandRailHeightStaircase() {
        return properties.get("handRailHeightStaircase").asLiteral().getInt();
    }

    @Override
    public Boolean getSpiralStair() {
        return properties.get("spiralStair").asLiteral().getBoolean();
    }

    @Override
    public Integer getDepthLift() {
        return properties.get("depthLift").asLiteral().getInt();
    }

    @Override
    public Integer getMaximumLoad() {
        return properties.get("maximumLoad").asLiteral().getInt();
    }

    @Override
    public Boolean getThroughLoader() {
        return properties.get("throughLoader").asLiteral().getBoolean();
    }

    @Override
    public Boolean getMirrorOnOppositeSide() {
        return properties.get("mirrorOnOppositeSide").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAttendant() {
        return properties.get("attendant").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAutomatic() {
        return properties.get("automatic").asLiteral().getBoolean();
    }

    @Override
    public Boolean getTactileActuatorsLift() {
        return properties.get("tactileActuatorsLift").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAcousticAnnouncements() {
        return properties.get("acousticAnnouncements").asLiteral().getBoolean();
    }

    @Override
    public Boolean getWheelchairTurningCircle() {
        return properties.get("wheelchairTurningCircle").asLiteral().getBoolean();
    }

    @Override
    public Integer getLenght() {
        return properties.get("lenght").asLiteral().getInt();
    }

    @Override
    public Integer getGradient() {
        return properties.get("gradient").asLiteral().getInt();
    }

    @Override
    public Boolean getPedestal() {
        return properties.get("pedestal").asLiteral().getBoolean();
    }

    @Override
    public String getHandrailTypeRamp() {
        return properties.get("handrailTypeRamp").asLiteral().getString();
    }

    @Override
    public Integer getHandrailHeight() {
        return properties.get("handrailHeight").asLiteral().getInt();
    }

    @Override
    public Boolean getTactileGuianceStrips() {
        return properties.get("tactileGuianceStrips").asLiteral().getBoolean();
    }

    @Override
    public Boolean getTemporary() {
        return properties.get("temporary").asLiteral().getBoolean();
    }

    @Override
    public Boolean getDoor() {
        return properties.get("door").asLiteral().getBoolean();
    }

    @Override
    public Boolean getRevolvingDoor() {
        return properties.get("revolvingDoor").asLiteral().getBoolean();
    }

    @Override
    public Boolean getBarrier() {
        return properties.get("barrier").asLiteral().getBoolean();
    }

    @Override
    public Integer getNumberOfGates() {
        return properties.get("numberOfGates").asLiteral().getInt();
    }

    @Override
    public Boolean getEntranceRequiresTicket() {
        return properties.get("entranceRequiresTicket").asLiteral().getBoolean();
    }

    @Override
    public Boolean getEntranceRequiresPassport() {
        return properties.get("entranceRequiresPassport").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAcousticSensor() {
        return properties.get("acousticSensor").asLiteral().getBoolean();
    }

    @Override
    public Boolean getAutomaticDoor() {
        return properties.get("automaticDoor").asLiteral().getBoolean();
    }

    @Override
    public Boolean getTicketMachines() {
        return properties.get("ticketMachines").asLiteral().getBoolean();
    }

    @Override
    public String getPaymentMethodTicketing() {
        return properties.get("paymentMethodTicketing").asLiteral().getString();
    }

    @Override
    public String getTicketTypesAvaiable() {
        return properties.get("ticketTypesAvaiable").asLiteral().getString();
    }

    @Override
    public Boolean getLowCounterAccess() {
        return properties.get("lowCounterAccess").asLiteral().getBoolean();
    }

    @Override
    public Integer getNumberOfLockers() {
        return properties.get("numberOfLockers").asLiteral().getInt();
    }

    @Override
    public Integer getLockerHeight() {
        return properties.get("lockerHeight").asLiteral().getInt();
    }

    @Override
    public Integer getLockerDepth() {
        return properties.get("lockerDepth").asLiteral().getInt();
    }

    @Override
    public Integer getLockerWidth() {
        return properties.get("lockerWidth").asLiteral().getInt();
    }

    @Override
    public String getPaymentMethodLuggage() {
        return properties.get("paymentMethodLuggage").asLiteral().getString();
    }

    @Override
    public Integer getSeats() {
        return properties.get("seats").asLiteral().getInt();
    }

    @Override
    public Integer getWidthWaiting() {
        return properties.get("widthWaiting").asLiteral().getInt();
    }

    @Override
    public Integer getLengthWaiting() {
        return properties.get("lengthWaiting").asLiteral().getInt();
    }

    @Override
    public Boolean getStepFree() {
        return properties.get("stepFree").asLiteral().getBoolean();
    }

    @Override
    public Integer getWheelchairAreaWidth() {
        return properties.get("wheelchairAreaWidth").asLiteral().getInt();
    }

    @Override
    public Integer getWheelchairAreaLength() {
        return properties.get("wheelchairAreaLength").asLiteral().getInt();
    }

    public Map<String,OntProperty> getAllProperties(){
        return properties;
    }
    
}
