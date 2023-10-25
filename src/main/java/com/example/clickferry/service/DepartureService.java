package com.example.clickferry.service;

import com.example.clickferry.model.DepartureInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class DepartureService {
    @Value("${external.service.url}")
    private String externalServiceUrl;

    public List<DepartureInfo> getAvailableDepartures(String route, String date) {
        // Construye la URL de la petición
        String apiUrl = externalServiceUrl + "/departures?route=" + route + "&time=" + date;

        // Realiza la solicitud al servicio externo y procesa la respuesta
        ResponseEntity<DepartureInfo[]> response = new RestTemplate().exchange(apiUrl, HttpMethod.GET, null, DepartureInfo[].class);

        // Extrae y devuelve la lista de salidas disponibles
        return Arrays.asList(response.getBody());
    }
}
