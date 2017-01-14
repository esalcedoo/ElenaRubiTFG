/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metro.controller;

import com.metro.model.StopPlace;
import com.metro.model.dto.StopPlaceIndividual;
import com.metro.service.StopPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author john
 */
@Controller
public class DefaultController {
    
    @Autowired(required = true)
    private StopPlaceService stopPlaceService;
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map) {
        StopPlaceIndividual sp = stopPlaceService.findByName("Argüelles");
        map.put("spl", sp);
        
        return "index";
    }
    
}
