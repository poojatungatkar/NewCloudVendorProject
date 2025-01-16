package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.CloudVendor;
import com.example.exception.CloudVendorNotFoundException;
import com.example.repository.CloudVendorRepository;

@Service
public class CloudVendorServiceImpl implements CloudVendorService 
{

	@Autowired
	private CloudVendorRepository cloudVendorRepository;
	
	@Override
	public CloudVendor addCloudvendor(CloudVendor vendor) 
	{
		return cloudVendorRepository.save(vendor);
	}

	@Override
	public List<CloudVendor> allCloudVendor() 
	{
		return cloudVendorRepository.findAll();
	}

	@Override
	public CloudVendor getCloudVendor(int vendorId) throws CloudVendorNotFoundException 
	{
		CloudVendor vid= cloudVendorRepository.findById(vendorId)
				.orElseThrow(()->new CloudVendorNotFoundException("cloud vendor not found : "+ vendorId));
		return vid;
	}

	@Override
	public void deleteCloudVendor(int vendorId) 
	{
		cloudVendorRepository.deleteById(vendorId);
	}

	@Override
	public CloudVendor updateCloudvendor(int vendorId, CloudVendor cloudeVendor) throws CloudVendorNotFoundException
	{
		CloudVendor cv=cloudVendorRepository.findById(vendorId).get();
		cv.setVendorName(cloudeVendor.getVendorName());
		cv.setVendorAddress(cloudeVendor.getVendorAddress());
		cv.setVendorPhoneNumber(cloudeVendor.getVendorPhoneNumber());
		
		return 	cloudVendorRepository.save(cv);
	}

	@Override
	public CloudVendor findByVendorName(String vendorName)
	{
		return cloudVendorRepository.findByVendorName(vendorName);
	}

}
