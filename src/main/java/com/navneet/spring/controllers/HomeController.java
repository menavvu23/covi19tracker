package com.navneet.spring.controllers;

import com.navneet.spring.modals.LocationStats;
import com.navneet.spring.services.CovidDataServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CovidDataServices covidDataServices;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allstats = covidDataServices.getAllstats();
        int totalReportedCases = allstats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allstats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allstats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases",totalNewCases);
        return "home";
    }
}
