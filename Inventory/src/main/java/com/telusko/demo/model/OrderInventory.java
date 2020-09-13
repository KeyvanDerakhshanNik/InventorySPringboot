package com.telusko.demo.model;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OrderInventory {
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )
	private int orderID;
	private String cemail;
	private LocalDate date;
	private String status;
	//to add a link to inventory and have quantitiy
	private int invID;
	private int quantities;
	
	public int getQuantities() {
		return quantities;
	}
	
	public void setQuantities(int quantities) {
		this.quantities = quantities;
	}
	
	public int getOrderID() {
		return orderID;
	}
	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	
	public String getCemail() {
		return cemail;
	}
	
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getInvID() {
		return invID;
	}
	
	public void setInvID(int invID) {
		this.invID = invID;
	}
	
	@Override
	public String toString() {
		return "OrderInventory [Orderid= " + orderID 
				+", OrderInventory Email= " + cemail + ", Date= " + date 
				+",Status= "+ status + ", InventoryID "+invID+ ", Quantities"+ quantities + "]";
	}
	
}
