package com.example.weatherService.repository;

import com.example.weatherService.model.Weather;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface  WeatherRepository extends CrudRepository<Weather, Integer> {
    List<Weather> findAll();
}
