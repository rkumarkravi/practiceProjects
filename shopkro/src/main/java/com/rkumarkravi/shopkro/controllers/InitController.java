package com.rkumarkravi.shopkro.controllers;

import com.rkumarkravi.shopkro.services.InitDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(("/api/init"))
public class InitController {

    @Autowired
    InitDataService initDataService;

    @PostMapping("/")
    public Map<String, String> getInitData() {
        return initDataService.getInitData();
    }
}
