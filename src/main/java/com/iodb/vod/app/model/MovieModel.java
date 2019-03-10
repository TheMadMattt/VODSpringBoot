package com.iodb.vod.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Movie model
 */
@Entity
@Table(name = "Movie")
public class MovieModel implements Serializable {

    public MovieModel() {
    }

    public MovieModel(String title, String director,
	    int price, Date date, String description,
	    GenreEnum genre, int runTime, String fileName) {
	this.title = title;
	this.director = director;
	this.price = price;
	this.makeDate = date;
	this.description = description;
	this.genre = genre;
	this.runTime = runTime;
	this.fileName = fileName;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ4")
    @SequenceGenerator(sequenceName = "movie_seq",
	    allocationSize = 1, name = "CUST_SEQ4")
    Long id;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date makeDate;

    String title;
    String director;
    int price;
    String description;
    GenreEnum genre;
    int runTime;
    String fileName;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getDirector() {
	return director;
    }

    public void setDirector(String director) {
	this.director = director;
    }

    public int getPrice() {
	return price;
    }

    public void setPrice(int price) {
	this.price = price;
    }

    public Date getMakeDate() {
	return makeDate;
    }

    public void setMakeDate(Date makeDate) {
	this.makeDate = makeDate;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public GenreEnum getGenre() {
	return genre;
    }

    public void setGenre(GenreEnum genre) {
	this.genre = genre;
    }

    public int getRunTime() {
	return runTime;
    }

    public void setRunTime(int runTime) {
	this.runTime = runTime;
    }

    public String getFileName() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = fileName;
    }

    public enum GenreEnum {
	HORROR, DRAMA, ACTION, ADVENTURE, MUSICAL, HISTORICAL, ANIME, ANIMATION
    }

    @Override
    public String toString() {
	return "MovieModel{" + "id=" + id + ", makeDate=" + makeDate
		+ ", title=" + title + ", director=" + director + ", price="
		+ price + ", description=" + description + ", genre=" + genre
		+ ", runTime=" + runTime + ", fileName=" + fileName + '}';
    }
}
