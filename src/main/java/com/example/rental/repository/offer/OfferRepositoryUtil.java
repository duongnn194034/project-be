package com.example.rental.repository.offer;

import com.example.rental.exception.OfferException;
import com.example.rental.model.Motor;
import com.example.rental.model.Offer;
import com.example.rental.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OfferRepositoryUtil {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Offer> findByVehicleAndDateBetween(Vehicle vehicle, Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria dateCriteria = new Criteria();
            dateCriteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            criteria.andOperator(Criteria.where("vehicle").is(vehicle), dateCriteria);
            query.addCriteria(criteria);
            return this.mongoTemplate.find(query, Offer.class);
        } catch (OfferException ex) {
            ex.printStackTrace();
            throw new OfferException("Error in query!");
        }
    }

    public List<String> findIdsByDateBetween(Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            query.addCriteria(criteria);
            List<Motor> motors = mongoTemplate.findDistinct(query, "vehicle", Offer.class, Motor.class);
            return motors.stream().map(motor -> motor.getId()).collect(Collectors.toList());
        } catch (OfferException e) {
            e.printStackTrace();
            throw new OfferException("Error in query!");
        }
    }

}
