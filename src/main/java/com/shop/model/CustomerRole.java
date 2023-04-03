package com.shop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * CustomerRole.
 * */
@Setter
@Getter
@Entity
@Table(name = "user_role")
@IdClass(UserRoleId.class)
public class CustomerRole {
  
  @Id
  @Column(name = "customer_id")

  private Integer customerId;
  
  @Id
  @Column(name = "role_id")
  private Integer roleId;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @MapsId("customerId")
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @ManyToOne(fetch = FetchType.EAGER)
  @MapsId("roleId")
  @JoinColumn(name = "role_id")
  private Role role;
  
}
