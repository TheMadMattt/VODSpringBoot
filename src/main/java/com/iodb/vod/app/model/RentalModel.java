package com.iodb.vod.app.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 * Rental model - info about rented movie
 */
@Entity
@Table(name = "Rental")
public class RentalModel implements Serializable {
    
    public RentalModel(){}

    public RentalModel(CustomerModel user, MovieModel movie,
            Date fromDate, Date toData) {
        this.user = user;
        this.movie = movie;
        this.fromDate = fromDate;
        this.toData = toData;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ5")
    @SequenceGenerator(sequenceName = "rental_seq",
            allocationSize = 1, name = "CUST_SEQ5")
    Long id;
       
    @ManyToOne
    CustomerModel user;
    
    @ManyToOne
    MovieModel movie;
    
    @Temporal(TemporalType.DATE)
    Date fromDate;
    
    @Temporal(TemporalType.DATE)
    Date toData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerModel getUser() {
        return user;
    }

    public void setUser(CustomerModel user) {
        this.user = user;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToData() {
        return toData;
    }

    public void setToData(Date toData) {
        this.toData = toData;
    }

    @Override
    public String toString() {
        return "RentalModel{" + "id=" + id + ", user=" + user.id + ", movie=" +
                movie.id + ", fromDate=" + fromDate + ", toData=" + toData + '}';
    }
}
