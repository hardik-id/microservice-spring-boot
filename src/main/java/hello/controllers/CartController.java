package hello.controllers;

import hello.model.Cart;
import hello.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("user/{userId}/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping("health-check")
    public String okay(){
        return "okay";
    }

    @RequestMapping(path = "/product/{productId}", method = RequestMethod.POST)
    public DeferredResult<Cart> addToCart(
            @RequestParam("quantity") Integer quantity,
            @PathVariable(value="userId") String userId,
            @PathVariable(value="productId") String productId){
        DeferredResult<Cart> result = new DeferredResult<>();
        cartService.addToCart(userId, productId, quantity)
                .subscribe((cart -> {
                    System.out.println("-------------added Following---------------------");
                    System.out.println(cart);
                    result.setResult(cart);
                }));
        return result;
    }

    @RequestMapping(path = "/{cartId}", method = RequestMethod.PUT)
    public DeferredResult<Cart> updateQuatityToCart(
            @PathVariable(value="cartId") String cartId,
            @RequestParam("quantity") Integer quantity){
        DeferredResult<Cart> result = new DeferredResult<>();
        cartService.updateQuanityToCart(cartId, quantity)
                .subscribe((cart -> {
                    System.out.println("-------------added Following---------------------");
                    System.out.println(cart);
                    result.setResult(cart);
                }));
        return result;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public DeferredResult<List<Cart>> getCartByUsers(@PathVariable(value="userId") String userId){
        DeferredResult<List<Cart>> result = new DeferredResult<>();
        cartService.getCartByUserId(userId)
                .subscribe((cart -> {
                    System.out.println("-------------get By User---------------------");
                    System.out.println(cart);
                    result.setResult(cart);
                }));
        return result;
    }

    @RequestMapping(path = "", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCartByUsers(@PathVariable(value="userId") String userId){
        cartService.deleteByUserId(userId)
                .subscribe((cart -> {
                    System.out.println("-------------delete By User---------------------");
                    System.out.println(cart.size());
                }));
    }

    @RequestMapping(path = "/{cartId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteCart(@PathVariable(value="cartId") String cartId){
        cartService.deleteByCartId(cartId)
                .subscribe((status -> {
                    System.out.println("-------------delete By ID---------------------");
                    System.out.println(status);
                }));
    }
}
