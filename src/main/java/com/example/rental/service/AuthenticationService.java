package com.example.rental.service;

import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.model.AuthenticationToken;
import com.example.rental.model.User;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    User getUser(String token);

    void authenticate(String token) throws AuthenticationFailException;
}
