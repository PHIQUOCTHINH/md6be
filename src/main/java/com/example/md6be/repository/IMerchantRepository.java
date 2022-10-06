package com.example.md6be.repository;

import com.example.md6be.model.AppUser;
import com.example.md6be.model.Merchant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant,Long> {
    Page<Merchant> findAll(Pageable pageable);
    @Query(nativeQuery = true, value = "select * from merchant where is_accept = false;")
    List<Merchant> getWaitingAcceptMerchant();

    @Query(nativeQuery = true, value = "SELECT * FROM merchant where name like concat('%',:name,'%');")
    Iterable<Merchant> findAllByNameContaining(String name);

    List<Merchant> findMerchantByIsAccept(Boolean isAccept);

    @Query(nativeQuery = true, value = "select * from merchant where is_accept = true;")
    Page<Merchant>showMerchant(Pageable pageable);

    Merchant findMerchantByAppUser(AppUser appUser);

    Merchant findMerchantById(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.merchant order by name desc;")
    List<Merchant> filterCustomerByNameDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM md6_case.merchant order by name asc;")
    List<Merchant> filterCustomerByNameAsc();





}
