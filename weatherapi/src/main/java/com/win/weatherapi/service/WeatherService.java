package com.win.weatherapi.service;

import java.util.List;

import com.win.weatherapi.model.Response;
import com.win.weatherapi.model.ZipCode;
import com.win.weatherapi.repository.RequestRepository;
import com.win.weatherapi.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RequestRepository requestRepository;

    public List<ZipCode> getRecentSearches() {
        return weatherRepository.findAll();
    }

    @Value("${api_key}")
    private String apiKey;

    public Response getForecast(String zipCode) {

        ZipCode zip = new ZipCode(zipCode);

        String url = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipCode + "&units=imperial&appid="
                + apiKey;
        RestTemplate restTemplate = new RestTemplate();

        try {
            if (weatherRepository.findByZip(zipCode) == null) {
                weatherRepository.save(zip);
            }
            return restTemplate.getForObject(url, Response.class);
        } catch (HttpClientErrorException ex) {
            Response response = new Response();
            response.setName("error");
            return response;
        }
    }
}
