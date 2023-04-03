package com.shop.repository;

import com.shop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * RoleRepository.
 * */
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(String name);
}
