package com.shop.dto;

import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * CartDetailRequest.
 * */
@Getter
@Setter
public class AuthDto {
  @NotNull
  @Size(min = 3, max = 20)
  private String username;
  
  @NotNull
  @Size(max = 20)
  @Email
  private String email;
  
  private Set<String> roles;
  
  @NotNull
  @Size(min = 6, max = 10)
  private String password;
}
