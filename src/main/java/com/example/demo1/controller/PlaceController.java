package com.example.demo1.controller;

import com.example.demo1.entity.Place;
import com.example.demo1.result.ResultVO;
import com.example.demo1.service.IPlaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/place")
@Api(tags = {"位置表API"})
public class PlaceController {

    @Autowired
    private IPlaceService iPlaceService;

    @ApiOperation(value = "创建位置",notes = "位置表API")
    @RequestMapping(value = "/addPlace",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Place> addPlace(@RequestBody Place place){
        return (ResultVO<Place>) iPlaceService.addPlace(place);
    }

    @ApiOperation(value = "批量创建位置",notes = "位置表API")
    @RequestMapping(value = "/batchCreatePlaces",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> batchCreatePlaces(){
        return iPlaceService.batchCreatePlaces();
    }

    @ApiOperation(value = "更新位置信息",notes = "位置表API")
    @RequestMapping(value = "/updatePlace",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Place> updatePlace(@RequestBody Place place){
        return (ResultVO<Place>) iPlaceService.updatePlace(place);
    }

    @ApiOperation(value = "按照半径查询位置",notes = "位置表API")
    @RequestMapping(value = "/findAreaPlaces",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> findAreaPlaces(@RequestParam float radius){
        return iPlaceService.findAreaPlaces(radius);
    }

    @ApiOperation(value = "查找位置",notes = "位置表API")
    @RequestMapping(value = "/findPlace",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<Place> findPlace(@RequestBody Place place){
        return (ResultVO<Place>) iPlaceService.findPlace(place);
    }

    @ApiOperation(value = "删除位置",notes = "位置表API")
    @RequestMapping(value = "/removePlace",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<?> removePlace(@RequestBody Place place){
        return iPlaceService.removePlace(place);
    }

    @ApiOperation(value = "按名称查找位置",notes = "位置表API")
    @RequestMapping(value = "/findPlaces",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO<List<Place>> findPlaces(@RequestBody Place place){
        return (ResultVO<List<Place>>) iPlaceService.findPlaces(place);
    }

}
