/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.products;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {


    @Test
    @DisplayName("Product -> should return Product with correct values -> when called with same values")
    void Product_ShouldReturnProductWithCorrectValues_WhenCalledWithSameValues(){
        String id = "4005500087151";
        String name = "Product";
        BigDecimal bd = new BigDecimal("50");

        Product p = new Product("4005500087151","Product", new BigDecimal("50"));

        assertEquals(id, p.id());
        assertEquals(name, p.name());
        assertEquals(bd, p.price());
    }


}