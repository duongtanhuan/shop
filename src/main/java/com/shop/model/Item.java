package com.shop.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Item.
 * */
@Entity
@Table(name = "item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  private List<OrderDetail> orderDetails;
  
  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
  private List<CartDetail> cartDetails;
  
  private String name;
  
  private Double price;
  
  public Item(String name, Double price) {
    this.name = name;
    this.price = price;
  }
}
