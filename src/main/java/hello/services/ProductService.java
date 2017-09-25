package hello.services;

import hello.model.*;
import hello.repository.CartRepository;
import hello.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Completable;
import rx.Observable;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;


    public void test(){

        cartRepository.deleteAll();



        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Product customer : productRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Customer found with findByFirstName('Alice'):");
        System.out.println("--------------------------------");
        System.out.println(productRepository.findByName("Canon D1300"));

        cartService.getCartByUserId("2").subscribe((carts -> {
            for(Cart cart: carts){
                System.out.println(cart);
            }
        }));
    }

    public Observable<List<Product>> intializeDefaultProducts() {
        productRepository.deleteAll();

        // save a couple of customers
        productRepository.save(new Product("Canon D1300", "Camera"));
        productRepository.save(new Product("Nikon 700D", "Camera"));
        productRepository.save(new Product("iPhone 7 Plus", "Mobile"));
        productRepository.save(new Product("Samsung Note 8 Plus", "Mobile"));
        productRepository.save(new Product("OnePlus 5", "Mobile"));
        return Observable.just(productRepository.findAll());
    }

    public Observable<List<Product>> getAllProducts(){
        return Observable.just(productRepository.findAll());
    }
}
