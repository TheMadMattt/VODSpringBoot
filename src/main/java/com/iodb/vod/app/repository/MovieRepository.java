package com.iodb.vod.app.repository;

import com.iodb.vod.app.dao.MovieDAO;
import com.iodb.vod.app.model.MovieModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Handles operations on Movie entries
 */
public class MovieRepository {

    private MovieDAO movieDAO;

    @Autowired
    public void setMovieDAO(MovieDAO movieDAO) {
	this.movieDAO = movieDAO;
    }

    public boolean addNewMovie(MovieModel movieModel) {
	boolean isInDatabase = movieDAO.existsByTitleOrFilename(
		movieModel.getTitle(), movieModel.getFileName());

	if (isInDatabase) {
	    return false;
	} else {
	    movieDAO.save(movieModel);
	}

	return true;
    }

    public boolean modifyMovie(MovieModel movieModel) {
	movieDAO.save(movieModel);
	return true;
    }

    public boolean deleteMovie(Long id) {
	boolean isInDataBase = movieDAO.existsById(id);
	if (isInDataBase) {
	    movieDAO.deleteById(id);
	    return true;
	}
	return false;
    }

    public List<MovieModel> getAll() {
	return movieDAO.findAll();
    }
    
    public MovieModel getMovie(Long id){
	return movieDAO.getOne(id);
    }
}
