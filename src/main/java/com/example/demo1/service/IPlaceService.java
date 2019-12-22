package com.example.demo1.service;

import com.example.demo1.entity.Place;
import com.example.demo1.result.ResultVO;

import java.math.BigDecimal;
import java.util.List;

public interface IPlaceService {

    public ResultVO<?> addPlace(Place place);

    public ResultVO<?> updatePlace(Place place);

    public ResultVO<?> findPlace(Place place);

    public ResultVO<?> findPlaces(Place place);

    public ResultVO<?> removePlace(Place place);

    public ResultVO<?> batchCreatePlaces();

    public ResultVO<?> findAreaPlaces(float radius);
}
