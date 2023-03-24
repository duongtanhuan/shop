package com.shop.model;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Order.
 * */
@Entity
@Table(name = "orders")
@Setter
@Getter
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
  private List<OrderDetail> orderDetails;
  
  @ManyToOne
  @JoinColumn(name = "customer_id")
  private Customer customer;
  
  @Column(name = "total_price")
  private double totalPrice;
  
  @Column(name = "create_date")
  private Date createDate;
  
  private Boolean status;
}
