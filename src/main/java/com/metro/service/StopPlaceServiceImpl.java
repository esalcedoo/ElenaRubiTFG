/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.service;

import com.metro.dao.StopPlaceDAO;
import com.metro.model.StopPlace;
import com.metro.model.dto.StopPlaceIndividual;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author elena.salcedo
 */
@Component
public class StopPlaceServiceImpl implements StopPlaceService {
    
    @Autowired(required = true)
    private StopPlaceDAO stopPlaceDao;
    
    public StopPlaceServiceImpl() {}    
    
    @Override
    public List<StopPlaceIndividual> findAll() {
        List<StopPlaceIndividual> lstSPI= new ArrayList<>();
        for (StopPlace sp :stopPlaceDao.findAll()){
            
            if(!lstSPI.isEmpty()){
                if (!lstSPI.contains(new StopPlaceIndividual(sp))){
                    lstSPI.add(new StopPlaceIndividual(sp));
                }
            }else{
                lstSPI.add(new StopPlaceIndividual(sp));
            }
        
        }
        return lstSPI;
    }

    @Override
    public StopPlaceIndividual findByName(String name) {
        return new StopPlaceIndividual(stopPlaceDao.findByName(name));
    }

    @Override
    public int countStopPlaces() {
        return stopPlaceDao.countStopPlace();
    }
    
    @Override
    public List<StopPlaceIndividual> findAdjacents(String name) {
                List<StopPlaceIndividual> lstSPI= new ArrayList<>();
        for (StopPlace sp :stopPlaceDao.findAdjacents(name)){
            
            if(!lstSPI.isEmpty()){
                if (!lstSPI.contains(new StopPlaceIndividual(sp))){
                    lstSPI.add(new StopPlaceIndividual(sp));
                }
            }else{
                lstSPI.add(new StopPlaceIndividual(sp));
            }
        }
        return lstSPI;
    }
    
    @Override
    public List<StopPlaceIndividual> findDijkstra(String spIni, String spFin) {
        
        boolean fin = false;
        
        Map<StopPlaceIndividual, Double> mapSPI = new HashMap<>();       
      
        mapSPI.put(findByName(spIni), 0.0);
        
        List<StopPlaceIndividual> previous = new ArrayList<>();        
        previous.add(findByName(spIni));
                
        for ( StopPlaceIndividual key : mapSPI.keySet() ) {
            
            for (StopPlaceIndividual sp :findAdjacents(key.getStopPlaceName())){  
                
                mapSPI.put(sp, mapSPI.get(key) + (Double)(key.getDistancefrom(sp)));
                previous.add(sp);
                fin = sp.getStopPlaceName().equalsIgnoreCase(spFin);
            }
            if(fin) break;
            mapSPI.remove(key);
        }
        
        /*
        function Dijkstra(Graph, spIni):
            for each vertex v in Graph:         // Initialization
                dist[v] := infinity             // initial distance from source to vertex v is set to infinite
                previous[v] := undefined	// Previous node in optimal path from source
            dist[spIni] := 0	// Distance from source to source
            Q := the set of all nodes in Graph	// all nodes in the graph are unoptimized - thus are in Q
            Q := 
            while Q is not empty:               // main loop
                u := node in Q with smallest dist[ ]
                remove u from Q
                for each neighbor v of u:	// where v has not yet been removed from Q.
                    alt := dist[u] + dist_between(u, v)
                    if alt < dist[v]            // Relax (u,v)
                        dist[v] := alt
                        previous[v] := u
            return previous[ ]
        */
        
        return previous;
    }
}
