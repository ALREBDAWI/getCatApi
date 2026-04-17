package com.getcat.api.controller;

import com.getcat.api.model.Service;
import com.getcat.api.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    public ServiceService serviceService;

    @GetMapping
    public List<Service> getAllServices(){
        return serviceService.getAllServices();
    }
}
