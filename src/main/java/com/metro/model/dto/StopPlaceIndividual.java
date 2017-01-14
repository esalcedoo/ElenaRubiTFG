/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model.dto;

import com.metro.model.StopPlace;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author elena.salcedo
 */
public class StopPlaceIndividual {

    private double coordX;
    private double coordY;
    private String stopPlaceName;
    private List<Integer> lineCode;
    private Map<String,Object> stopPlaceEquipment;

    
    public StopPlaceIndividual(StopPlace sp) {
        coordX = sp.getLiteralCoordX();
        coordY = sp.getLiteralCoordY();
        stopPlaceName = sp.getLiteralPlaceName();
        lineCode = new ArrayList<>(sp.getLiteralLineCode());
        
//        Field [] attributes =  sp.getClass().getDeclaredFields();
//        for (Field field : attributes) {
//            // Dynamically read Attribute Name
//            System.out.println("ATTRIBUTE NAME: " + field.getName());
////            System.out.println("ATTRIBUTE NAME: " + field.get(sp));
//
//            try {
//                // Dynamically set Attribute Value
//                System.out.println("ATTRIBUTE VALUE: " + field.get(field));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
        
    }

    public double getCoordX() {
        return coordX;
    }

    public void setCoordX(double coordX) {
        this.coordX = coordX;
    }

    public double getCoordY() {
        return coordY;
    }

    public void setCoordY(double coordY) {
        this.coordY = coordY;
    }

    public String getStopPlaceName() {
        return stopPlaceName;
    }

    public void setStopPlaceName(String stopPlaceName) {
        this.stopPlaceName = stopPlaceName;
    }

    public List<Integer> getLineCode() {
        return lineCode;
    }

    public void setLineCode(List<Integer> lineCode) {
        this.lineCode = lineCode;
    }
    
    public double getDistancefrom(StopPlaceIndividual other) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(this.coordX-other.getCoordX());
        double dLng = Math.toRadians(this.coordY-other.getCoordY());
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                   Math.cos(Math.toRadians(other.getCoordX())) * Math.cos(Math.toRadians(this.coordX)) *
                   Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);

        return dist;
    }
    
    @Override
    public boolean equals(Object other){
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof StopPlaceIndividual))return false;
        StopPlaceIndividual otherStopPlaceIndividual = (StopPlaceIndividual)other;
        return this.stopPlaceName.equals(otherStopPlaceIndividual.getStopPlaceName()) &&
                this.getCoordX() == otherStopPlaceIndividual.getCoordX();
    }

    
    public Map<String, Object> getStopPlaceEquipment() {
        return stopPlaceEquipment;
    }
    
}
