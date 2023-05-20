package com.example.rental.repository.offer;

import com.example.rental.exception.OfferException;
import com.example.rental.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class OfferRepositoryUtil {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Offer> findByDateBetween(Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            query.addCriteria(criteria);
            return this.mongoTemplate.find(query, Offer.class);
        } catch (OfferException ex) {
            throw new OfferException("Error in query!");
        }
    }

}
