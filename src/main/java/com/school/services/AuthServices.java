package com.school.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.school.data.vo.v1.security.AccountCredentialsVO;
import com.school.data.vo.v1.security.TokenVO;
import com.school.repository.UserRepository;
import com.school.security.jwt.JwtTokenProvider;

@Service
public class AuthServices {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository repository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsVO data) {
        try {
            var email = data.getEmail();

            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            var user = repository.findByEmail(email);

            var tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(email, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Username" + email + " not found!");

            }
            return ResponseEntity.ok(tokenResponse);

        } catch (Exception e) {

            throw new BadCredentialsException("Invalid email/password!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String email, String refreshToken) {
        var user = repository.findByEmail(email);

        var tokenResponse = new TokenVO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Username" + email + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
