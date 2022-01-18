package com.uplus.msa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//설정역할을 하는 클래스
@Configuration
public class AppConfig {

	//<bean id="" class=""> 
	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper;
	}
	
}