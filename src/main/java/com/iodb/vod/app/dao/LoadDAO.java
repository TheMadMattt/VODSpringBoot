package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.LoadModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Load entries DAO
 */
public interface LoadDAO extends JpaRepository<LoadModel, Long> {
    
}
