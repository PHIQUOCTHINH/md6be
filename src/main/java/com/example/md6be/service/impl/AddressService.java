package com.example.md6be.service.impl;


import com.example.md6be.model.Address;
import com.example.md6be.repository.IAddressRepository;
import com.example.md6be.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService implements IAddressService {
    @Autowired
    IAddressRepository addressRepository;

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }
}
