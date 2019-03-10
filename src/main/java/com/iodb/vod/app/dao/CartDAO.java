package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * DAO for CartModel
 */
public interface CartDAO extends JpaRepository<CartModel, Long> {
    
}
