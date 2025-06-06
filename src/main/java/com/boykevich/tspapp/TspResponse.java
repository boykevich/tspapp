package com.boykevich.tspapp;

import java.util.List;

/**
 * @author Dmitry Boikevych
 */
public class TspResponse {
    private List<String> orderedCities;

    public TspResponse(List<String> orderedCities) {
        this.orderedCities = orderedCities;
    }

    public List<String> getOrderedCities() {
        return orderedCities;
    }
}
