package com.uplus.msa.service;

import java.util.List;

import com.uplus.msa.dto.CustomerDTO;

public interface CustomerService {
	//전체조회
	public List<CustomerDTO> getAllCustomers() throws Exception;
	
	//Customer 하나 조회
	public CustomerDTO getCustomerById(Long id) throws Exception;
	
	Long createCustomer(CustomerDTO custmerDTO) throws Exception;
	
	
}
