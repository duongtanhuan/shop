package com.shop.service.impl;

import com.shop.dto.AuthDto;
import com.shop.dto.JwtResponse;
import com.shop.dto.MessageResponse;
import com.shop.exception.IncorrectLoginInformationException;
import com.shop.exception.IncorrectRegisterInformationException;
import com.shop.exception.SystemErrorException;
import com.shop.model.Customer;
import com.shop.model.CustomerRole;
import com.shop.model.Role;
import com.shop.repository.CustomerRepository;
import com.shop.repository.CustomerRoleRepository;
import com.shop.repository.RoleRepository;
import com.shop.security.jwt.JwtUtils;
import com.shop.service.IAuthService;
import com.shop.service.impl.security.CustomerDetailsImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * AuthServiceImpl.
 * */
@Service
public class AuthServiceImpl implements IAuthService {
  @Autowired
  CustomerRepository customerRepository;
  
  @Autowired
  RoleRepository roleRepository;
  
  @Autowired
  CustomerRoleRepository customerRoleRepository;
  @Autowired
  private MessageSource messageSource;
  
  @Autowired
  AuthenticationManager authenticationManager;
  
  @Autowired
  JwtUtils jwtUtils;
  
  @Autowired
  PasswordEncoder encoder;
  
  @Override
  public JwtResponse logIn(AuthDto authDto) {
    try {
      Customer customer = customerRepository.findByUsername(authDto.getUsername()).orElseThrow(()
              -> new IncorrectLoginInformationException(messageSource.getMessage("EBL308B", null,
              Locale.ENGLISH)));
      if (new BCryptPasswordEncoder().matches(authDto.getPassword(), customer.getPassword())) {
        Authentication authentication = authenticationManager.authenticate(new
                UsernamePasswordAuthenticationToken(authDto.getUsername(), authDto.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        CustomerDetailsImpl customerDetails = (CustomerDetailsImpl) authentication.getPrincipal();
        List<String> roles = customerDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        
        return new JwtResponse(jwt,
                customerDetails.getId(),
                customerDetails.getUsername(),
                customerDetails.getEmail(),
                roles);
      } else {
        throw new IncorrectLoginInformationException(messageSource.getMessage("EBL308A",
                null, Locale.ENGLISH));
      }
    } catch (IncorrectLoginInformationException e) {
      throw new IncorrectLoginInformationException(messageSource.getMessage("EBL308A",
              null, Locale.ENGLISH));
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL309", null, Locale.ENGLISH));
    }
  }
  
  @Override
  @Transactional
  public MessageResponse signup(AuthDto authDto) {
    try {
      if (authDto.getUsername().isEmpty() || authDto.getEmail().isEmpty() || authDto.getPassword().isEmpty()) {
        return new MessageResponse(messageSource.getMessage("EBL317", null, Locale.ENGLISH));
      }
      
      if (customerRepository.existsByUsername(authDto.getUsername())) {
        return new MessageResponse(messageSource.getMessage("EBL310", null, Locale.ENGLISH));
      }
      
      if (customerRepository.existsByEmail(authDto.getEmail())) {
        return new MessageResponse(messageSource.getMessage("EBL311", null, Locale.ENGLISH));
      }
      
      Customer customer = new Customer(authDto.getUsername(), authDto.getEmail(),
              encoder.encode(authDto.getPassword()));
      Set<Role> roles = new HashSet<>();
      
      if (CollectionUtils.isEmpty(authDto.getRoles())) {
        Role userRole = roleRepository.findByName("user")
                .orElseThrow(() -> new IncorrectRegisterInformationException(messageSource
                        .getMessage("EBL312", null, Locale.ENGLISH)));
        
        roles.add(userRole);
      } else {
        authDto.getRoles().forEach(role -> {
          Role userRole = roleRepository.findByName("admin".equals(role) ? "admin" : "user")
                  .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          
          roles.add(userRole);
        });
      }
      
      customer.setCustomerRoles(new HashSet<>());
      customer = customerRepository.save(customer);
      List<CustomerRole> customerRoles = new ArrayList<>();
      for (var role : roles) {
        var userRole = new CustomerRole();
        userRole.setRoleId(role.getId());
        userRole.setCustomerId(customer.getId());
        
        customerRoles.add(userRole);
      }
      customerRoleRepository.saveAll(customerRoles);
      return new MessageResponse(messageSource.getMessage("EBL313", null, Locale.ENGLISH));
    } catch (Exception e) {
      throw new SystemErrorException(messageSource.getMessage("EBL314", null, Locale.ENGLISH));
    }
  }
}
