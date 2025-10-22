/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    @DisplayName("Order -> should return Order with correct values -> when called with same values")
    void Order_ShouldReturnOrderWithCorrectValues_WhenCalledWithSameValues() {
        String id = "1232145";
        List<String> productIds = List.of( "4005500087151", "4305500087421" );

        Order p = new Order( "1232145", productIds );

        assertEquals( id, p.id() );
        assertEquals( productIds, p.productIds() );
    }

}