package com.iodb.vod.app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Movie in cart model - one item in cart 
 */
@Entity
@Table(name = "Movie_in_cart")
public class MovieInCartModel implements Serializable {
    
    public MovieInCartModel(){}

    public MovieInCartModel(CartModel cart, MovieModel movie) {
        this.cart = cart;
        this.movie = movie;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ3")
    @SequenceGenerator(sequenceName = "movie_in_cart_seq",
            allocationSize = 1, name = "CUST_SEQ3")
    Long id;
    
    @ManyToOne
    CartModel cart;
    
    @ManyToOne
    MovieModel movie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CartModel getCart() {
        return cart;
    }

    public void setCart(CartModel cart) {
        this.cart = cart;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    @Override
    public String toString() {
        return "MoviesInCartModel{" + "id=" + id + ", cart=" + cart.id +
                ", movie=" + movie.id + '}';
    }
}
