package com.texoit.texoitapi.controller;

import com.texoit.texoitapi.model.AwardInterval;
import com.texoit.texoitapi.service.AwardIntervalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/awards-interval")
public class AwardIntervalController {

    @Autowired
    private AwardIntervalService awardIntervalService;

    @GetMapping
    public ResponseEntity<AwardInterval> getMinMaxIntervalAwards() {
        return ResponseEntity.ok().body(awardIntervalService.getAwardIntervalMinMax());
    }
}
