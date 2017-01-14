/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model.dto;

import com.metro.model.StopPlaceEquipment;

/**
 *
 * @author elena.salcedo
 */
public class StopPlaceEquipmentIndividual {
    
    private String brandGraphic_property;
    private String signGraphic_property;
    private String placement_property;
    private Boolean asBraille_property;
    private Boolean machineReadable_property;
    private Integer heightSign_property;
    private Integer widthSign_property;
    private Integer heightFromFloor_property;
    private String placeName_property;
    private String directionName_property;
    private String lineName_property;
    private String lineCode_property;
    private String lineMap_property;
    private String lineMode_property;
    private String content_property;
    private Integer widthAccess_property;
    private String pathDirection_property;
    private Integer passengersPerMinute_property;
    private Integer relativeWeighting_property;
    private Boolean railedQueue_property;
    private Integer numberOfServers_property;
    private Integer ticketedQueue_property;
    private Boolean zebraCrossing_property;
    private Boolean pedestrianLights_property;
    private Boolean acousticDeviceSensors_property;
    private Boolean acousticCrossingAid_property;
    private Boolean visualGuianceBands_property;
    private Boolean tactileGuideStrips_property;
    private Integer depthStair_property;
    private Integer numberOfSteps_property;
    private Boolean tactileActuatorsStair_property;
    private Boolean energySavingTravelator_property;
    private Boolean tactileActuators_property;
    private Integer speed_property;
    private Boolean energySavingEscalator_property;
    private String handRailTypeStaircase_property;
    private Integer handRailHeightStaircase_property;
    private Boolean SpiralStair_property;
    private Integer depthLift_property;
    private Integer maximumLoad_property;
    private Boolean throughLoader_property;
    private Boolean mirrorOnOppositeSide_property;
    private Boolean attendant_property;
    private Boolean automatic_property;
    private Boolean tactileActuatorsLift_property;
    private Boolean acousticAnnouncements_property;
    private Boolean wheelchairTurningCircle_property;
    private Integer lenght_property;
    private Integer gradient_property;
    private Boolean pedestal_property;
    private String handrailTypeRamp_property;
    private Integer handrailHeight_property;
    private Boolean tactileGuianceStrips_property;
    private Boolean temporary_property;
    private Boolean door_property;
    private Boolean revolvingDoor_property;
    private Boolean barrier_property;
    private Integer numberOfGates_property;
    private Boolean entranceRequiresTicket_property;
    private Boolean entranceRequiresPassport_property;
    private Boolean acousticSensor_property;
    private Boolean automaticDoor_property;
    private Boolean ticketMachines_property;
    private String paymentMethodTicketing_property;
    private String ticketTypesAvaiable_property;
    private Boolean lowCounterAccess_property;
    private Integer numberOfLockers_property;
    private Integer lockerHeight_property;
    private Integer lockerDepth_property;
    private Integer lockerWidth_property;
    private String paymentMethodLuggage_property;
    private Integer seats_property;
    private Integer widthWaiting_property;
    private Integer lengthWaiting_property;
    private Boolean stepFree_property;
    private Integer wheelchairAreaWidth_property;
    private Integer wheelchairAreaLength_property;
    private Boolean smokingAllowed_property;

    public StopPlaceEquipmentIndividual(StopPlaceEquipment spe) {
        
//        this.brandGraphic_property = spe.getBrandGraphic_property().asLiteral().getValue().toString();
//        this.signGraphic_property = spe.getSignGraphic_property().asLiteral().getValue().toString();
//        this.placement_property = spe.getPlacement_property().asLiteral().getValue().toString();
//        this.asBraille_property = spe.getAsBraille_property().asLiteral().getBoolean();
//        this.machineReadable_property = spe.getMachineReadable_property().asLiteral().getBoolean();
//        this.heightSign_property = spe.getHeightSign_property().asLiteral().getInt();
//        this.widthSign_property = spe.getWidthSign_property().asLiteral().getInt();
//        this.heightFromFloor_property = spe.getHeightFromFloor_property().asLiteral().getInt();
//        this.placeName_property = spe.getPlaceName_property().asLiteral().getValue().toString();
//        this.directionName_property = spe.getDirectionName_property().asLiteral().getValue().toString();
//        this.lineName_property = spe.getLineName_property().asLiteral().getValue().toString();
//        this.lineCode_property = spe.getLineCode_property().asLiteral().getValue().toString();
//        this.lineMap_property = spe.getLineMap_property().asLiteral().getValue().toString();
//        this.lineMode_property = spe.getLineMode_property().asLiteral().getValue().toString();
//        this.content_property = spe.getContent_property().asLiteral().getValue().toString();
//        this.widthAccess_property = spe.getWidthAccess_property().asLiteral().getInt();
//        this.pathDirection_property = spe.getPathDirection_property().asLiteral().getValue().toString();
//        this.passengersPerMinute_property = spe.getPassengersPerMinute_property().asLiteral().getInt();
//        this.relativeWeighting_property = spe.getRelativeWeighting_property().asLiteral().getInt();
//        this.railedQueue_property = spe.getRailedQueue_property().asLiteral().getBoolean();
//        this.numberOfServers_property = spe.getNumberOfServers_property().asLiteral().getInt();
//        this.ticketedQueue_property = spe.getTicketedQueue_property().asLiteral().getInt();
//        this.zebraCrossing_property = spe.getZebraCrossing_property().asLiteral().getBoolean();
//        this.pedestrianLights_property = spe.getPedestrianLights_property().asLiteral().getBoolean();
//        this.acousticDeviceSensors_property = spe.getAcousticDeviceSensors_property().asLiteral().getBoolean();
//        this.acousticCrossingAid_property = spe.getAcousticCrossingAid_property().asLiteral().getBoolean();
//        this.visualGuianceBands_property = spe.getVisualGuianceBands_property().asLiteral().getBoolean();
//        this.tactileGuideStrips_property = spe.getTactileGuideStrips_property().asLiteral().getBoolean();
//        this.depthStair_property = spe.getDepthStair_property().asLiteral().getInt();
//        this.numberOfSteps_property = spe.getNumberOfSteps_property().asLiteral().getInt();
//        this.tactileActuatorsStair_property = spe.getTactileActuatorsStair_property();
//        this.energySavingTravelator_property = spe.getEnergySavingTravelator_property;
//        this.tactileActuators_property = spe.getTactileActuators_property;
//        this.speed_property = spe.getSpeed_property;
//        this.energySavingEscalator_property = spe.getEnergySavingEscalator_property;
//        this.handRailTypeStaircase_property = spe.getHandRailTypeStaircase_property;
//        this.handRailHeightStaircase_property = spe.getHandRailHeightStaircase_property;
//        this.SpiralStair_property = spe.getSpiralStair_property;
//        this.depthLift_property = spe.getDepthLift_property;
//        this.maximumLoad_property = spe.getMaximumLoad_property;
//        this.throughLoader_property = spe.getThroughLoader_property;
//        this.mirrorOnOppositeSide_property = spe.getMirrorOnOppositeSide_property;
//        this.attendant_property = spe.getAttendant_property;
//        this.automatic_property = spe.getAutomatic_property;
//        this.tactileActuatorsLift_property = spe.getTactileActuatorsLift_property;
//        this.acousticAnnouncements_property = spe.getAcousticAnnouncements_property;
//        this.wheelchairTurningCircle_property = spe.getWheelchairTurningCircle_property;
//        this.lenght_property = spe.getLenght_property;
//        this.gradient_property = spe.getGradient_property;
//        this.pedestal_property = spe.getPedestal_property;
//        this.handrailTypeRamp_property = spe.getHandrailTypeRamp_property;
        this.handrailHeight_property = handrailHeight_property;
        this.tactileGuianceStrips_property = tactileGuianceStrips_property;
        this.temporary_property = temporary_property;
        this.door_property = door_property;
        this.revolvingDoor_property = revolvingDoor_property;
        this.barrier_property = barrier_property;
        this.numberOfGates_property = numberOfGates_property;
        this.entranceRequiresTicket_property = entranceRequiresTicket_property;
        this.entranceRequiresPassport_property = entranceRequiresPassport_property;
        this.acousticSensor_property = acousticSensor_property;
        this.automaticDoor_property = automaticDoor_property;
        this.ticketMachines_property = ticketMachines_property;
        this.paymentMethodTicketing_property = paymentMethodTicketing_property;
        this.ticketTypesAvaiable_property = ticketTypesAvaiable_property;
        this.lowCounterAccess_property = lowCounterAccess_property;
        this.numberOfLockers_property = numberOfLockers_property;
        this.lockerHeight_property = lockerHeight_property;
        this.lockerDepth_property = lockerDepth_property;
        this.lockerWidth_property = lockerWidth_property;
        this.paymentMethodLuggage_property = paymentMethodLuggage_property;
        this.seats_property = seats_property;
        this.widthWaiting_property = widthWaiting_property;
        this.lengthWaiting_property = lengthWaiting_property;
        this.stepFree_property = stepFree_property;
        this.wheelchairAreaWidth_property = wheelchairAreaWidth_property;
        this.wheelchairAreaLength_property = wheelchairAreaLength_property;
        this.smokingAllowed_property = smokingAllowed_property;
    }

    public String getBrandGraphic_property() {
        return brandGraphic_property;
    }

    public String getSignGraphic_property() {
        return signGraphic_property;
    }

    public String getPlacement_property() {
        return placement_property;
    }

    public Boolean getAsBraille_property() {
        return asBraille_property;
    }

    public Boolean getMachineReadable_property() {
        return machineReadable_property;
    }

    public Integer getHeightSign_property() {
        return heightSign_property;
    }

    public Integer getWidthSign_property() {
        return widthSign_property;
    }

    public Integer getHeightFromFloor_property() {
        return heightFromFloor_property;
    }

    public String getPlaceName_property() {
        return placeName_property;
    }

    public String getDirectionName_property() {
        return directionName_property;
    }

    public String getLineName_property() {
        return lineName_property;
    }

    public String getLineCode_property() {
        return lineCode_property;
    }

    public String getLineMap_property() {
        return lineMap_property;
    }

    public String getLineMode_property() {
        return lineMode_property;
    }

    public String getContent_property() {
        return content_property;
    }

    public Integer getWidthAccess_property() {
        return widthAccess_property;
    }

    public String getPathDirection_property() {
        return pathDirection_property;
    }

    public Integer getPassengersPerMinute_property() {
        return passengersPerMinute_property;
    }

    public Integer getRelativeWeighting_property() {
        return relativeWeighting_property;
    }

    public Boolean getRailedQueue_property() {
        return railedQueue_property;
    }

    public Integer getNumberOfServers_property() {
        return numberOfServers_property;
    }

    public Integer getTicketedQueue_property() {
        return ticketedQueue_property;
    }

    public Boolean getZebraCrossing_property() {
        return zebraCrossing_property;
    }

    public Boolean getPedestrianLights_property() {
        return pedestrianLights_property;
    }

    public Boolean getAcousticDeviceSensors_property() {
        return acousticDeviceSensors_property;
    }

    public Boolean getAcousticCrossingAid_property() {
        return acousticCrossingAid_property;
    }

    public Boolean getVisualGuianceBands_property() {
        return visualGuianceBands_property;
    }

    public Boolean getTactileGuideStrips_property() {
        return tactileGuideStrips_property;
    }

    public Integer getDepthStair_property() {
        return depthStair_property;
    }

    public Integer getNumberOfSteps_property() {
        return numberOfSteps_property;
    }

    public Boolean getTactileActuatorsStair_property() {
        return tactileActuatorsStair_property;
    }

    public Boolean getEnergySavingTravelator_property() {
        return energySavingTravelator_property;
    }

    public Boolean getTactileActuators_property() {
        return tactileActuators_property;
    }

    public Integer getSpeed_property() {
        return speed_property;
    }

    public Boolean getEnergySavingEscalator_property() {
        return energySavingEscalator_property;
    }

    public String getHandRailTypeStaircase_property() {
        return handRailTypeStaircase_property;
    }

    public Integer getHandRailHeightStaircase_property() {
        return handRailHeightStaircase_property;
    }

    public Boolean getSpiralStair_property() {
        return SpiralStair_property;
    }

    public Integer getDepthLift_property() {
        return depthLift_property;
    }

    public Integer getMaximumLoad_property() {
        return maximumLoad_property;
    }

    public Boolean getThroughLoader_property() {
        return throughLoader_property;
    }

    public Boolean getMirrorOnOppositeSide_property() {
        return mirrorOnOppositeSide_property;
    }

    public Boolean getAttendant_property() {
        return attendant_property;
    }

    public Boolean getAutomatic_property() {
        return automatic_property;
    }

    public Boolean getTactileActuatorsLift_property() {
        return tactileActuatorsLift_property;
    }

    public Boolean getAcousticAnnouncements_property() {
        return acousticAnnouncements_property;
    }

    public Boolean getWheelchairTurningCircle_property() {
        return wheelchairTurningCircle_property;
    }

    public Integer getLenght_property() {
        return lenght_property;
    }

    public Integer getGradient_property() {
        return gradient_property;
    }

    public Boolean getPedestal_property() {
        return pedestal_property;
    }

    public String getHandrailTypeRamp_property() {
        return handrailTypeRamp_property;
    }

    public Integer getHandrailHeight_property() {
        return handrailHeight_property;
    }

    public Boolean getTactileGuianceStrips_property() {
        return tactileGuianceStrips_property;
    }

    public Boolean getTemporary_property() {
        return temporary_property;
    }

    public Boolean getDoor_property() {
        return door_property;
    }

    public Boolean getRevolvingDoor_property() {
        return revolvingDoor_property;
    }

    public Boolean getBarrier_property() {
        return barrier_property;
    }

    public Integer getNumberOfGates_property() {
        return numberOfGates_property;
    }

    public Boolean getEntranceRequiresTicket_property() {
        return entranceRequiresTicket_property;
    }

    public Boolean getEntranceRequiresPassport_property() {
        return entranceRequiresPassport_property;
    }

    public Boolean getAcousticSensor_property() {
        return acousticSensor_property;
    }

    public Boolean getAutomaticDoor_property() {
        return automaticDoor_property;
    }

    public Boolean getTicketMachines_property() {
        return ticketMachines_property;
    }

    public String getPaymentMethodTicketing_property() {
        return paymentMethodTicketing_property;
    }

    public String getTicketTypesAvaiable_property() {
        return ticketTypesAvaiable_property;
    }

    public Boolean getLowCounterAccess_property() {
        return lowCounterAccess_property;
    }

    public Integer getNumberOfLockers_property() {
        return numberOfLockers_property;
    }

    public Integer getLockerHeight_property() {
        return lockerHeight_property;
    }

    public Integer getLockerDepth_property() {
        return lockerDepth_property;
    }

    public Integer getLockerWidth_property() {
        return lockerWidth_property;
    }

    public String getPaymentMethodLuggage_property() {
        return paymentMethodLuggage_property;
    }

    public Integer getSeats_property() {
        return seats_property;
    }

    public Integer getWidthWaiting_property() {
        return widthWaiting_property;
    }

    public Integer getLengthWaiting_property() {
        return lengthWaiting_property;
    }

    public Boolean getStepFree_property() {
        return stepFree_property;
    }

    public Integer getWheelchairAreaWidth_property() {
        return wheelchairAreaWidth_property;
    }

    public Integer getWheelchairAreaLength_property() {
        return wheelchairAreaLength_property;
    }

    public Boolean getSmokingAllowed_property() {
        return smokingAllowed_property;
    }
    
}
