package com.ecommerce.ItemOrder.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ItemOrder.Bean.OrderItem;

/**
 * @author Manohar
 *
 */
@Repository
@Transactional
public interface OrderItemDAO extends JpaRepository<OrderItem, Long>{
	
	@Modifying
    @Transactional
    @Query("SELECT orderId, dateCreated, orderQuantity, userEmail FROM OrderItem")
	Iterable<OrderItem> findAllOrders();

}
