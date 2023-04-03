package com.shop.controller;

import com.shop.dto.AuthDto;
import com.shop.dto.JwtResponse;
import com.shop.dto.MessageResponse;
import com.shop.service.IAuthService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthController.
 * */
@CrossOrigin(origins = "*", maxAge = 4200)
@RestController
@RequestMapping("/auth")
public class AuthController {
  
  @Autowired
  private IAuthService authService;
  
  @PostMapping("/login")
  public ResponseEntity<JwtResponse> authenticateCustomer(@Valid @RequestBody AuthDto authDto) {
    return new ResponseEntity<>(authService.logIn(authDto), HttpStatus.OK);
  }
  
  @PostMapping("/signup")
  public ResponseEntity<MessageResponse> registerCustomer(@Valid @RequestBody AuthDto authDto) {
    return new ResponseEntity<>(authService.signup(authDto), HttpStatus.CREATED);
  }
  
}

