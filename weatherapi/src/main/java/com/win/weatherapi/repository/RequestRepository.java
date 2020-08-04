package com.win.weatherapi.repository;

import com.win.weatherapi.model.Request;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
}