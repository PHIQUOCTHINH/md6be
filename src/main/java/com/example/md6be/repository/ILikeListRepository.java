package com.example.md6be.repository;


import com.example.md6be.model.LikeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ILikeListRepository extends JpaRepository<LikeList,Long> {
}
