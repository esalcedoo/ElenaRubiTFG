/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.service;

import com.metro.model.dto.StopPlaceIndividual;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author elena.salcedo
 */
@Service
public interface StopPlaceService {

    public List<StopPlaceIndividual> findAll();

    public StopPlaceIndividual findByName(String name);
    
    public int countStopPlaces();
    
    public List<StopPlaceIndividual> findAdjacents(String name);
    
    public List<StopPlaceIndividual> findDijkstra(String spIni, String spFin);
    
    
}
