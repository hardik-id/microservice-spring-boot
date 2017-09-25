package hello.repository;

import hello.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    public Product findByName(String name);
    public Product findById(String id);
    public List<Product> findByCategory(String category);

}
