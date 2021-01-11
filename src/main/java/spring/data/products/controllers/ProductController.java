package spring.data.products.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.data.products.model.Product;
import spring.data.products.repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping ("/prod")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository; // это пока не правльно! (преподаватель сказал) потом будет правильно. Это пока облегчит код (сервис вроде надо ещё)

    @GetMapping
    public List<Product> getAllProducts(@RequestParam(name = "min", required = false) Integer min,
                                        @RequestParam(name = "max", required = false) Integer max) {
            if (min == null) {
                min = 0;
            }
            if (max == null) {
                max = Integer.MAX_VALUE;
            }
            return (List<Product>) productRepository.findAllByPriceBetween(min, max); //findAll();

    }

    @GetMapping ("/{id}")
    public Product getProductByID(@PathVariable Long id) {
        return productRepository.findById(id).get();
    }

    @GetMapping ("/delete/{id}")
    public String deleteProductById (@PathVariable Long id) {
        productRepository.deleteById(id);
        return "redirect:/prod"; // почему не срабатывает? 11/01 - я так понял и не сработает из-за RestController, тут ответ в json
    }
//    То что ниже теперь не нужно - добавил фильтр в getAllProducts
//    @GetMapping ("/filter_min")
//    public List<Product> getAllProductsMoreThan(@RequestParam int min_price) {
//        return (List<Product>) productRepository.findAllByPriceGreaterThanEqual(min_price);
//    }
//
//    @GetMapping ("/filter_max")
//    public List<Product> getAllProductsLessThan(@RequestParam int max_price) {
//        return productRepository.findAllByPriceLessThanEqual(max_price);
//    }
//
//    @GetMapping ("/filter_between")
//    public List<Product> getAllProductsBetween(@RequestParam int min, int max) {
//        return productRepository.findAllByPriceBetween(min, max);
//    }

    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        product.setId(null);
        return productRepository.save(product);
    }

}
