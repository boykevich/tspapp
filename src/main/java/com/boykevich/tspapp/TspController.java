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

@RestController
@RequestMapping("/api/tsp")
public class TspController {

    @PostMapping
    public TspResponse solve(@RequestBody CityRequest request) {
        List<String> cities = request.getCities();
        if (cities == null || cities.size() < 2) {
            return new TspResponse(cities);
        }

        Map<String, Map<String, Integer>> distances = generateDistances(cities);
        List<String> ordered = solveGreedyTsp(cities, distances);

        return new TspResponse(ordered);
    }

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

    private List<String> solveGreedyTsp(List<String> cities, Map<String, Map<String, Integer>> dist) {
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
