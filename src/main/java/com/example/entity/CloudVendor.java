package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CloudVendor 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int vendorId;
	private String vendorName;
	private String vendorAddress;
	private Long vendorPhoneNumber;
	
	
}
