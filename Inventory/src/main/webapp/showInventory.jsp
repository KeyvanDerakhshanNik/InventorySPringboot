<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/custom.css"  rel="stylesheet">
</head>
<body>
	<h1>Welcome to Our Inventory </h1>
	<table style="width:100%">
 		<tr>
    	<th>Inventory ID</th>
    	<th>Inventory Name</th>
    	<th>Inventory Description</th>
    	<th>Price</th>
    	<th>Available Quantities</th>
  		</tr>
  		<c:forEach var="entry" items="${inventories}">
			<tr>
				<td>${entry.inventoryID}</td>
				<td>${entry.name}</td>
				<td>${entry.description}</td>
				<td>${entry.price}</td>
				<td>${entry.quantity}</td>
		    </tr>
		</c:forEach>
    </table>
	<br/>
	<h1>Welcome to Our Order </h1>
	<table style="width:100%">
 		<tr>
    	<th>Order ID</th>
    	<th>Email</th>
    	<th>Date</th>
    	<th>Status</th>
    	<th>Inventory ID</th>
    	<th>Quantities</th>
  		</tr>
  		<c:forEach var="entry" items="${orders}">
			<tr>
				<td>${entry.orderID}</td>
				<td>${entry.cemail}</td>
				<td>${entry.date}</td>
				<td>${entry.status}</td>
				<td>${entry.invID}</td>
				<td>${entry.quantities}</td>
		    </tr>
		</c:forEach>
    </table>
	<br/>
	<form  action="inventories" method="post">
    			<span>Inventory Name:</span>
      			<input type="text" name="name">
      			<span>Description:</span>
      			<input type="text" name="description">
      			<span>Price:</span>
      			<input type="text" name="price">
      			<span>Available Quantity:</span>
      			<input type="text" name="quantity"><br/>
      			<input type="submit" name="addInventory" value="Create Inventory Item" />
 	</form>
 	<br/>
 	<form action="inventories" method="get">
 		<input type="submit" name="getInventories" value="Get Inventory Items" />
 	</form>
 	<br/>
 	<form action="inventories/1" method="get">
 		<span>Inventory ID:</span>
    	<input type="text" name="inventoryID">
 		<input type="submit" name="getInventory" value="Get Inventory" />
 	</form>
 	<br/>  	  
  	<h2> Delete and Put is not supported in Browsers So we need Postman to check this features</h2>
 	<form action="inventories/1" method="delete">
 		<span>Inventory ID:</span>
    	<input type="text" name="inventoryID">
 		<input type="submit" name="deleteInventory" value="DELETE Inventory" />
 	</form>
 	<br/>
  
 	<form  action="inventories/1" method="put">
 		<span>Inventory ID</span>
 		<input type="text" name="inventoryID">
    	<span>Inventory Name:</span>
    	<input type="text" name="name">
    	<span>Description:</span>
    	<input type="text" name="description">
    	<span>Price:</span>
    	<input type="text" name="price">
    	<span>Available Quantity:</span>
    	<input type="text" name="quantity"><br/>
    	<input type="submit" name="updateInventory" value="Update Inventory Item" />
 	</form>
 
 	<h1>The Order Section</h1>
 	<form  action="orders" method="post">
    	<span>Email:</span>
    	<input type="text" name="email">
    	<span>Date:</span>
    	<input type="date" name="date">
    	<span>InventoryID:</span>
    		<select name="inventoryID" id="inv">
  				<c:forEach var="entry" items="${orders}">
					<option value="${entry.invID}">${entry.invID}</option>
				</c:forEach>
			</select>   
    	<span>Quantity:</span>
    	<input type="text" name="quantity"><br/>
    	<input type="submit" name="addInventory" value="Create Inventory Item" />
 	</form>
 	<br/>
 	<form action="orders" method="get">
 		<input type="submit" name="getOrders" value="Get Order Items" />
 	</form>
 	<br/>
 	<form action="orders/1" method="get">
 		<span>Order ID:</span>
    	<input type="text" name="orderID">
 		<input type="submit" name="getOrder" value="Get Order" />
 	</form>
 	<br/>
  	<h2> Delete and Put is not supported in Browsers So we need Postman to check this features</h2>
 	<form action="orders/1" method="delete">
 		<span>Order ID:</span>
    	<input type="text" name="orderID">
 	<input type="submit" name="deleteOrder" value="DELETE Order" />
 	</form>
 	<br/>
 
 	<form  action="orders/1" method="put">
 		<span>Order ID</span>
 		<input type="text" name="orderID">
    	<span>Email:</span>
    	<input type="text" name="email">
    	<span>Date:</span>
    	<input type="date" name="date">
    	<span>Inventory ID:</span>
    	<input type="text" name="invID">
    	<span>Quantity:</span>
    	<input type="text" name="quantities"><br/>
    	<input type="submit" name="updateOrder" value="Update Order Item" />
 	</form> 		
	
</body>
</html>