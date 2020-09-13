package com.telusko.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.telusko.demo.model.OrderInventory;

public interface OrderRepo extends CrudRepository<OrderInventory,Integer>{

}
