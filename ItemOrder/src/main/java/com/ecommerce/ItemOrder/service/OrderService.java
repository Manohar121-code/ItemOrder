package com.ecommerce.ItemOrder.service;

import com.ecommerce.ItemOrder.Bean.OrderItem;

/**
 * @author Manohar
 *
 */
public interface OrderService {

	Iterable<OrderItem> getAllOrders();

	OrderItem create(OrderItem order);

	void update(OrderItem order);

}
