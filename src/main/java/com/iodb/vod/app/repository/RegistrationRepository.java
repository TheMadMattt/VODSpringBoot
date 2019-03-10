package com.iodb.vod.app.repository;

import com.iodb.vod.app.dao.CustomerDAO;
import com.iodb.vod.app.model.CustomerModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Handles registration transactions
 */
public class RegistrationRepository {

    private CustomerDAO customerDAO;

    @Autowired
    public void setCustomerRepository(CustomerDAO customerDAO) {
	this.customerDAO = customerDAO;
    }

    public CustomerModel findByLogin(String login) {
	return customerDAO.findByLogin(login);
    }

    public List<CustomerModel> getNewUsers() {
	return customerDAO.findAllByUserStatus(CustomerModel.UserStatus.NEW);
    }

    public List<CustomerModel> getAllUsersWithoutAdmin() {
	return customerDAO.findAllByUserStatusWithout(CustomerModel.UserStatus.ADMINISTRATOR);
    }

    public boolean register(CustomerModel customerModel) {
	boolean isInDatabase = customerDAO.existsByLoginOrEmail(
		customerModel.getLogin(), customerModel.getEmail());

	if (isInDatabase) {
	    return false;
	} else {
	    customerModel.setUserStatus(CustomerModel.UserStatus.NEW);
	    String pass = new BCryptPasswordEncoder().encode(
		    customerModel.getPassword());

	    customerModel.setPassword(pass);

	    customerDAO.save(customerModel);
	}
	return true;
    }

    public boolean activateUser(Long userId) {
	CustomerModel customer = customerDAO.getOne(userId);

	if (customer != null) {
	    customer.setUserStatus(CustomerModel.UserStatus.ACTIVE);
	    customerDAO.save(customer);
	    return true;
	}
	return false;
    }

    public boolean deleteCustomer(Long id) {
	boolean isInDataBase = customerDAO.existsById(id);
	if (isInDataBase) {
	    customerDAO.deleteById(id);
	    return true;
	}
	return false;
    }
}
