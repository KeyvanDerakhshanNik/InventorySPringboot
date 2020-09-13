package com.telusko.demo.dao;

import org.springframework.data.repository.CrudRepository;
import com.telusko.demo.model.Inventory;


public interface InventoryRepo extends CrudRepository<Inventory,Integer>{
	
}
