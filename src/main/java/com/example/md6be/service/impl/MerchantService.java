package com.example.md6be.service.impl;

import com.example.md6be.model.AppUser;
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
    @Override
    public Page<Merchant> findAll(Pageable pageable) {
        return merchantRepository.findAll(pageable);
    }
    @Override
    public Merchant save(Merchant merchant){
        return merchantRepository.save(merchant);
    }
    @Override
    public List<Merchant> getWaitingAcceptMerchant(){
        return merchantRepository.getWaitingAcceptMerchant();
    }
    @Override
    public void deleteSeller(Long id) {
        merchantRepository.deleteById(id);
    }
    @Override
    public Optional<Merchant> findById(Long id){
        return merchantRepository.findById(id);
    }
    @Override
    public Iterable<Merchant> findByName(String name){
        return merchantRepository.findAllByNameContaining(name);
    }

    @Override
    public List<Merchant> showActiveSeller(Boolean isActive){
        return merchantRepository.findMerchantByIsAccept(isActive);
    }
    @Override
    public Page<Merchant> showMerchant(Pageable pageable){
        return merchantRepository.showMerchant(pageable);
    }
    @Override
    public Merchant findByAppUser(AppUser appUser) {
        return merchantRepository.findMerchantByAppUser(appUser);
    }
    @Override
    public Merchant findMerchantById(Long id) {
        return merchantRepository.findMerchantById(id);
    }
    @Override
    public List<Merchant> filterMerchantByNameDown(){
        return merchantRepository.filterCustomerByNameDesc();
    }
    @Override
    public List<Merchant> filterMerchantByNameUp(){
        return merchantRepository.filterCustomerByNameAsc();
    }

    @Override
    public List<Merchant> findMerchantByPhoneNumber(String numberPhone) {
        return null;
    }

    @Override
    public Merchant findByAppUserId(Long id) {
        return merchantRepository.findByAppUserId(id);
    }

}
