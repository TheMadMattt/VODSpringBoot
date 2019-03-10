package com.iodb.vod.controller;

import com.iodb.vod.app.model.MovieModel;
import com.iodb.vod.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MoviesController {

    private MovieRepository movieRepository;

    @Autowired
    public void setMovieRepository(MovieRepository movieRepository) {
	this.movieRepository = movieRepository;
    }

    @GetMapping("/movies")
    public String showAll(Model model) {
	model.addAttribute("movies", movieRepository.getAll());
	return "movies";
    }

    @GetMapping("/admin/movies/new")
    public String addMovieForm(Model model) {
	model.addAttribute("movie", new MovieModel());
	return "addMovie";
    }

    @RequestMapping(value = "/admin/movies/new", method = POST)
    public String addMovieSubmit(@ModelAttribute(value = "movie") MovieModel movie,
	    RedirectAttributes redirectAttributes) {
	if (movieRepository.addNewMovie(movie)) {
	    redirectAttributes.addAttribute("successful", "true");
	    return "redirect:/movies";
	} else {
	    redirectAttributes.addAttribute("error", "true");
	    return "redirect:/admin/movies/new";
	}
    }
    
    @RequestMapping(value = "/admin/movies/deleteMovie/{id}", method = GET)
    public String deleteMovie(@PathVariable Long id, RedirectAttributes redirectAttributes){
	//System.out.println(id);
	if(movieRepository.deleteMovie(id)){
	    redirectAttributes.addAttribute("deleted", "true");
	}else{
	    redirectAttributes.addAttribute("errorDelete", "true");
	}
	return "redirect:/movies/";
    }
    
    @RequestMapping(value = "/admin/movies/updateMovie/{id}")
    public String updateMovie(@PathVariable Long id, Model model,
	    RedirectAttributes redirectAttributes){
	//System.out.println(id);
	model.addAttribute("movie", movieRepository.getMovie(id));
	model.addAttribute("update", "true");

	return "updateMovie";
    }
    
    @RequestMapping(value = "/admin/movies/update", method = POST)
    public String updateMovieSubmit(@ModelAttribute(value = "movie") MovieModel movie,
	    RedirectAttributes redirectAttributes) {
	System.out.println(movie.getId());
	if (movieRepository.modifyMovie(movie)) {
	    redirectAttributes.addAttribute("updated", "true");
	    return "redirect:/movies";
	} else {
	    redirectAttributes.addAttribute("errorUpdate", "true");
	    return "redirect:/admin/movies/updateMovie/{id}";
	}
    }
}
