package spring.data.products.model;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "product_id")
    private Long id;

    @Column (name = "title_fld")
    private String title;

    @Column (name = "price_fld")
    private int price;

}
