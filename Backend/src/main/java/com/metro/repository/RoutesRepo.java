package com.metro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.metro.model.Routes;

public interface RoutesRepo extends JpaRepository<Routes, String> 
{
	Optional<Routes> findBySourceIdAndDestinationId(String sourceId, String destinationId);
}
