package hello.model;

import org.springframework.data.annotation.Id;

public class Cart {

    @Id
    public String id;

    public String userId;
    public Product product;
    public Integer quantity;


    public Cart() {}

    public Cart(String userId, Product product, Integer quantity) {
        this.userId = userId;
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id='" + id + '\'' +
                ", user=" + userId +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

