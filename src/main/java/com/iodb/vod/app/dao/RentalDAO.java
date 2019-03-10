package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.RentalModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalDAO extends JpaRepository<RentalModel, Long> {
    
}
