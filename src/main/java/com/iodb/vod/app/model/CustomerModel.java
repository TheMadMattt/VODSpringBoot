package com.iodb.vod.app.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * User model - user info and access to current Cart
 */
@Entity
@Table(name = "Customer")
public class CustomerModel implements Serializable {
    
    public CustomerModel(){}

    public CustomerModel(String login,
            String password, String email,
            UserStatus userStatus, CartModel currentCart) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
        this.currentCart = currentCart;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUST_SEQ2")
    @SequenceGenerator(sequenceName = "customer_seq",
            allocationSize = 1, name = "CUST_SEQ2")
    Long id;
    
    @OneToOne
    CartModel currentCart;
    
    String login;
    String password;
    String email;
    UserStatus userStatus;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public CartModel getCurrentCart() {
        return currentCart;
    }

    public void setCurrentCart(CartModel currentCart) {
        this.currentCart = currentCart;
    }

    public enum UserStatus {
        NEW, ACTIVE, BANNED, REMOVED, ADMINISTRATOR, MODERATOR
    }

    @Override
    public String toString() {
        return "CustomerModel{" + "id=" + id + ", currentCart=" + currentCart.id +
                ", login=" + login + ", password=" + password + ", email=" +
                email + ", userStatus=" + userStatus + '}';
    }
}
