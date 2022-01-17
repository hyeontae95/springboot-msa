package com.uplus.msa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uplus.msa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

}
