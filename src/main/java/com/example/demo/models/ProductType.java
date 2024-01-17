package com.example.demo.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "PRODUCT_TYPES")
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductType extends InheritModel {
    @Column(unique = true, name = "type_name")
    String name;
}
