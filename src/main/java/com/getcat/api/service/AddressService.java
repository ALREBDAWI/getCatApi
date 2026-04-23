package com.getcat.api.service;

import com.getcat.api.model.Address;
import com.getcat.api.repo.AddressRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepo addressRepo;

    public List<Address> getAllAddresses(){
        return addressRepo.findAll();
    }

    public Address getAddressById(Integer id){
        return addressRepo.findById(id).orElseThrow(()->new RuntimeException("Address not found"));
    }

    public Address createAddress(Address address){
        return addressRepo.save(address);
    }

    public Address updateAddress(Integer id, Address address){
        Address oldAddress = getAddressById(id);
        oldAddress.setBuildingNum(address.getBuildingNum());
        oldAddress.setCity(address.getCity());
        oldAddress.setStreet(address.getStreet());
        oldAddress.setPostalCode(address.getPostalCode());
        return addressRepo.save(oldAddress);
    }

    // I need to think if we need to delete address and why
    public void deleteAddress(Integer id){
        Address oldAddress = getAddressById(id);
        addressRepo.delete(oldAddress);
    }
}
