package com.example.demo.services.dtos;

import java.io.Serializable;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode()
@Builder(toBuilder = true)
public class ProductTypeDto implements Serializable {
    UUID id;
    @NotNull(message = "Product type name should not be null")
    @NotBlank(message = "Product type name should not be blank")
    String name;
}
