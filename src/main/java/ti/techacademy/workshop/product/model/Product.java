package ti.techacademy.workshop.product.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable{

    private long code;
    private String description;
    private Category category;

}