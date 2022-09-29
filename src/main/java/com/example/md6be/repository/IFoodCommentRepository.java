package com.example.md6be.repository;

import com.example.md6be.model.FoodComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFoodCommentRepository extends JpaRepository<FoodComment,Long> {
}
