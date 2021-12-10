package com.example.weatherService.controller;

import com.example.weatherService.model.Weather;
import com.example.weatherService.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;


@RestController
public class WeatherController {

    private final String yandex = "https://yandex.ru/";

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/weather")
    public String weather() {
        return getWeather();
    }

    public String getWeather() {
        String valueWeather = null;
        LocalDate localDate = LocalDate.now();
        // проходимся по всем сохранёным датам
        for (Weather w : weatherRepository.findAll()) {
            if (localDate.equals(w.getWeather_date())) {
                valueWeather = w.getWeather_value();
            }
        }
        // если не нашли такую дату в БД то создаём новый Weather погоду берем с сайта
        if (valueWeather == null) {
            Weather weather = new Weather(localDate, getWeatherYandex());
            weatherRepository.save(weather);
            valueWeather = weather.getWeather_value();
        }


        return valueWeather;
    }


    public String getWeatherYandex() {
        String weatherYandex = "";
        try {
            URL url = new URL(yandex);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(url.openStream()));
            String temp = "weather__temp'>";
            String inputLine;
            while ((inputLine = input.readLine()) != null) {
                if (inputLine.contains(temp)) {
                    String result = inputLine.substring(inputLine.indexOf(temp) + 1);
                    weatherYandex = result.substring(14, 17);
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return weatherYandex;
    }


}
