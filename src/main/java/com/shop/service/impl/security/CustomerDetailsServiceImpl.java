package com.shop.service.impl.security;

import com.shop.model.Customer;
import com.shop.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class CustomerDetailsServiceImpl implements UserDetailsService {
  
  @Autowired
  CustomerRepository customerRepository;
  
  @Autowired
  private MessageSource messageSource;
  /**
   * Locates the user based on the username. In the actual implementation, the search
   * may possibly be case sensitive, or case insensitive depending on how the
   * implementation instance is configured. In this case, the <code>UserDetails</code>
   * object that comes back may have a username that is of a different case than what
   * was actually requested..
   *
   * @param username the username identifying the user whose data is required.
   * @return a fully populated user record (never <code>null</code>)
   * @throws UsernameNotFoundException if the user could not be found or the user has no
   *                                   GrantedAuthority
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
  
    Customer customer = customerRepository.findByUsername(username).orElseThrow(() -> new
            UsernameNotFoundException(messageSource.getMessage("EBL307", null, Locale.ENGLISH) + username));
    
    return CustomerDetailsImpl.build(customer);
  }
}
