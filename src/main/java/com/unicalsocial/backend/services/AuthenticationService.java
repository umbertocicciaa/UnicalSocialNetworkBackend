package com.unicalsocial.backend.services;


import com.unicalsocial.backend.dtos.AuthenticationRequest;
import com.unicalsocial.backend.dtos.AuthenticationResponse;
import com.unicalsocial.backend.dtos.RegisterRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

}
