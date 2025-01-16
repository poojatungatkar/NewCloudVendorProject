package com.example.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.entity.CloudVendor;
import com.example.service.CloudVendorServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest
public class VendorControllerTest
{
	@MockBean
	private CloudVendorServiceImpl VendorService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	CloudVendor cloudVendor; 
	CloudVendor cloudVendor2;
	
	@BeforeEach
	void init()
	{
		cloudVendor= new CloudVendor();
		cloudVendor.setVendorId(1);
		cloudVendor.setVendorName("aaa");
		cloudVendor.setVendorAddress("bbb");
		cloudVendor.setVendorPhoneNumber(2345678678l);
		
		cloudVendor2= new CloudVendor();
		cloudVendor2.setVendorId(2);
		cloudVendor2.setVendorName("aaa");
		cloudVendor2.setVendorAddress("bbb");
		cloudVendor2.setVendorPhoneNumber(2345678678l);
	}
	
	@Test
	void createVendorTest() throws  Exception
	{
		when(VendorService.addCloudvendor(any(CloudVendor.class))).thenReturn(cloudVendor);
		this.mockMvc.perform(post("/cloudvendor/add")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(cloudVendor)))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.vendorName",is(cloudVendor.getVendorName())))
				.andExpect(jsonPath("$.vendorAddress", is(cloudVendor.getVendorAddress())))
				.andExpect(jsonPath("$.vendorPhoneNumber",is(cloudVendor.getVendorPhoneNumber())));
	}
	
	@Test
	void getAllVendorTest() throws Exception
	{
		List<CloudVendor>vendors=new ArrayList<>();
		vendors.add(cloudVendor);
		vendors.add(cloudVendor2);
		
		when(VendorService.allCloudVendor()).thenReturn(vendors);
		this.mockMvc.perform(get("/cloudvendor/all"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.size()",is(vendors.size())));
	}
	
	@Test
	void getCloudVendorTest() throws Exception
	{
		when(VendorService.getCloudVendor(anyInt())).thenReturn(cloudVendor);
		this.mockMvc.perform(get("/cloudvendor/{vendorId}",1))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendorName",is(cloudVendor.getVendorName())));
	}
	
	@Test
	void deleteCloudVendorTest() throws Exception
	{	
		doNothing().when(VendorService).deleteCloudVendor(anyInt());
		this.mockMvc.perform(delete("/cloudvendor/delete/{vendorId}",1))
		.andExpect(status().isOk());
	}
	
	@Test
	void updateVendorTest() throws Exception
	{
		cloudVendor2= new CloudVendor();
		cloudVendor2.setVendorId(2);
		cloudVendor2.setVendorName("aaa");
		cloudVendor2.setVendorAddress("bbb");
		cloudVendor2.setVendorPhoneNumber(2345678678l);
		
		when(VendorService.updateCloudvendor(anyInt(),any(CloudVendor.class))).thenReturn(cloudVendor2);
		this.mockMvc.perform(put("/cloudvendor/update/{vendorId}",2)
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(cloudVendor2)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendorName",is(cloudVendor.getVendorName())));
		
	
	}
	
	@Test
	void findByVendorNameTest() throws Exception
	{
		when(VendorService.findByVendorName(anyString())).thenReturn(cloudVendor);
		this.mockMvc.perform(get("/cloudvendor/find/{vendorName}","aaa"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.vendorName",is(cloudVendor.getVendorName())));
		
	}
	
	@Test
	public void globalExceptionHandlerTest() throws Exception
	{
		mockMvc.perform(get("/cloudvendor/cloudVendor-not-found"))
		.andExpect(status().isNotFound())
		.andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
        .andExpect(jsonPath("$.detail").value("Cloud Vendor not found"));
		
	}
}









