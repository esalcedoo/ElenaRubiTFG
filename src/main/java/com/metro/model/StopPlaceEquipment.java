/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model;

import com.uris.URI_Access;
import org.apache.jena.rdf.model.Literal;

/**
 *
 * @author elena.salcedo
 */
public interface StopPlaceEquipment {

    public String lang = "en";
    public URI_Access uriAccess = new URI_Access();
    
    public boolean isAccesibleForHearingImpaired();

    public boolean isAccesibleForVisuallyImpaired();

    public boolean isAccesibleForPhysicallyHandicapped();

    public void addLiteralPassengerInfoFacilityType_property(Literal data/*, String label*/);

    public void addLiteralInfoFacility_property(Literal data/*, String label*/);

    public void addLiteralBrandGraphic_property(Literal data/*, String label*/);

    public void addLiteralSignGraphic_property(Literal data/*, String label*/);

    public void addLiteralPlacement_property(Literal data/*, String label*/);

    public void addLiteralAsBraille_property(Literal data/*, String label*/);

    public void addLiteralMachineReadable_property(Literal data/*, String label*/);

    public void addLiteralHeightSign_property(Literal data/*, String label*/);

    public void addLiteralWidthSign_property(Literal data/*, String label*/);

    public void addLiteralHeightFromFloor_property(Literal data/*, String label*/);

    public void addLiteralPlaceName_property(Literal data/*, String label*/);

    public void addLiteralDirectionName_property(Literal data/*, String label*/);

    public void addLiteralLineName_property(Literal data/*, String label*/);

    public void addLiteralLineCode_property(Literal data/*, String label*/);

    public void addLiteralLineMap_property(Literal data/*, String label*/);

    public void addLiteralLineMode_property(Literal data/*, String label*/);

    public void addLiteralContent_property(Literal data/*, String label*/);

    public void addLiteralWidthAccess_property(Literal data/*, String label*/);

    public void addLiteralPathDirection_property(Literal data/*, String label*/);

    public void addLiteralPassengersPerMinute_property(Literal data/*, String label*/);

    public void addLiteralRelativeWeighting_property(Literal data/*, String label*/);

    public void addLiteralRailedQueue_property(Literal data/*, String label*/);

    public void addLiteralNumberOfServers_property(Literal data/*, String label*/);

    public void addLiteralTicketedQueue_property(Literal data/*, String label*/);

    public void addLiteralZebraCrossing_property(Literal data/*, String label*/);

    public void addLiteralPedestrianLights_property(Literal data/*, String label*/);

    public void addLiteralAcousticDeviceSensors_property(Literal data/*, String label*/);

    public void addLiteralAcousticCrossingAid_property(Literal data/*, String label*/);

    public void addLiteralVisualGuianceBands_property(Literal data/*, String label*/);

    public void addLiteralTactileGuideStripsCrossing_property(Literal data/*, String label*/);

    public void addLiteralDepthStair_property(Literal data/*, String label*/);

    public void addLiteralNumberOfSteps_property(Literal data/*, String label*/);

    public void addLiteralTactileActuatorsStair_property(Literal data/*, String label*/);

    public void addLiteralEnergySavingTravelator_property(Literal data/*, String label*/);

    public void addLiteralTactileActuatorsTravelator_property(Literal data/*, String label*/);

    public void addLiteralSpeed_property(Literal data/*, String label*/);

    public void addLiteralEnergySavingEscalator_property(Literal data/*, String label*/);

    public void addLiteralHandRailTypeStaircase_property(Literal data/*, String label*/);

    public void addLiteralHandRailHeightStaircase_property(Literal data/*, String label*/);

    public void addLiteralSpiralStair_property(Literal data/*, String label*/);

    public void addLiteralDepthLift_property(Literal data/*, String label*/);

    public void addLiteralMaximumLoad_property(Literal data/*, String label*/);

    public void addLiteralThroughLoader_property(Literal data/*, String label*/);

    public void addLiteralMirrorOnOppositeSide_property(Literal data/*, String label*/);

    public void addLiteralAttendant_property(Literal data/*, String label*/);

    public void addLiteralAutomatic_property(Literal data/*, String label*/);

    public void addLiteralTactileActuatorsLift_property(Literal data/*, String label*/);

    public void addLiteralAcousticAnnouncements_property(Literal data/*, String label*/);

    public void addLiteralWheelchairTurningCircle_property(Literal data/*, String label*/);

    public void addLiteralRampLenght_property(Literal data/*, String label*/);

    public void addLiteralGradient_property(Literal data/*, String label*/);

    public void addLiteralPedestal_property(Literal data/*, String label*/);

    public void addLiteralHandrailTypeRamp_property(Literal data/*, String label*/);

    public void addLiteralHandrailHeightRamp_property(Literal data/*, String label*/);

    public void addLiteralTactileGuideStripsRamp_property(Literal data/*, String label*/);

    public void addLiteralTemporary_property(Literal data/*, String label*/);

    public void addLiteralDoor_property(Literal data/*, String label*/);

    public void addLiteralRevolvingDoor_property(Literal data/*, String label*/);

    public void addLiteralBarrier_property(Literal data/*, String label*/);

    public void addLiteralNumberOfGates_property(Literal data/*, String label*/);

    public void addLiteralEntranceRequiresTicket_property(Literal data/*, String label*/);

    public void addLiteralEntranceRequiresPassport_property(Literal data/*, String label*/);

    public void addLiteralAcousticSensor_property(Literal data/*, String label*/);

    public void addLiteralAutomaticDoor_property(Literal data/*, String label*/);

    public void addLiteralTicketMachines_property(Literal data/*, String label*/);

    public void addLiteralPaymentMethodTicketing_property(Literal data/*, String label*/);

    public void addLiteralTicketTypesAvaiable_property(Literal data/*, String label*/);

    public void addLiteralLowCounterAccess_property(Literal data/*, String label*/);

    public void addLiteralNumberOfLockers_property(Literal data/*, String label*/);

    public void addLiteralLockerHeight_property(Literal data/*, String label*/);

    public void addLiteralLockerDepth_property(Literal data/*, String label*/);

    public void addLiteralLockerWidth_property(Literal data/*, String label*/);

    public void addLiteralPaymentMethodLuggage_property(Literal data/*, String label*/);

    public void addLiteralSeats_property(Literal data/*, String label*/);

    public void addLiteralWidthWaiting_property(Literal data/*, String label*/);

    public void addLiteralWaitingLength_property(Literal data/*, String label*/);

    public void addLiteralStepFree_property(Literal data/*, String label*/);

    public void addLiteralWheelchairAreaWidth_property(Literal data/*, String label*/);

    public void addLiteralWheelchairAreaLength_property(Literal data/*, String label*/);


    public String getBrandGraphic();

    public String getSignGraphic();

    public String getPlacement();
    
    public Boolean getAsBraille();

    public Boolean getMachineReadable();

    public Integer getHeightSign();

    public Integer getWidthSign();

    public Integer getHeightFromFloor();

    public String getPlaceName();

    public String getDirectionName();

    public String getLineName();

    public String getLineCode();

    public String getLineMap();

    public String getLineMode();

    public String getContent();
    
    public Integer getWidthAccess();

    public String getPathDirection();
    
    public Integer getPassengersPerMinute();

    public Integer getRelativeWeighting();

    public Boolean getRailedQueue();

    public Integer getNumberOfServers();

    public Integer getTicketedQueue();

    public Boolean getZebraCrossing();

    public Boolean getPedestrianLights();

    public Boolean getAcousticDeviceSensors();

    public Boolean getAcousticCrossingAid();

    public Boolean getVisualGuianceBands();

    public Boolean getTactileGuideStrips();

    public Integer getDepthStair();

    public Integer getNumberOfSteps();

    public Boolean getTactileActuatorsStair();

    public Boolean getEnergySavingTravelator();

    public Boolean getTactileActuators();

    public Integer getSpeed();

    public Boolean getEnergySavingEscalator();

    public String getHandRailTypeStaircase();

    public Integer getHandRailHeightStaircase();

    public Boolean getSpiralStair();

    public Integer getDepthLift();

    public Integer getMaximumLoad();

    public Boolean getThroughLoader();

    public Boolean getMirrorOnOppositeSide();

    public Boolean getAttendant();

    public Boolean getAutomatic();

    public Boolean getTactileActuatorsLift();

    public Boolean getAcousticAnnouncements();

    public Boolean getWheelchairTurningCircle();

    public Integer getLenght();

    public Integer getGradient();

    public Boolean getPedestal();

    public String getHandrailTypeRamp();

    public Integer getHandrailHeight();

    public Boolean getTactileGuianceStrips();

    public Boolean getTemporary();

    public Boolean getDoor();

    public Boolean getRevolvingDoor();

    public Boolean getBarrier();

    public Integer getNumberOfGates();

    public Boolean getEntranceRequiresTicket();

    public Boolean getEntranceRequiresPassport();

    public Boolean getAcousticSensor();

    public Boolean getAutomaticDoor();

    public Boolean getTicketMachines();

    public String getPaymentMethodTicketing();

    public String getTicketTypesAvaiable();

    public Boolean getLowCounterAccess();

    public Integer getNumberOfLockers();

    public Integer getLockerHeight();

    public Integer getLockerDepth();

    public Integer getLockerWidth();

    public String getPaymentMethodLuggage();

    public Integer getSeats();

    public Integer getWidthWaiting();

    public Integer getLengthWaiting();

    public Boolean getStepFree();

    public Integer getWheelchairAreaWidth();

    public Integer getWheelchairAreaLength();
    
}
