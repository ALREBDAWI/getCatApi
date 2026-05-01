package com.getcat.api.service;

import com.getcat.api.model.Service;
import com.getcat.api.repo.ServiceRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepo serviceRepo;

    public List<Service> getAllServices(){
        return serviceRepo.findAll();
    }

    // we need to rethink of error handling
    public Service getServiceById(Integer id){
        return serviceRepo.findById(id).orElseThrow(()->new RuntimeException("Service not found with id: "+id));
    }

}
