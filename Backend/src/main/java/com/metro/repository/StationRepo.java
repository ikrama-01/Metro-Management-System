package com.metro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.model.Station;

public interface StationRepo extends JpaRepository<Station, String>
{
	boolean existsByCode(String code);
}
