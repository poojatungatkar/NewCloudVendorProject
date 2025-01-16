package com.example.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.entity.CloudVendor;
import com.example.exception.CloudVendorNotFoundException;
import com.example.repository.CloudVendorRepository;

@ExtendWith(MockitoExtension.class)
public class CloudVendorServiceTest 
{
	@InjectMocks
	private CloudVendorServiceImpl cloudVendorService;
	
	@Mock
	private CloudVendorRepository cloudVendorRepository;
	
	private CloudVendor cloudVendor;
	private CloudVendor cloudVendor2;
	
	@BeforeEach
	public void setUp()
	{
		cloudVendor= new CloudVendor();
		cloudVendor.setVendorId(1);
		cloudVendor.setVendorName("aaa");
		cloudVendor.setVendorAddress("bbb");
		cloudVendor.setVendorPhoneNumber(2345678l);
		
		cloudVendor2= new CloudVendor();
		cloudVendor2.setVendorId(2);
		cloudVendor2.setVendorName("ppp");
		cloudVendor2.setVendorAddress("qqq");
		cloudVendor2.setVendorPhoneNumber(235678l);
	}
	
	@Test
	public void save()
	{	
		when(cloudVendorRepository.save(any(CloudVendor.class))).thenReturn(cloudVendor);
		CloudVendor cloudVendor2= cloudVendorService.addCloudvendor(cloudVendor);
		assertNotNull(cloudVendor2);
		assertThat(cloudVendor2.getVendorName()).isEqualTo("aaa");
	}
	
	@Test
	public void getAll()
	{	
		List<CloudVendor> list= new ArrayList<>();
		list.add(cloudVendor);
		list.add(cloudVendor2);
		
		when(cloudVendorRepository.findAll()).thenReturn(list);
		List<CloudVendor> list2=cloudVendorService.allCloudVendor();
		
		assertNotNull(list2);
		assertEquals(2,list2.size());
	}
	
	@Test
	public void getCloudvendor() throws CloudVendorNotFoundException
	{	
		when(cloudVendorRepository.findById(anyInt())).thenReturn(Optional.of(cloudVendor));
		CloudVendor old=cloudVendorService.getCloudVendor(1);
		assertNotNull(old);
		assertThat(cloudVendor.getVendorId()).isEqualTo(1);
	}
	
	@Test
	void getCloudVendorByIdForException()
	{
		int vendorId=1;
		
		when(cloudVendorRepository.findById(vendorId)).thenReturn(Optional.empty());
		CloudVendorNotFoundException ex=assertThrows(CloudVendorNotFoundException.class,()->{
			cloudVendorService.getCloudVendor(vendorId);
			});
        assertEquals("cloud vendor not found : "+ vendorId,ex.getMessage());

	}
	
	
	@Test
	public void update() throws CloudVendorNotFoundException
	{
		when(cloudVendorRepository.findById(anyInt())).thenReturn(Optional.of(cloudVendor));
		when(cloudVendorRepository.save(any(CloudVendor.class))).thenReturn(cloudVendor);
		cloudVendor.setVendorName("pooja");
		cloudVendor.setVendorAddress("loni");
		CloudVendor updated =cloudVendorService.updateCloudvendor(1, cloudVendor);
		assertNotNull(updated);
		assertEquals("pooja", updated.getVendorName());
		assertEquals("loni", updated.getVendorAddress());
	}
	
	@Test
	public void delete()
	{
		doNothing().when(cloudVendorRepository).deleteById(1);
		cloudVendorService.deleteCloudVendor(1);
		verify(cloudVendorRepository,times(1)).deleteById(1);
	}
	
	@Test
	public void findByVendorName()
	{
		when(cloudVendorRepository.findByVendorName("aaa")).thenReturn(cloudVendor);
		CloudVendor vendor=cloudVendorService.findByVendorName("aaa");
		assertEquals("aaa", vendor.getVendorName());
	}
}
