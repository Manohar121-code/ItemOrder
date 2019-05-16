package com.ecommerce.ItemOrder.controller;

import java.net.URI;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.ItemOrder.Bean.Item;
import com.ecommerce.ItemOrder.service.ItemService;

/**
 * @author Manohar
 *
 */
@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping(value = { "", "/" })
    public @NotNull Iterable<Item> getProducts() {
        return itemService.getAllProducts();
    }
	@GetMapping(value = "/{id}")
	public @NotNull Item getProduct(@PathVariable long id) {
		return itemService.getProduct(id);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> createUser(@Valid @RequestBody Item item) {
		Item savedProduct = itemService.save(item);
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedProduct.getItemId())
			.toUri();
		return ResponseEntity.created(location).build();
	}
	@DeleteMapping("/delete/{id}")
	public void deleteUser(@PathVariable long id) {
		Item item = itemService.deleteById(id);
		if (item == null)
			throw new RuntimeException("id: "+id);
	}
}
