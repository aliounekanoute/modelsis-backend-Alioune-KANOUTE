package com.example.demo.controllers;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.services.dtos.ProductDto;
import com.example.demo.utils.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql("/init-db-test/product-data.sql")
public class ProductControllerTests {

    @Autowired
  private MockMvc mockMvc;

  private final String apiUri = "/product";

  @Test
  @DisplayName("Find all api test")
  @Transactional
  void findAllTest() throws Exception {
    mockMvc
            .perform(
                    get(apiUri).contentType(
                            MediaType.APPLICATION_JSON
                    ))
            .andExpect(status().isOk())
            .andExpect(jsonPath("[*].name").value(Matchers.hasItem("product 1")));
  }


  @Test
  @DisplayName("Create api test")
  @Transactional
  void createTest() throws Exception {

    mockMvc.perform(
                    post(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(buildProductDto()))
            )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("name").value("product 3"))
            .andDo(print());
  }

  @Test
  @DisplayName("Update api test")
  @Transactional
  void putTest() throws Exception {
        String id = "ddedaa41-85ea-4a38-9db3-001992d33ea3";
        ProductDto productDto = buildProductDto();
        productDto.setId(UUID.fromString(id));

    mockMvc.perform(
                    put(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(productDto))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("product 3"))
            .andExpect(jsonPath("id").value(id))
            .andDo(print());
  }

  @Test
  @DisplayName("Update api test throws 400")
  @Transactional
  void putTestThrows400() throws Exception {

    mockMvc.perform(
                    put(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(buildProductDto()))
            )
            .andExpect(status().isBadRequest())
            .andExpect(status().reason(Matchers.containsString("id should not be null")));
  }

  @Test
  @DisplayName("Update api test throws 404")
  @Transactional
  void putTestThrows404() throws Exception {
        String id = "ddedaa41-85ea-4a38-9db3-001992d33ea6";
        ProductDto productDto = buildProductDto();
        productDto.setId(UUID.fromString(id));

    mockMvc.perform(
                    put(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(productDto))
            )
            .andExpect(status().isNotFound())
            .andExpect(status().reason(Matchers.containsString(String.format("Product with id %s not found", productDto.getId()))));
  }

  private ProductDto buildProductDto() {
    return ProductDto
        .builder()
        .name("product 3")
        .typeName("type 1")
        .build();
  }
    
}
