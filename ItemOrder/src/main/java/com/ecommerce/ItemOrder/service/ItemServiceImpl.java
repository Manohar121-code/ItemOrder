package com.ecommerce.ItemOrder.service;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.ItemOrder.Bean.Item;
import com.ecommerce.ItemOrder.dao.ItemDao;

/**
 * @author Manohar
 *
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService{
	
	@Autowired
	private ItemDao itemDao;
	
	@Override
	public @NotNull Iterable<Item> getAllProducts() {
		return itemDao.findAll();
	}
	
	@Override
    public Item getProduct(long id) {
        return itemDao
          .findById(id)
          .orElseThrow(() -> new RuntimeException("Item not found"));
    }
 
    @Override
    public Item save(Item item) {
        return itemDao.save(item);
    }

	@Override
	public Item deleteById(long id) {
		Item product = new Item();
		try {
			itemDao.deleteProductByid(id);
		}catch(Exception e){
			return null;
		}
		return product;
	}

}
