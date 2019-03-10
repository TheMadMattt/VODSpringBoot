package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.MovieInCartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieInCartDAO 
        extends JpaRepository<MovieInCartModel, Long> {
    
}
