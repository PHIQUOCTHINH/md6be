package com.example.md6be.service.impl;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import com.example.md6be.repository.IMerchantRepository;
import com.example.md6be.service.IMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MerchantService implements IMerchantService {
    @Autowired
    IMerchantRepository merchantRepository;

    public Page<Merchant> findAll(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }

    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }

    public List<Merchant> getWaitingAcceptMerchant(){
        return merchantRepository.getWaitingAcceptMerchant();
    }

    public void deleteSeller(Long id) {
        merchantRepository.deleteById(id);
    }

    public Optional<Merchant> findById(Long id){
        return merchantRepository.findById(id);
    }

    public Iterable<Merchant> findByName(String name){
        return merchantRepository.findAllByNameContaining(name);
    }


    public List<Merchant> showActiveSeller(Boolean isActive){
        return merchantRepository.findMerchantByIsAccept(isActive);
    }

    public Page<Merchant> showMerchant(Pageable pageable){
        return merchantRepository.showMerchant(pageable);
    }

    public Merchant findByAppUser(AppUser appUser) {
        return merchantRepository.findMerchantByAppUser(appUser);
    }

    public Merchant findMerchantById(Long id) {
        return merchantRepository.findMerchantById(id);
    }

    public List<Merchant> filterMerchantByNameDown(){
        return merchantRepository.filterCustomerByNameDesc();
    }

    public List<Merchant> filterMerchantByNameUp(){
        return merchantRepository.filterCustomerByNameAsc();
    }

    @Override
    public List<Merchant> findAllMerchant() {
        return merchantRepository.findAll();
    }
}

