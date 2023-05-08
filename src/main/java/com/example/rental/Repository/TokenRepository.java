package com.example.rental.Repository;


import com.example.rental.Model.AuthenticationToken;
import com.example.rental.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends MongoRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
