package hello.repository;

import hello.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, String> {

    public List<Cart> findByUserId(String userId);
    public List<Cart> deleteByUserId(String userId);

}
