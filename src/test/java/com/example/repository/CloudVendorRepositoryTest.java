package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.entity.CloudVendor;
import com.example.exception.CloudVendorNotFoundException;
import com.example.repository.CloudVendorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;


public class CloudVendorRepositoryTest
{
    @Mock
    private CloudVendorRepository repo;
    
    private CloudVendor cloudVendor;
    private CloudVendor cloudVendor1;
    
    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        cloudVendor=new CloudVendor();
		cloudVendor.setVendorId(1);
		cloudVendor.setVendorName("aaa");
		cloudVendor.setVendorAddress("sss");
		cloudVendor.setVendorPhoneNumber(23456789l);
		
		cloudVendor1=new CloudVendor();
		cloudVendor1.setVendorId(2);
		cloudVendor1.setVendorName("bbb");
		cloudVendor1.setVendorAddress("jjj");
		cloudVendor1.setVendorPhoneNumber(23454569l);
    }
    
    @Test
    public void saveTest()
    {
    	
    	when(repo.save(cloudVendor)).thenReturn(cloudVendor);
    	CloudVendor vendor=repo.save(cloudVendor);
    	assertNotNull(vendor);
    	assertEquals("aaa", vendor.getVendorName());
    	verify(repo,times(1)).save(cloudVendor);
    	
    }
    
    @Test
    public void getCloudVendor()
    {
    	when(repo.findById(1)).thenReturn(Optional.of(cloudVendor));
    	Optional<CloudVendor> vendorId =repo.findById(1);
    	assertTrue(vendorId.isPresent());
    	
    	assertEquals(1, vendorId.get().getVendorId());;
    }
    
    @Test
    public void deleteVendor()
    {
    	doNothing().when(repo).deleteById(1);
    	repo.deleteById(1);
    	verify(repo,times(1)).deleteById(1);
    }
    
    @Test
    public void getAllVendor()
    {
    	List<CloudVendor>list=new ArrayList<>();
    	list.add(cloudVendor);
    	list.add(cloudVendor1);
    	
    	when(repo.findAll()).thenReturn(list);
    	List<CloudVendor>list2=repo.findAll();
    	assertNotNull(list2);
    	verify(repo,times(1)).findAll();
    	
    }
    
    @Test
    public void update()
    {
    	when(repo.save(cloudVendor)).thenReturn(cloudVendor);
    	cloudVendor.setVendorName("pooja");
    	cloudVendor.setVendorAddress("loni kalbhor");
    	CloudVendor updated =repo.save(cloudVendor);
    	
    	assertEquals("pooja", updated.getVendorName());
    	assertEquals("loni kalbhor", updated.getVendorAddress());
    	
    	verify(repo,times(1)).save(cloudVendor);
    	
    }
    
    @Test
    public void findByVendorName()
    {
    	when(repo.findByVendorName("aaa")).thenReturn(cloudVendor);
    	CloudVendor cv=repo.findByVendorName("aaa");
    	assertNotNull(cv);
    	assertEquals("aaa",cv.getVendorName());
    }
 
}
