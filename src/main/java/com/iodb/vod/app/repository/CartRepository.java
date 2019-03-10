package com.iodb.vod.app.repository;

import com.iodb.vod.app.model.CartModel;

/**
 * Handles operations on cart entries
 */
public class CartRepository {
    
    public void addMovieToCart(Long userID, Long movieId){
        
    }
    
    public CartModel getUserCurrentCart(Long userId){
        return null;
    }
    
    public CartModel createNewCart(Long userId){
        return null;
    }
    
    public boolean finalizeCart(Long userId){
       return true;
    }
}
