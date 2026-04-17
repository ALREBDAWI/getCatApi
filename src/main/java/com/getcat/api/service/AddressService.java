package com.getcat.api.service;

import com.getcat.api.model.Address;
import com.getcat.api.repo.AddressRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AddressService {
    @Autowired
    private AddressRepo addressRepo;

    public List<Address> getAllAddresses(){
        return addressRepo.findAll();
    }

    public Address getAddressById(Integer id){
        return addressRepo.findById(id).orElseThrow(()->new RuntimeException("Address not found"));
    }

    public Address saveAddress(Address address){
        return addressRepo.save(address);
    }

    public void deleteAddress(Address address){
        addressRepo.delete(address);
    }
}
