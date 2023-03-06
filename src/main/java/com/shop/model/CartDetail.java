package com.shop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * CartDetail.
 * */
@Entity
@Table(name = "cart_detail")
@Setter
@Getter
public class CartDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  
  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;
  
  @ManyToOne
  @JoinColumn(name = "cart_id")
  private Cart cart;
  
  private Integer quantity;
  
  @Column(name = "date_added")
  private Date dateAdded;
}
