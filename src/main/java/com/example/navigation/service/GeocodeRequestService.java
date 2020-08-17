package com.example.navigation.service;

import com.example.navigation.model.Coordinates;
import com.example.navigation.model.GeocodeResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeocodeRequestService {
    private final String GEOCODE_URL = "https://geocode.xyz/?";
    GraphHopperRequestService graphHopperRequestService;
    private final RestTemplate restTemplate;


    @Autowired
    public GeocodeRequestService(GraphHopperRequestService graphHopperRequestService, RestTemplateBuilder restTemplateBuilder) {
        this.graphHopperRequestService = graphHopperRequestService;
        this.restTemplate = restTemplateBuilder.build();
    }


    public String geocodeRequest(String location1, String location2) throws JsonProcessingException {
        String url1 = GEOCODE_URL + "locate=" + location1 + "&geoit=JSON";
        String url2 = GEOCODE_URL + "locate=" + location2 + "&geoit=JSON";
        GeocodeResponseDTO loc1 = this.restTemplate.getForObject(url1, GeocodeResponseDTO.class);
        GeocodeResponseDTO loc2 = this.restTemplate.getForObject(url2, GeocodeResponseDTO.class);
        Coordinates place1 = new Coordinates(loc1.getLongt(), loc1.getLatt());
        Coordinates place2 = new Coordinates(loc2.getLongt(), loc2.getLatt());
        String instructions = this.graphHopperRequestService.graphHopperRequest(place1, place2);
        return instructions;
    }

}
