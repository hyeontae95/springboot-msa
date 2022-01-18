package com.uplus.msa.util;

import org.springframework.beans.BeanUtils;

import com.uplus.msa.dto.CustomerDTO;
import com.uplus.msa.entity.CustomerEntity;

public class AppUtils {
	public static CustomerDTO customerDto(CustomerEntity customerEntity) {
		CustomerDTO customerDTO = new CustomerDTO();
		BeanUtils.copyProperties(customerEntity, customerDTO);
		return customerDTO;
	}
	
	public static CustomerEntity dtoToEntity(CustomerDTO customerDto) {
		CustomerEntity customerEntity = new CustomerEntity();
		BeanUtils.copyProperties(customerDto, customerEntity);
		return customerEntity;
	}
}