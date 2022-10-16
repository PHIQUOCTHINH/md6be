package com.example.md6be.service;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IMerchantService {
    public Page<Merchant> findAll(Pageable pageable);
    public Merchant save(Merchant merchant);
    public List<Merchant> getWaitingAcceptMerchant();
    public void deleteMerchant(Long id);
    public Optional<Merchant> findById(Long id);
    public Iterable<Merchant> findByName(String name);
    public List<Merchant> showActiveMerchant(Boolean isActive);
    public Page<Merchant> showMerchant(Pageable pageable);
    public Merchant findByAppUser(AppUser appUser);
    public Merchant findMerchantById(Long id);
    public List<Merchant> filterMerchantByNameDown();
    public List<Merchant> filterMerchantByNameUp();
    List<Merchant> findAllMerchant();
    List<Merchant> findMerchantByPhoneNumber(String numberPhone);
    List<Merchant> findAll();
    List<Merchant> findMerchantByAddress(@Param("name") String name);
}
