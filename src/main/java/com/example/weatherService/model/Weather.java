package com.example.weatherService.model;



import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "weather_history")
public class Weather {
    @Id
    @Column(name = "weather_date")
    private LocalDate weather_date;
    @Column(name = "weather_value")
    private String weather_value;

    public Weather(LocalDate weather_date, String weather_value) {
        this.weather_date = weather_date;
        this.weather_value = weather_value;
    }

    public Weather() {
    }

    public LocalDate getWeather_date() {
        return weather_date;
    }

    public String getWeather_value() {
        return weather_value;
    }

    public void setWeather_value(String weather_value) {
        this.weather_value = weather_value;
    }

    public void setWeather_date(LocalDate weather_date) {
        this.weather_date = weather_date;
    }
}
