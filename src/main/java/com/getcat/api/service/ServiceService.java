package com.getcat.api.service;

import com.getcat.api.model.Service;
import com.getcat.api.repo.ServiceRepo;
import lombok.RequiredArgsConstructor;

import java.util.List;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
public class ServiceService {
    private ServiceRepo serviceRepo;

    public List<Service> getAllServices(){
        return serviceRepo.findAll();
    }

    // we need to rethink of error handling
    public Service getServiceById(Integer id){
        return serviceRepo.findById(id).orElseThrow(()->new RuntimeException("Service not found with id: "+id));
    }

}
