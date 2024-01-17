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

import com.example.demo.services.dtos.ProductTypeDto;
import com.example.demo.utils.TestUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql("/init-db-test/product-type-data.sql")
public class ProductTypeControllerTests {
  @Autowired
  private MockMvc mockMvc;

  private final String apiUri = "/productType";

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
            .andExpect(jsonPath("[*].name").value(Matchers.hasItem("type 1")));
  }


  @Test
  @DisplayName("Create api test")
  @Transactional
  void createTest() throws Exception {

    mockMvc.perform(
                    post(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(builProductTypeDto()))
            )
            .andExpect(status().isCreated())
            .andExpect(jsonPath("name").value("product type"))
            .andDo(print());
  }

  @Test
  @DisplayName("Update api test")
  @Transactional
  void putTest() throws Exception {
        String id = "ddedaa41-85ea-4a38-9db3-001992d33ea0";
        ProductTypeDto productTypeDto = builProductTypeDto();
        productTypeDto.setId(UUID.fromString(id));

    mockMvc.perform(
                    put(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(productTypeDto))
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("name").value("product type"))
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
                            .content(TestUtil.convertObjectToJsonBytes(builProductTypeDto()))
            )
            .andExpect(status().isBadRequest())
            .andExpect(status().reason(Matchers.containsString("id should not be null")));
  }

  @Test
  @DisplayName("Update api test throws 404")
  @Transactional
  void putTestThrows404() throws Exception {
        String id = "ddedaa41-85ea-4a38-9db3-001992d33ea6";
        ProductTypeDto productTypeDto = builProductTypeDto();
        productTypeDto.setId(UUID.fromString(id));

    mockMvc.perform(
                    put(apiUri)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(TestUtil.convertObjectToJsonBytes(productTypeDto))
            )
            .andExpect(status().isNotFound())
            .andExpect(status().reason(Matchers.containsString(String.format("Product type with id %s not found", productTypeDto.getId()))));
  }


  private ProductTypeDto builProductTypeDto() {
        return ProductTypeDto
                .builder()
                .name("product type")
                .build();
  }

}
