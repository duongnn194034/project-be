package com.example.ecommerce.Service.impl;

import com.example.ecommerce.Exception.AuthenticationFailException;
import com.example.ecommerce.Model.AuthenticationToken;
import com.example.ecommerce.Model.User;
import com.example.ecommerce.Repository.TokenRepository;
import com.example.ecommerce.Service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.ecommerce.Exception.AuthenticationFailException.AUTH_TOEKN_NOT_PRESENT;
import static com.example.ecommerce.Exception.AuthenticationFailException.AUTH_TOEKN_NOT_VALID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    TokenRepository repository;

    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        repository.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(User user) {
        return repository.findTokenByUser(user);
    }

    @Override
    public User getUser(String token) {
        AuthenticationToken authenticationToken = repository.findTokenByToken(token);
        if (authenticationToken != null) {
            if (authenticationToken.getUser() != null) {
                return authenticationToken.getUser();
            }
        }
        return null;
    }

    @Override
    public void authenticate(String token) throws AuthenticationFailException {
        if (token == null) {
            throw new AuthenticationFailException(AUTH_TOEKN_NOT_PRESENT);
        }
        if (getUser(token) == null) {
            throw new AuthenticationFailException(AUTH_TOEKN_NOT_VALID);
        }
    }
}
