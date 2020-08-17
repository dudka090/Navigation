package com.example.navigation.controller;

import com.example.navigation.service.GeocodeRequestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlacesController {
    GeocodeRequestService geocodeRequestService;

    @Autowired
    public PlacesController(GeocodeRequestService geocodeRequestService) {
        this.geocodeRequestService = geocodeRequestService;
    }

    @GetMapping("/getDirections")
    public String getDirections(@RequestParam String location1, @RequestParam String location2){
        String response = null;
        try {
            response = this.geocodeRequestService.geocodeRequest(location1, location2);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Problem with parsing to object";
        }
        return response;
    }
}
