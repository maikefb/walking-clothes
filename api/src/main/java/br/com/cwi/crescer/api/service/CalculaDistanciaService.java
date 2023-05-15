package br.com.cwi.crescer.api.service;

import org.springframework.stereotype.Service;

@Service
public class CalculaDistanciaService {

    public double apply(double latitude, double longitude, double latitude2, double longitude2) {
        double deg2radMultiplier = Math.PI / 180;
        latitude = latitude * deg2radMultiplier;
        latitude2 = latitude2 * deg2radMultiplier;
        longitude = longitude * deg2radMultiplier;
        longitude2 = longitude2 * deg2radMultiplier;

        double radius = 6378.137;
        double dlon = longitude2 - longitude;
        return radius * Math.acos(Math.cos(latitude) * Math.cos(latitude2) * Math.cos(dlon) + Math.sin(latitude) * Math.sin(latitude2));
    }
}
