package com.iodb.vod;

import com.iodb.vod.app.dao.CartDAO;
import com.iodb.vod.app.dao.CustomerDAO;
import com.iodb.vod.app.dao.LoadDAO;
import com.iodb.vod.app.dao.MovieDAO;
import com.iodb.vod.app.dao.MovieInCartDAO;
import com.iodb.vod.app.dao.RentalDAO;
import com.iodb.vod.app.model.CartModel;
import com.iodb.vod.app.model.CustomerModel;
import com.iodb.vod.app.model.LoadModel;
import com.iodb.vod.app.model.MovieInCartModel;
import com.iodb.vod.app.model.MovieModel;
import com.iodb.vod.app.model.RentalModel;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbConnectionTest {

        static CartDAO cartRepository;
        static CustomerDAO customerRepository;
        static MovieDAO movieRepository;
        static MovieInCartDAO moviesInCartRepository;
        static RentalDAO rentalRepository;
        static LoadDAO loadDao;
        
        @Autowired
        public void setCartRepository(CartDAO cartRepository){
            DbConnectionTest.cartRepository = cartRepository;
        }
        
        @Autowired
        public void setCustomerRepository(
                CustomerDAO customerRepository){
            DbConnectionTest.customerRepository = customerRepository;
        }
        
        @Autowired
        public void MovieRepository(MovieDAO movieRepository){
            DbConnectionTest.movieRepository = movieRepository;
        }
        
        @Autowired
        public void setMoviesInCartRepository(
                MovieInCartDAO moviesInCartRepository){
            DbConnectionTest.moviesInCartRepository = moviesInCartRepository;
        }
        
        @Autowired
        public void setRentalRepository(RentalDAO rentalRepository){
            DbConnectionTest.rentalRepository = rentalRepository;
        }
        
        @Autowired
        public void setLoadDao(LoadDAO loadDAO){
            DbConnectionTest.loadDao = loadDAO;
        }
    
	@Test
	public void contextLoads() {
            //db test, todo - move to test package
                
                MovieModel movie = new MovieModel("movie", "Mike",
                        21312, new Date(3444234), "desc",
                        MovieModel.GenreEnum.ACTION, 170, "sdsad.mpp4");
                
                movieRepository.save(movie);
                
                Random random = new Random();
                String newCustomerId = Integer.toString(random.nextInt());
                
                String pass = new BCryptPasswordEncoder().encode("pass");
                        
                CustomerModel customer = new CustomerModel(
                        newCustomerId, pass,
                        "sds@mail.com", CustomerModel.UserStatus.ACTIVE, null);
                
                customerRepository.save(customer);
                
                RentalModel rental = new RentalModel(customer, movie,
                        new Date(10000), new Date(1000000));
                
                rentalRepository.save(rental);
                
                CartModel cart = new CartModel(customer,
                        CartModel.CartStatus.PURCHASED);
                
                cartRepository.save(cart);
                
                //update customer
                Optional<CustomerModel> newCustomer 
                        = customerRepository.findById(
                                (Long)customerRepository.count());
                
                if(newCustomer.isPresent()){
                    customer = newCustomer.get();
                }
                
                customer.setCurrentCart(cart);
                customerRepository.save(customer);
                
                MovieInCartModel moviesInCart 
                        = new MovieInCartModel(cart, movie);
                
                moviesInCartRepository.save(moviesInCart);
                
                LoadModel load = new LoadModel(movie, 200);
                loadDao.save(load);
                
                System.out.println("Cart table");
                cartRepository.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println("Customer table");
                customerRepository.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println("Movie table");
                movieRepository.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println("MoviesInCart table");
                moviesInCartRepository.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println("Rental table");
                rentalRepository.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println("Load table");
                loadDao.findAll().forEach((model) -> {
                    System.out.println(model.toString());
                });
                
                System.out.println(customerRepository.findByLogin(
                        newCustomerId).toString());
	}

}
