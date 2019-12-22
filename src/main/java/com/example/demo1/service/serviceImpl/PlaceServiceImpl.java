package com.example.demo1.service.serviceImpl;

import com.example.demo1.entity.Location;
import com.example.demo1.entity.Place;
import com.example.demo1.result.ResultVO;
import com.example.demo1.service.IPlaceService;
import com.example.demo1.util.CommonUtil;
import com.example.demo1.util.ResultVOUtil;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;

@Component
@Slf4j
public class PlaceServiceImpl implements IPlaceService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public ResultVO<?> addPlace(Place place) {
        place.setId(CommonUtil.generateId());
        Place model = mongoTemplate.insert(place);
        return ResultVOUtil.returnSuccess(model);
    }

    @Override
    public ResultVO<?> updatePlace(Place place) {
        Query query = new Query(Criteria.where("id").is(place.getId()));
        Update update = new Update();
        update.set("name", place.getName());
        update.set("loc", place.getLoc());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Place.class);
        long matchedCount = updateResult.getMatchedCount();
        if (matchedCount > 0) {
            return ResultVOUtil.returnSuccess();
        } else {
            return ResultVOUtil.returnFail();
        }
    }

    @Override
    public ResultVO<?> findPlace(Place place) {
        Query query = new Query(Criteria.where("id").is(place.getId()));
        Place model = mongoTemplate.findOne(query, Place.class);
        return ResultVOUtil.returnSuccess(model);
    }

    @Override
    public ResultVO<?> findPlaces(Place place) {
        Query query = new Query(Criteria.where("name").is(place.getName()));
        List<Place> places = mongoTemplate.find(query, Place.class);
        return ResultVOUtil.returnSuccess(places);
    }

    @Override
    public ResultVO<?> removePlace(Place place) {
        Query query = new Query(Criteria.where("id").is(place.getId()));
        DeleteResult deleteResult = mongoTemplate.remove(query, Place.class);
        return ResultVOUtil.returnSuccess(deleteResult.getDeletedCount());
    }

    @Override
    public ResultVO<?> batchCreatePlaces() {
        Random random1 = new Random();
        DoubleStream lonStream1 = random1.doubles(121.47004, 121.49004);
        double[] lons1 = lonStream1.limit(100).toArray();

        Random random2 = new Random();
        DoubleStream latStream1 = random2.doubles(31.23136, 31.24136);
        double[] lats1 = latStream1.limit(100).toArray();
        for (int i = 0; i < 100; i++) {
            Place place = new Place();
            place.setId(CommonUtil.generateId());
            place.setName("" + i);
            Location location = new Location();
            location.setType("point");
            List<BigDecimal> coordinates = new ArrayList<>();
            coordinates.add(BigDecimal.valueOf(lons1[i]));
            coordinates.add(BigDecimal.valueOf(lats1[i]));
            location.setCoordinates(coordinates);
            place.setLoc(location);
            mongoTemplate.insert(place);
        }
        return ResultVOUtil.returnSuccess();
    }

    @Override
    public ResultVO<?> findAreaPlaces(float radius) {
        return null;
    }
}
