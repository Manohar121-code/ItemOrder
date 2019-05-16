package com.ecommerce.ItemOrder.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ItemOrder.Bean.Item;
import com.ecommerce.ItemOrder.Bean.OrderItem;
import com.ecommerce.ItemOrder.dao.ItemDao;
import com.ecommerce.ItemOrder.dao.OrderItemDAO;

/**
 * @author Manohar
 *
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderItemDAO orderItemDao;

	@Autowired
	private ItemDao itemDao;

	@Override
	public Iterable<OrderItem> getAllOrders() {
		return this.orderItemDao.findAllOrders();
	}

	@Override
	public OrderItem create(OrderItem item) {
		List<Integer> count = itemDao.getOrderDetails(item.getItem().getItemId(), item.getOrderQuantity());
		if (count.get(0) == 1) {
			item.setDateCreated(LocalDate.now());
			OrderItem orderResponse = this.orderItemDao.save(item);
			itemDao.updateQuantityOfItem(item.getItem().getItemId(), item.getOrderQuantity());
			return orderResponse;
		}
		throw new RuntimeException("Item or quantity is invalid");
	}

	@Override
	public void update(OrderItem orderItem) {
		this.orderItemDao.save(orderItem);
	}

}
