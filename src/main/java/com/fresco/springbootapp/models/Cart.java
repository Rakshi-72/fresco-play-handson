package com.fresco.springbootapp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    private Double totalAmount;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartProduct> cartProduct;
}
