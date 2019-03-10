package com.iodb.vod.app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Model for service load table
 */
@Entity
@Table(name = "Load")
public class LoadModel implements Serializable {

    public LoadModel() {}

    public LoadModel(MovieModel movie, int currentLoad) {
        this.movie = movie;
        this.currentLoad = currentLoad;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ6")
    @SequenceGenerator(sequenceName = "load_seq",
            allocationSize = 1, name = "CUST_SEQ6")
    Long id;
    
    @OneToOne
    MovieModel movie;    
    
    int currentLoad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(int currentLoad) {
        this.currentLoad = currentLoad;
    }

    @Override
    public String toString() {
        return "LoadModel{" + "id=" + id + ", movie=" + movie.id + ","
                + " currentLoad=" + currentLoad + '}';
    }
}
