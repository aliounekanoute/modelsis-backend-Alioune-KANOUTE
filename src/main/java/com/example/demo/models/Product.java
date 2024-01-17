package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;


@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Getter
@Setter
@Entity
@Table(name = "PRODUCTS")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends InheritModel {
    @Column(unique = true, name = "product_name")
    String name;
    @OneToOne()
    ProductType type;
}
