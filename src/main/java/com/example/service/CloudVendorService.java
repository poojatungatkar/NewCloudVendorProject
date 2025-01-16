package com.example.service;

import java.util.List;

import com.example.entity.CloudVendor;
import com.example.exception.CloudVendorNotFoundException;

public interface CloudVendorService 
{
	//create
	CloudVendor addCloudvendor(CloudVendor vendor);
	
	//get all
	public List<CloudVendor> allCloudVendor();
	
	// get single
	public CloudVendor getCloudVendor(int vendorId) throws CloudVendorNotFoundException;
	
	// delete
	public void deleteCloudVendor( int vendorId);
	
	//update
	public CloudVendor updateCloudvendor(int vendorId, CloudVendor cloudVendor) throws CloudVendorNotFoundException;
	
	//get vendor by name
	public CloudVendor findByVendorName(String vendorName);
	
	

}
