package ti.techacademy.workshop.product.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductByCustomer  implements Serializable{
    
    private long customer;
    private long code;
    private String description;
    private Category category;

}
