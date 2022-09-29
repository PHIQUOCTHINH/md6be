package com.example.md6be.repository;

import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMerchantRepository extends JpaRepository<Merchant,Long> {
}
