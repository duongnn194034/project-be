package com.example.rental.repository.motor;

import com.example.rental.model.Motor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.NearQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MotorRepositoryUtil {
    private static final double MAX_DIST = 10.0;
    @Autowired
    MongoTemplate mongoTemplate;

    public GeoResults<Motor> findByQuery(double lat, double lng, List<String> ids) {
        Query query = new Query();
        Criteria criteria = Criteria.where("id").nin(ids);
        query.addCriteria(criteria);
        NearQuery nearQuery = NearQuery.near(new Point(lng, lat), Metrics.KILOMETERS);
        nearQuery.maxDistance(MAX_DIST);
        nearQuery.query(query);
        return mongoTemplate.geoNear(nearQuery, Motor.class);
    }
}
