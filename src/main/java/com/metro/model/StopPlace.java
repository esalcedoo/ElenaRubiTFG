/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.model;

import com.uris.URI_Access;
import java.util.List;

/**
 *
 * @author elena.salcedo
 */
public interface StopPlace {

    public String lang = "en";
    public URI_Access uriAccess = new URI_Access();

    public double getdistanceFromPoint(double x, double y);

    public void addLiteralCoordX(double x);

    public void addLiteralCoordY(double y);

    public void addLiteralPlaceName(String data);
    
    public void addLiteralLineCode(String data);
    
    public double getLiteralCoordX();

    public double getLiteralCoordY();

    public String getLiteralPlaceName();
    
    public List<Integer> getLiteralLineCode();
}
