package com.shop.service.impl.security;

import com.shop.model.Customer;
import com.shop.model.CustomerRole;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * CustomerDetailsImpl.
 * */
@NoArgsConstructor
@Service
public class CustomerDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;
  private Integer id;
  
  private String username;
  
  private String email;
  
  private String password;
  
  private Collection<? extends GrantedAuthority> authorities;
  
  /**
   * CustomerDetailsImpl
   * */
  public CustomerDetailsImpl(Integer id, String username, String password, String email,
                             Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.authorities = authorities;
  }
  
  /**
   * CustomerDetailsImpl
   * */
  public static CustomerDetailsImpl build(Customer customer) {
    List<GrantedAuthority> authorities = customer.getCustomerRoles().stream()
            .map(CustomerRole::getRole)
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    
    return new CustomerDetailsImpl(
            customer.getId(),
            customer.getUsername(),
            customer.getPassword(),
            customer.getEmail(),
            authorities);
  }
  

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }
  
  
  public Integer getId() {
    return id;
  }
  
  public String getEmail() {
    return email;
  }

  @Override
  public String getPassword() {
    return password;
  }
  
  @Override
  public String getUsername() {
    return username;
  }
  
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }
  
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }
  
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
  
  @Override
  public boolean isEnabled() {
    return true;
  }
}
