package com.ecommerce.ItemOrder.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecommerce.ItemOrder.Bean.Item;

/**
 * @author Manohar
 *
 */
@Repository
@Transactional
public interface ItemDao extends JpaRepository<Item, Long>{
	
	@Modifying
    @Transactional
    @Query("delete from Item p where p.itemId = ?1")
    void deleteProductByid(long id);

	@Modifying
    @Transactional
    @Query("select count(*) from Item p where p.itemId = ?1 and p.itemQuantity >= ?2")
	List<Integer> getOrderDetails(Long itemId, Integer quantity);

	@Modifying
    @Transactional
    @Query("update Item p set p.itemQuantity=p.itemQuantity-?2 where p.itemId = ?1")
	void updateQuantityOfItem(Long productId, Integer quantity);
}
