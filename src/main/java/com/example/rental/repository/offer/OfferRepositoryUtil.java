package com.example.rental.repository.offer;

import com.example.rental.exception.OfferException;
import com.example.rental.model.motor.Motor;
import com.example.rental.model.offer.Offer;
import com.example.rental.model.motor.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
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

    public List<Offer> findByVehiclesAndDateBetween(List<String> vehicleIds) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria statusCriteria = new Criteria();
            Criteria dateCriteria = new Criteria();
            statusCriteria.andOperator(Criteria.where("status").is("COMPLETED"),
                    Criteria.where("endTime").gte(new Date(System.currentTimeMillis() - 7 * 24 * 3600 * 1000)));
            criteria.andOperator(Criteria.where("vehicleId").in(vehicleIds),
                    statusCriteria,
                    dateCriteria);
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
            List<String> motorIds = mongoTemplate.findDistinct(query, "vehicleId", Offer.class, String.class);
            return motorIds;
        } catch (Exception e) {
            throw new OfferException(e.getMessage());
        }
    }

    public List<Offer> findOffersByDateBetween(Date startDate, Date endDate) throws OfferException {
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            criteria.orOperator(Criteria.where("startTime").gte(startDate).lte(endDate),
                    Criteria.where("endTime").gte(startDate).lte(endDate));
            query.addCriteria(criteria);
            return mongoTemplate.find(query, Offer.class);
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
