package com.example.rental.repository.token;


import com.example.rental.model.AuthenticationToken;
import com.example.rental.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<AuthenticationToken, String> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
