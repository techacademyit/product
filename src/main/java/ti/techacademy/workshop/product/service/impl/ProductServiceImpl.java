package ti.techacademy.workshop.product.service.impl;
 
import java.util.Objects;

import ti.techacademy.workshop.product.model.Category;
import ti.techacademy.workshop.product.model.Product;
import ti.techacademy.workshop.product.model.ProductByCustomer;
import ti.techacademy.workshop.product.service.ProductService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ResolvableType;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService{

   
    @Value("${app.product.resource.by-customer}") Resource resourceFileByCustomer;
    @Value("${app.product.resource.data}") Resource resourceFile;
     
    @Override
    public Flux<Product> getProducts() {
        return new Jackson2JsonDecoder()
                .decode(getData(resourceFile),ResolvableType.forClass(Product.class) , null, null)
                .cast(Product.class)
                .doOnNext( f -> log.info(f.toString()));

    }

    public Flux<Product> getProductsByCategory(Category category){
        Objects.requireNonNull(category, "getProductsByCategory");
        return getProducts()
                    .filter( p -> p.getCategory().equals(category));
    }

    public Flux<ProductByCustomer> getProductsByCustomer(Long code){
        Objects.requireNonNull(code, "getProductsByCustomer");
        return new Jackson2JsonDecoder()
                        .decode(getData(resourceFileByCustomer),ResolvableType.forClass(ProductByCustomer.class) , null, null)
                        .cast(ProductByCustomer.class)
                        .filter(p -> code.equals(p.getCustomer()) )
                        .doOnNext( f -> log.info(f.toString()));
    }

    private Flux<DataBuffer> getData(Resource resourceFile){
        return DataBufferUtils.read( resourceFile, new DefaultDataBufferFactory(), 4096);
    }

    



}