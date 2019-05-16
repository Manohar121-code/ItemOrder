package com.ecommerce.ItemOrder.service;

import javax.validation.constraints.NotNull;

import com.ecommerce.ItemOrder.Bean.Item;

/**
 * @author Manohar
 *
 */
public interface ItemService {

	@NotNull
	Iterable<Item> getAllProducts();

	Item getProduct(long id);

	Item save(Item item);

	Item deleteById(long id);
	
}
