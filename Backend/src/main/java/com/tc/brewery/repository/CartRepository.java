package com.tc.brewery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.tc.brewery.entity.Cart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
//    Cart findByUserId(Long userId);
//    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems WHERE c.user.id = :userId")
//    Cart findCartByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems WHERE c.user.id = :userId")
    List<Cart> findCartsByUserId(@Param("userId") Long userId);

}
