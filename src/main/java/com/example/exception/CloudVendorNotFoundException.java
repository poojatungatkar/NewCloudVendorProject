package com.example.exception;

public class CloudVendorNotFoundException extends RuntimeException
{
	private static final long serialVersionUID = 1L;

	public CloudVendorNotFoundException(String message)
	{
		super(message);
	}
	
}
