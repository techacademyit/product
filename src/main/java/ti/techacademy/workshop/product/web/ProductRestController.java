package ti.techacademy.workshop.product.web;

import java.time.Duration;

import ti.techacademy.workshop.product.model.Product;
import ti.techacademy.workshop.product.model.ProductByCustomer;
import ti.techacademy.workshop.product.service.ProductService;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@RestController
public class ProductRestController {

    private ProductService service;

    @GetMapping( path = "/product",
                 produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, 
                              MediaType.APPLICATION_NDJSON_VALUE, 
                              MediaType.APPLICATION_JSON_VALUE}
                )
    public Flux<Product> getAll(@RequestParam(name="delay", defaultValue = "0") Integer delay){   
        return service.getProducts()
                      .delayElements(Duration.ofMillis(delay));
    }

    @GetMapping( path = "/product/by-customer/{customer}",
                 produces = { MediaType.APPLICATION_STREAM_JSON_VALUE, 
                              MediaType.APPLICATION_NDJSON_VALUE, 
                              MediaType.APPLICATION_JSON_VALUE}
                )
    public Flux<ProductByCustomer> getByCustomer(@RequestParam(name="delay", defaultValue = "0") Integer delay,  
                                       @PathVariable("customer" ) Long customer ){

            return service.getProductsByCustomer(customer);
  
    }
 
   
}
