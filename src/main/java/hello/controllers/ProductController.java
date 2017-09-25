package hello.controllers;

import hello.model.Cart;
import hello.model.Product;
import hello.services.CartService;
import hello.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @RequestMapping("health-check")
    public String okay(){
        return "okay";
    }

    @RequestMapping(path = "initialize", method = RequestMethod.POST)
    public DeferredResult<List<Product>> initializeProducts(){
        DeferredResult<List<Product>> result = new DeferredResult<>();
        productService.intializeDefaultProducts()
                .subscribe((products -> {
                    System.out.println("INITIALIZED PRODUCTS======================================");
                    for(Product p: products){
                        System.out.println(p);
                    }
                    result.setResult(products);
                }));
        return result;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public DeferredResult<List<Product>> getProducts(){
        DeferredResult<List<Product>> result = new DeferredResult<>();
        productService.getAllProducts()
                .subscribe((products -> {
                    System.out.println("List OF PRODUCTS======================================");
                    for(Product p: products){
                        System.out.println(p);
                    }
                    result.setResult(products);
                }));
        return result;
    }
/*
    @RequestMapping(path = "/{userId}", method = RequestMethod.GET)
    public DeferredResult<List<Cart>> getCartByUsers(@PathVariable(value="userId") String userId){
        DeferredResult<List<Cart>> result = new DeferredResult<>();
        cartService.getCartByUserId(userId)
                .subscribe((cart -> {
                    System.out.println("-------------get By User---------------------");
                    System.out.println(cart);
                    result.setResult(cart);
                }));
        return result;
    }*/
/*
    @RequestMapping(path = "interest-rates", method = RequestMethod.GET)
    public DeferredResult<InterestRatesListModel> getInterestRates() {
        DeferredResult<InterestRatesListModel> result = new DeferredResult<>();
        this.mortgageService.getInterestRatesList()
                .subscribe((interestRatesListModel) -> {
                    result.setResult(interestRatesListModel);
                });
        return result;
    }

    @RequestMapping(path = "mortgage-check",method = RequestMethod.POST)
    public MortgageResponse mortgageCheck(@RequestBody MortgageRequest mortgageRequest) {
        return this.mortgageService.calculateMorgage(mortgageRequest);
    }*/
}
