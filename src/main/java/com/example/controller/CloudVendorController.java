package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.CloudVendor;
import com.example.exception.CloudVendorNotFoundException;
import com.example.service.CloudVendorService;

@RestController
@RequestMapping("/cloudvendor")
public class CloudVendorController
{
	@Autowired
	private CloudVendorService cloudVendorService;
	
	@PostMapping("/add")
	public ResponseEntity<CloudVendor> addCloudVendor(@RequestBody CloudVendor cloudVendor)
	{
		return new ResponseEntity<CloudVendor>(cloudVendorService.addCloudvendor(cloudVendor), HttpStatus.CREATED);

	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CloudVendor>> getAllCloudVendor()
	{	
		return new ResponseEntity<List<CloudVendor>>(cloudVendorService.allCloudVendor(), HttpStatus.OK);
	}
	
	@GetMapping("/{vendorId}")
	public ResponseEntity<CloudVendor> getCloudVendor(@PathVariable Integer vendorId) throws CloudVendorNotFoundException
	{
		return new ResponseEntity<CloudVendor>(cloudVendorService.getCloudVendor(vendorId), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{vendorId}")
	public ResponseEntity<String> deleteCloudVendor(@PathVariable Integer vendorId)
	{
		cloudVendorService.deleteCloudVendor(vendorId);
		return ResponseEntity.ok("cloud vendor deleted...");
	}
	
	
	@PutMapping("/update/{vendorId}")
	public ResponseEntity<CloudVendor> updateCloudVendor(@PathVariable Integer vendorId, @RequestBody CloudVendor cv) throws CloudVendorNotFoundException
	{
		CloudVendor updatedd=cloudVendorService.updateCloudvendor(vendorId, cv);
		return new ResponseEntity<CloudVendor>(updatedd, HttpStatus.OK);
		
	}
	
	@GetMapping("/find/{vendorName}")
	public ResponseEntity<CloudVendor> findByVendorName(@PathVariable String vendorName)
	{
		CloudVendor vendor= cloudVendorService.findByVendorName(vendorName);
		return new ResponseEntity<CloudVendor>(vendor, HttpStatus.OK);
	}
	
	@GetMapping("/cloudVendor-not-found")
	public void triggerException() 
	{
	    throw new CloudVendorNotFoundException("Cloud Vendor not found");
	}

}
