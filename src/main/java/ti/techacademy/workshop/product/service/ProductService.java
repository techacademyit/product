package ti.techacademy.workshop.product.service;

import ti.techacademy.workshop.product.model.Category;
import ti.techacademy.workshop.product.model.Product;
import ti.techacademy.workshop.product.model.ProductByCustomer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface ProductService{

    public Flux<Product> getProducts();

    public Mono<Product> getProducts(Long code);

    public Flux<ProductByCustomer> getProductsByCustomer(Long code);

    public Flux<Product> getProductsByCategory(Category category);
}