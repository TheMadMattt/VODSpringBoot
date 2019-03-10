package com.iodb.vod.controller;

import com.iodb.vod.app.model.CustomerModel;
import com.iodb.vod.app.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationPageController {

    private RegistrationRepository registryRepository;

    @Autowired
    public void setRegistrationRepository(RegistrationRepository registryRepository) {
	this.registryRepository = registryRepository;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
	model.addAttribute("customer", new CustomerModel());
	return "register";
    }

    @RequestMapping(value = "/register", method = POST)
    public String registerSubmit(@ModelAttribute CustomerModel customer,
	    RedirectAttributes redirectAttributes, Model model) {
	if (registryRepository.register(customer)) {
	    model.addAttribute("register", "true");
	    return "login";
	} else {
	    redirectAttributes.addAttribute("error", "true");
	    return "redirect:/register";
	}
    }

    @GetMapping("/admin/new-customers")
    public String showNewCustomers(Model model) {
	model.addAttribute("customers", registryRepository.getNewUsers());
	return "newCustomers";
    }

    @RequestMapping(value = "/admin/new-customers/activate/{id}", method = GET)
    public String activateUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	//System.out.println(id);
	if (registryRepository.activateUser(id)) {
	    redirectAttributes.addAttribute("activated", "true");
	} else {
	    redirectAttributes.addAttribute("errorActivating", "true");
	}
	return "redirect:/admin/new-customers/";
    }

    @GetMapping("/admin/customers")
    public String showCustomers(Model model) {
	model.addAttribute("customers", registryRepository.getAllUsersWithoutAdmin());
	return "customers";
    }

    @RequestMapping(value = "/admin/customers/deleteCustomer/{id}", method = GET)
    public String deleteCustomer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
	//System.out.println(id);
	if (registryRepository.deleteCustomer(id)) {
	    redirectAttributes.addAttribute("deleted", "true");
	} else {
	    redirectAttributes.addAttribute("errorDelete", "true");
	}
	return "redirect:/admin/customers/";
    }
}
