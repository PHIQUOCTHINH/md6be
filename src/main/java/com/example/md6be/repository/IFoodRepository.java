package com.example.md6be.repository;

import com.example.md6be.model.Food;
import com.example.md6be.model.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findFoodByMerchant(Merchant merchant);
    List<Food> findFoodsByMerchantId(Long id);

    @Query(nativeQuery = true, value = "SELECT * FROM food where merchant_id in (select merchant.id from merchant where user_id = :id )")
    List<Food> findAllByUserId(@Param("id") Long id);

    @Query(nativeQuery = true, value = " SELECT * FROM food where (merchant_id in (select merchant.id from merchant where user_id = :id )) and (food.name like :name)")
    List<Food> findByLikeName(@Param("id") Long id ,@Param("name") String name);
    @Query(nativeQuery = true, value = "SELECT * FROM food where merchant_id in (select merchant.id from merchant where is_accept=1 and is_active=1)")
    List<Food> findAllFoodByStatus();

    Optional<Food> findById(Long id);

    @Query(value = "select*from food where merchant_id in (select merchant.id from merchant where user_id = :id )  ", nativeQuery = true)
    List<Food> findFoodsByUserId(@Param("id") Long id);
    @Query(value = "select * from food p where p.name like ?1 and merchant_id in (select id from merchant where user_id = ?2)", nativeQuery = true)
    List<Food> findFood( String name,Long id);

    @Query(value = "select*from food where merchant_id in (select merchant.id from merchant where is_active = 1 ) and food_category_id= :id", nativeQuery = true)
    List<Food> findAllByFoodCategoryAndAndMerchantIsActive(@Param("id") Long id);


    @Query(nativeQuery = true, value = " SELECT * FROM food where (merchant_id in (select merchant.id from merchant where is_accept=1 and is_active=1)) and (food.name like :name)")
    List<Food> findAllByLikeName(@Param("name") String name);
}