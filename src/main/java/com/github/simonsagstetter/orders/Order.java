/*
 * Simon Sagstetter Copyright (c) 2025.
 */

package com.github.simonsagstetter.orders;

import com.github.simonsagstetter.products.Product;

import java.util.List;

public record Order(String id, List<String> productIds) {
}
