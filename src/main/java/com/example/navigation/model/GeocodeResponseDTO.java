package com.example.navigation.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class GeocodeResponseDTO implements Serializable {

    float longt;
    float latt;
}
