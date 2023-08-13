package com.example.rental.repository.user;

import com.example.rental.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryUtil {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getUserToVerify() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("verified").is(false),
                Criteria.where("idCard").ne(null));
        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }
}
