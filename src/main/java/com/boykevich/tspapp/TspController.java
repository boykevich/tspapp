package com.boykevich.tspapp;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * A controller that provides an endpoint to solve the Travelling Salesman Problem (TSP)
 * for a given set of cities.
 */
@RestController
@RequestMapping("/api/tsp")
public class TspController {

    /**
     * Method handles POST requests to the /api/tsp endpoint.
     *
     * @param request - a CityRequest object, which contains a list of cities.
     * @return an ordered list of the cities with generated distances.
     */
    @PostMapping
    public TspResponse solve(@RequestBody CityRequest request) {
        List<String> cities = request.getCities();
        if (cities == null || cities.size() < 2) {
            return new TspResponse(cities);
        }

        Map<String, Map<String, Integer>> distances = generateDistances(cities);
        List<String> ordered = solveTsp(cities, distances);

        return new TspResponse(ordered);
    }

    /**
     * Method generates random distances between the cities.
     *
     * @param cities - list of the cities, which would be proceeded.
     * @return the distance matrix
     *    The outer map's key is the city - from,
     *    the value is a map where the key is a city - to,
     *    and the value is the distance between them.
     */
    private Map<String, Map<String, Integer>> generateDistances(List<String> cities) {
        Map<String, Map<String, Integer>> distances = new HashMap<>();
        Random randDist = new Random();
        for (String from : cities) {
            if (distances.containsKey(from)) {
                continue;
            }

            distances.put(from, new HashMap<>());
            for (String to : cities) {
                if (!from.equals(to)) {
                    distances.get(from).put(to, randDist.nextInt());
                }
            }
        }

        return distances;
    }

    /**
     * Method solves the TSP problem using a greedy approach.
     *
     * @param cities - list of the cities.
     * @param dist - a map with distances between cities.
     * @return a list - representing the order of cities visited in the solution.
     */
    private List<String> solveTsp(List<String> cities, Map<String, Map<String, Integer>> dist) {
        List<String> path = new ArrayList<>();
        Set<String> remaining = new HashSet<>(cities);
        String current = cities.get(0);
        path.add(current);
        remaining.remove(current);

        while (!remaining.isEmpty()) {
            String next = findNearestNeighbor(current, remaining, dist);
            if (next != null) {
                path.add(next);
                remaining.remove(next);
                current = next;
            } else {
                break;
            }
        }

        return path;
    }

    /**
     * Method finds the nearest neighbor to the current city
     *
     * @param current - the current city.
     * @param remaining - a set of the cities that have not yet been visited.
     * @param dist - a map with distances between cities.
     * @return the name of the nearest city.
     */
    private String findNearestNeighbor(String current, Set<String> remaining, Map<String, Map<String, Integer>> dist) {
        String nearest = null;
        int minDistance = Integer.MAX_VALUE;

        for (String city : remaining) {
            int distance = dist.get(current).getOrDefault(city, Integer.MAX_VALUE);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = city;
            }
        }

        return nearest;
    }
}
