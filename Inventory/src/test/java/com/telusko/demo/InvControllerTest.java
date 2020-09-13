package com.telusko.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;



import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.telusko.demo.model.Inventory;



public class InvControllerTest extends AbstractTest{

	@Override
	@Before(value = "/")
	public void setUp() {
	      super.setUp();
	   }
	@Test
	   public void getInventories() throws Exception {
	      String uri = "/inventories";
	      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();	      
	      int status = mvcResult.getResponse().getStatus();
	      assertEquals(200, status);
	      String content = mvcResult.getResponse().getContentAsString();
	      Inventory[] Inventories = super.mapFromJson(content, Inventory[].class);
	      assertTrue(Inventories.length > 0);
	   }
}
