package com.example.navigation.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphHopperInstruction {
    double distance;
    double heading;
    double last_heading;
    int sign;
    int[] interval;
    String text;
    double time;
    String street_name;
    int exit_number;
    double turn_angle;
    String exited;

}
