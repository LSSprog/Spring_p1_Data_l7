package spring.data.products.repositories;

import org.apache.catalina.LifecycleState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.data.products.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { // указываем скщность с которой работаем и какой тип у primary key
    List<Product> findAllByPriceGreaterThanEqual (int min_price);
    List<Product> findAllByPriceLessThanEqual (int max_price);
    List<Product> findAllByPriceBetween (int min, int max);
}
