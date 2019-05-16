package com.ecommerce.ItemOrder.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ItemOrder.Bean.OrderItem;
import com.ecommerce.ItemOrder.service.OrderService;

/**
 * @author Manohar
 *
 */
@RestController
@RequestMapping("/user/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@GetMapping(value = { "", "/" })
    public @NotNull Iterable<OrderItem> getOrders() {
        return orderService.getAllOrders();
    }
	
	@PostMapping("/createOrder")
	public ResponseEntity<Object> createUser(@Valid @RequestBody List<OrderItem> orders) {
		List<String> listOfOrderIds = new ArrayList<>();
		for (OrderItem order : orders) {	
			try {
				orderService.create(order);
			} catch(RuntimeException e) {
				listOfOrderIds.add(e.getMessage());
				return new ResponseEntity<Object>(listOfOrderIds, HttpStatus.NO_CONTENT);
			}
		}
		return new ResponseEntity<Object>(listOfOrderIds, HttpStatus.OK);
	}
}
