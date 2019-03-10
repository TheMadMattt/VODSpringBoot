package com.iodb.vod.app.dbutils;

import com.iodb.vod.app.model.MovieModel;
import java.util.Date;
import java.util.List;

/**
 * Build and execute search query
 */
  public class MovieSearchBuilder {
        
        private String director;
        private String title;
        private MovieModel.GenreEnum genre;
        private Date date;
        private int price;
        private int runTime;
        private String fileName;
        private String description;
        
        public MovieSearchBuilder setDirector(String director){
            this.director = director;
            return this;
        }
        
        public MovieSearchBuilder setTitle(String title){
            this.title = title;
            return this;
        }
        
        public MovieSearchBuilder setGenre(MovieModel.GenreEnum genre){
            this.genre = genre;
            return this;
        }
        
        public MovieSearchBuilder setDate(Date date){
            this.date = date;
            return this;
        }
        
        public MovieSearchBuilder setPrice(int price){
            this.price = price;
            return this;
        }

        public MovieSearchBuilder setRunTime(int runTime) {
            this.runTime = runTime;
            return this;
        }

        public MovieSearchBuilder setFileName(String fileName) {
            this.fileName = fileName;
            return this;
        }

        public MovieSearchBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public List<MovieModel> build(){
            //todo
            return null;
        }
    }
