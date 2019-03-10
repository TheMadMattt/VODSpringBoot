package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieDAO extends JpaRepository<MovieModel, Long> {

    @Query(value = "SELECT case when count(t)>0 THEN true ELSE false END"
	    + " FROM MovieModel t WHERE t.title = ?1 OR t.fileName = ?2")
    boolean existsByTitleOrFilename(String title, String filename);
}
