package cn.rivamed.springbootmybatiswithredis.controller;

import cn.rivamed.springbootmybatiswithredis.service.CityService;
import cn.rivamed.springbootmybatiswithredis.dao.domain.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @GetMapping("/api/city/{id}")
    public City findOneCity(@PathVariable("id") Long id){
        return cityService.findCityById(id);
    }

    @PostMapping("/api/city")
    public void createCity(@RequestBody City city){
        cityService.saveCity(city);
    }

    @PutMapping("/api/city")
    public void modifyCity(@RequestBody City city){
        cityService.updateCity(city);
    }

    @DeleteMapping("/api/city/{id}")
    public void modifyCity(@PathVariable("id") Long id){
        cityService.deleteCity(id);
    }
}
