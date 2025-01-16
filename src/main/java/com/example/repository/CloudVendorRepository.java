package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.CloudVendor;

@Repository
public interface CloudVendorRepository extends JpaRepository<CloudVendor, Integer>
{
	public CloudVendor findByVendorName(String vendorName);
}
