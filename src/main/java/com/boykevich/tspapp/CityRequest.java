package com.boykevich.tspapp;

import java.util.List;

/**
 * Class is used to represent the request body.
 *
 * @author Dmitry Boikevych
 */
public class CityRequest {
    private List<String> cities;

    public CityRequest() {}

    public CityRequest(List<String> cities) {
        this.cities = cities;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }
}
