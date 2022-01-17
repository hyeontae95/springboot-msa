package com.uplus.msa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uplus.msa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
	
	Optional<CustomerEntity> findByName(String name);
}
