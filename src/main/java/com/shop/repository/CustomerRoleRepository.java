package com.shop.repository;

import com.shop.model.CustomerRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CustomerRoleRepository.
 * */
public interface CustomerRoleRepository extends JpaRepository<CustomerRole, Integer> {
}
