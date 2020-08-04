package com.win.weatherapi.repository;

import com.win.weatherapi.model.ZipCode;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<ZipCode, Long> {
    public ZipCode findByZip(String zipCode);
}