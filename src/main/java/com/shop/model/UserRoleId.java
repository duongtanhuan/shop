package com.shop.model;

import java.io.Serializable;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * UserRoleId.
 * */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserRoleId implements Serializable {
  
  @Column(name = "customer_id")
  private Integer customerId;
  
  @Column(name = "role_id")
  private Integer roleId;
  
  
}
