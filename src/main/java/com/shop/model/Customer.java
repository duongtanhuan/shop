package com.shop.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Customer.
 * */
@Entity
@Table(name = "customer")
@Setter
@Getter
@NoArgsConstructor
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
  private Cart cart;
  
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
  private List<Order> orders;
  
  private String username;
  
  private String password;
  
  private String email;
  
  @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,fetch = FetchType.EAGER, orphanRemoval = true)
  private Set<CustomerRole> customerRoles;
  
  /**
   * Customer constructor.
   * */
  public Customer(String username, String email, String encode) {
    this.username = username;
    this.email = email;
    this.password = encode;
  }
  
}
