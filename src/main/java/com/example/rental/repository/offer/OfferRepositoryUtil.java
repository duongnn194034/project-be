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

    public List<Offer> findByVehicleAndDateBetween(String vehicleId, Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria dateCriteria = new Criteria();
            dateCriteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            criteria.andOperator(Criteria.where("vehicleId").is(vehicleId), dateCriteria);
            query.addCriteria(criteria);
            return this.mongoTemplate.find(query, Offer.class);
        } catch (Exception e) {
            throw new OfferException(e.getMessage());
        }
    }

    public List<Offer> findByVehiclesAndDateBetween(List<String> vehicleIds, Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria dateCriteria = new Criteria();
            dateCriteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            criteria.andOperator(Criteria.where("vehicleId").in(vehicleIds), dateCriteria);
            query.addCriteria(criteria);
            return this.mongoTemplate.find(query, Offer.class);
        } catch (Exception e) {
            throw new OfferException(e.getMessage());
        }
    }

    public List<String> findIdsByDateBetween(Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            query.addCriteria(criteria);
            List<Motor> motors = mongoTemplate.findDistinct(query, "vehicleId", Offer.class, Motor.class);
            return motors.stream().map(Vehicle::getId).collect(Collectors.toList());
        } catch (Exception e) {
            throw new OfferException(e.getMessage());
        }
    }

    public long getCountOfferByMotor(String motorId) {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.andOperator(Criteria.where("vehicleId").is(motorId),
                    Criteria.where("status").is("RETURNED"));
            query.addCriteria(criteria);
            long count = mongoTemplate.count(query, Offer.class);
            return count;
        } catch (Exception e) {
            throw new OfferException(e.getMessage());
        }
    }
}
