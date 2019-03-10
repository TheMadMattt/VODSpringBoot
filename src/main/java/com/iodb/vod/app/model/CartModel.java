
package com.iodb.vod.app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Cart model - cart status, owner User id
 */
@Entity
@Table(name = "Cart")
public class CartModel implements Serializable {
    
    public CartModel(){}

    public CartModel(CustomerModel user, CartStatus cartStatus) {
        this.user = user;
        this.cartStatus = cartStatus;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ1")
    @SequenceGenerator(sequenceName = "cart_seq",
            allocationSize = 1, name = "CUST_SEQ1")
    Long id;
    
    @ManyToOne
    CustomerModel user;
    
    CartStatus cartStatus;

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

    public CartStatus getCartStatus() {
        return cartStatus;
    }

    public void setCartStatus(CartStatus cartStatus) {
        this.cartStatus = cartStatus;
    }

    public enum CartStatus {
        OPEN, PAYMENT_PENDING, PURCHASED
    }

    @Override
    public String toString() {
        return "CartModel{" + "id=" + id + ", user=" + user.id +
                ", cartStatus=" + cartStatus + '}';
    }
}
