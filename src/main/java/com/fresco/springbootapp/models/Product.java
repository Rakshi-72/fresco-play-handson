package com.fresco.springbootapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "sellerId", referencedColumnName = "userId", updatable = false)
    @JsonIgnore
    private User seller;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;
}
