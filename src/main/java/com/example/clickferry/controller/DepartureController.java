package com.example.clickferry.controller;

import com.example.clickferry.model.DepartureInfo;
import com.example.clickferry.service.DepartureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/available-departures")
public class DepartureController {
    @Autowired
    private DepartureService departureService;

    @GetMapping
    public List<DepartureInfo> getAvailableDepartures(@RequestParam String route) {
        // Lógica para obtener los días disponibles de los próximos 2 meses
        LocalDate today = LocalDate.now();
        LocalDate twoMonthsLater = today.plusMonths(2);

        List<DepartureInfo> availableDepartures = new ArrayList<>();

        while (today.isBefore(twoMonthsLater)) {
            String date = today.toString();
            List<DepartureInfo> departures = departureService.getAvailableDepartures(route, date);
            availableDepartures.addAll(departures);

            today = today.plusDays(1); // Avanza al siguiente día
        }

        return availableDepartures;
    }
}
