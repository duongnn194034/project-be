package com.example.rental.Service;

import com.example.rental.Exception.AuthenticationFailException;
import com.example.rental.Model.AuthenticationToken;
import com.example.rental.Model.User;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    User getUser(String token);

    void authenticate(String token) throws AuthenticationFailException;
}
