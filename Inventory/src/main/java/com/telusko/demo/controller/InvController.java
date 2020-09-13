package com.telusko.demo.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.telusko.demo.dao.InventoryRepo;
import com.telusko.demo.dao.OrderRepo;
import com.telusko.demo.model.Inventory;
import com.telusko.demo.model.OrderInventory;

@Controller
public class InvController {
	
	@Autowired
	InventoryRepo inventoryrepo;
	@Autowired
	OrderRepo orderrepo;
	
	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView model = new ModelAndView("/showInventory.jsp");		
		model.addObject("inventories", inventoryrepo.findAll());
		model.addObject("orders", orderrepo.findAll());
	    return  model;
	}
	
	@PostMapping(path="/inventories")
	@ResponseBody
	public Inventory addInventory(@RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam int quantity) {
		Inventory temp=new Inventory();
		temp.setName(name);
		temp.setDescription(description);
		temp.setPrice(price);
		temp.setQuantity(quantity);	
		inventoryrepo.save(temp);
		return temp;
	}
	
	@GetMapping("/inventories")
	@ResponseBody
	public Iterable<Inventory> getInventories() {
		return inventoryrepo.findAll();		
	}

	@GetMapping("/inventories/1")
	@ResponseBody
	public String getInventory(@RequestParam int inventoryID) {		
		return inventoryrepo.findById(inventoryID).toString();
	}
	
	@DeleteMapping("/inventories/1")
	@ResponseBody
	public String deleteInventory(@RequestParam int inventoryID) {
		inventoryrepo.deleteById(inventoryID);
		return "Delete Inventory Successfully";
	}
	
	@PutMapping("/inventories/1")
	@ResponseBody
	public String updateInventory(@RequestParam int inventoryID,@RequestParam String name, @RequestParam String description, @RequestParam int price, @RequestParam int quantity) {
		Inventory temp=new Inventory();
		temp.setInventoryID(inventoryID);
		temp.setName(name);
		temp.setDescription(description);
		temp.setPrice(price);
		temp.setQuantity(quantity);
		inventoryrepo.save(temp);
		return "Update Inventory Successfully";
	}
	
	@PostMapping(path="/orders")
	@ResponseBody
	public OrderInventory addOrder(@RequestParam String email, @RequestParam String date, @RequestParam int inventoryID, @RequestParam int quantity) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate localDate = LocalDate.parse(date, formatter);
		OrderInventory temp=new OrderInventory();
		temp.setCemail(email);
		temp.setDate(localDate);;
		temp.setInvID(inventoryID);
		temp.setQuantities(quantity);
		Inventory resource=inventoryrepo.findById(inventoryID).get();
		if(resource!=null ) {
			if(resource.getQuantity()>quantity) {
				resource.setQuantity(resource.getQuantity()-quantity);
				inventoryrepo.save(resource);
				temp.setStatus("Approved");
			}else {
				temp.setStatus("Demand");
			}			
		}else {
			temp.setStatus("Demand");
		}		
		orderrepo.save(temp);
		return temp;
	}
	
	@GetMapping("/orders")
	@ResponseBody
	public Iterable<OrderInventory> getOrders() {
		return orderrepo.findAll();		
	}

	@GetMapping("/orders/1")
	@ResponseBody
	public String getOrder(@RequestParam int orderID) {		
		return orderrepo.findById(orderID).toString();
	}

	@DeleteMapping("/orders/1")
	@ResponseBody
	public String deleteOrder(@RequestParam int orderID) {
		OrderInventory order= orderrepo.findById(orderID).get();
		Inventory inv =inventoryrepo.findById(order.getInvID()).get();
		inv.setQuantity(inv.getQuantity()+order.getQuantities());
		inventoryrepo.save(inv);
		order.setQuantities(0);
		order.setStatus("Deleted");
		orderrepo.save(order);
		return "Delete Order Successfully";
	}

	@PutMapping("/orders/1")
	@ResponseBody
	public String updateOrder(@RequestParam int orderID,@RequestParam String email, @RequestParam String date, @RequestParam int invID, @RequestParam int quantities) {
		OrderInventory order=orderrepo.findById(orderID).get();
		Inventory inv =inventoryrepo.findById(order.getInvID()).get();
		inv.setQuantity(inv.getQuantity()+order.getQuantities());
		inv.setQuantity(inv.getQuantity()-quantities);
		inventoryrepo.save(inv);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
		LocalDate localDate = LocalDate.parse(date, formatter);
		order.setCemail(email);
		order.setDate(localDate);
		order.setInvID(invID);
		order.setQuantities(quantities);
		order.setStatus("Updated");
		orderrepo.save(order);
		return "Update Order Successfully";
	}
	
}
