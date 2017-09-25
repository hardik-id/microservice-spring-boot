package hello.model;

import org.springframework.data.annotation.Id;

public class Product {

    @Id
    public String id;

    public String name;
    public String category;

    public Product() {}

    public Product(String name, String category) {
        this.name = name;
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', category='%s']",
                id, name, category);
    }

}

