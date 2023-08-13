package com.example.rental.service.token;

import com.example.rental.enums.Role;
import com.example.rental.exception.AuthenticationFailException;
import com.example.rental.model.user.AuthenticationToken;
import com.example.rental.model.user.User;
import com.example.rental.repository.token.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.rental.exception.AuthenticationFailException.AUTH_TOEKN_NOT_PRESENT;
import static com.example.rental.exception.AuthenticationFailException.AUTH_TOEKN_NOT_VALID;

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
        if (authenticationToken != null && authenticationToken.getUser() != null) {
                return authenticationToken.getUser();

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

    @Override
    public void authenticateAdmin(String token) throws AuthenticationFailException {
        if (token == null) {
            throw new AuthenticationFailException(AUTH_TOEKN_NOT_PRESENT);
        }
        if (getUser(token) == null) {
            throw new AuthenticationFailException(AUTH_TOEKN_NOT_VALID);
        } else if (getUser(token).getRole() != Role.ADMIN) {
            throw new AuthenticationFailException("Forbidden");
        }
    }

    @Override
    public void deleteToken(User user) throws AuthenticationFailException {
        repository.deleteByUser(user);
    }
}
