package hello.services;

import hello.model.Cart;
import hello.model.Product;
import hello.repository.CartRepository;
import hello.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartRepository cartRepository;


    public Observable<List<Cart>> getCartByUserId(String userId){
        return Observable.just(cartRepository.findByUserId(userId));
    }

    public Observable<Cart> addToCart(String userId, String productId, Integer quantity){
        Cart saved= null;
        Product p = productRepository.findById(productId);
        if(p !=null){
            Cart cart = new Cart(userId, p, quantity);
            saved = cartRepository.save(cart);
        }
        return Observable.just(saved);
    }


    public Observable<List<Cart>> deleteByUserId(String userId){
        return Observable.just(cartRepository.deleteByUserId(userId));
    }

    public Observable<String> deleteByCartId(String cartId) {
        cartRepository.delete(cartId);
        return Observable.just("Ok");
    }

    public Observable<Cart> updateQuanityToCart(String cartId, Integer quantity) {
        Cart cart = cartRepository.findOne(cartId);
        cart.quantity = quantity;
        return Observable.just(cartRepository.save(cart));
    }
}
