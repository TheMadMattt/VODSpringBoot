package com.iodb.vod.app.dao;

import com.iodb.vod.app.model.CustomerModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * DAO for CustomerModel
 */
public interface CustomerDAO extends JpaRepository<CustomerModel, Long> {
    
    @Query("SELECT t FROM CustomerModel t WHERE t.login = ?1")
    CustomerModel findByLogin(String login);
    
    @Query(value = "SELECT case when count(t)>0 THEN true ELSE false END"
            + " FROM CustomerModel t WHERE t.login = ?1 OR t.email = ?2")
    boolean existsByLoginOrEmail(String login, String email);
    
    @Query(value = "SELECT t FROM CustomerModel t WHERE t.userStatus = ?1")
    List<CustomerModel> findAllByUserStatus(CustomerModel.UserStatus status);
    
    @Query(value = "SELECT t FROM CustomerModel t WHERE t.userStatus != ?1")
    List<CustomerModel> findAllByUserStatusWithout(CustomerModel.UserStatus status);
}
