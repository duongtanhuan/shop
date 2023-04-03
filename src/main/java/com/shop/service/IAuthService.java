package com.shop.service;

import com.shop.dto.AuthDto;
import com.shop.dto.JwtResponse;
import com.shop.dto.MessageResponse;

public interface IAuthService {
  JwtResponse logIn(AuthDto authDto);
  MessageResponse signup(AuthDto authDto);
}
