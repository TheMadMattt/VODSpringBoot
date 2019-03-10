package com.iodb.vod.conf;

import com.iodb.vod.app.model.CustomerModel;
import com.iodb.vod.app.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    
    private RegistrationRepository registrationRepository;
    
    @Autowired
    public void setRegistrationRepository(
            RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        CustomerModel user = registrationRepository.findByLogin(username);

        UserBuilder builder = null;
        if (user != null) {

            String role = getCustomerRole(user);
            if(role.equals("INVALID")){
                throw new UsernameNotFoundException("User has invalid role.");
            }
            
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(user.getPassword());
            builder.roles(role);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }

        return builder.build();
    }
    
    public String getCustomerRole(CustomerModel customerModel) {
        switch (customerModel.getUserStatus()) {
            case ACTIVE:
                return "USER";
            case ADMINISTRATOR:
                return "ADMIN";
            default:
                return "INVALID";
        }
    }
}
