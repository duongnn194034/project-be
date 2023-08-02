package com.example.rental.service.token;

import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.model.user.AuthenticationToken;
import com.example.rental.model.user.User;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    User getUser(String token);

    void authenticate(String token) throws AuthenticationFailException;

    void authenticateAdmin(String token) throws AuthenticationFailException;
}
