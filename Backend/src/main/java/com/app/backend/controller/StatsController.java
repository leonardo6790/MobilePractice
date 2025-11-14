package com.app.backend.controller;

import com.app.backend.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@RestController
@RequestMapping("/api/stats")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("")
    public Map<String, Long> getStats(){
        return statsService.getStats();
    }
}
