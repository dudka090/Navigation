package com.example.navigation.service;

import com.example.navigation.model.Coordinates;

import com.example.navigation.model.GeocodeResponseDTO;
import com.example.navigation.model.GraphHopperInstruction;
import com.example.navigation.model.GraphHopperResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class GraphHopperRequestService {
    private final String GRAPHHOPPER_URL = "https://graphhopper.com/api/1/route?";
    private ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;
    private long routeTime = 0;


    public GraphHopperRequestService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public String graphHopperRequest(Coordinates place1, Coordinates place2) throws JsonProcessingException {
        String url1 = GRAPHHOPPER_URL + "point=" + place1.getLatitude() + "," + place1.getLongitude() +"&point=" + place2.getLatitude() + "," + place2.getLongitude() + "&key=d359b895-0c7d-4f8e-ae1c-c188d9ae0950";
        String loc1 = this.restTemplate.getForObject(url1, String.class);
        String graphHopperJSONInsturctions = "{" + loc1.substring((loc1.indexOf("instructions")-1), (loc1.indexOf("legs"))-2) + "}";
        GraphHopperResponseDTO graphHopperResponseDTO = objectMapper.readValue(graphHopperJSONInsturctions, GraphHopperResponseDTO.class);
        StringBuilder insturctionsFinal = new StringBuilder();
        StringBuilder instructionsText = new StringBuilder();
        long routeTime = 0;
        for (GraphHopperInstruction i: graphHopperResponseDTO.getInstructions()) {
            routeTime+=i.getTime();
            instructionsText.append(i.getDistance() + " metrów ");
         instructionsText.append(" " + i.getText());
            instructionsText.append("\n");
        }

        String routeTimeText ="Czas podróży: " +  ((int)Math.round(routeTime/60000)) + " minut " + ((int)Math.round((routeTime%60000/1000))) + " sekund \n";
        insturctionsFinal.append(routeTimeText).append(instructionsText);
        return insturctionsFinal.toString();
    }
}
