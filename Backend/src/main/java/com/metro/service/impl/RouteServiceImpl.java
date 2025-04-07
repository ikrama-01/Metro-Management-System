package com.metro.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.model.Routes;
import com.metro.repository.RoutesRepo;
import com.metro.service.RoutesService;

@Service
public class RouteServiceImpl implements RoutesService {
	
	@Autowired
	private RoutesRepo routesRepo;
	
	@Override
	public Routes addRoute(Routes route) {
		route.setId(UUID.randomUUID().toString());
		return routesRepo.save(route);
	}
	
	@Override
	public List<Routes> getAllRoutes(){
		return routesRepo.findAll();
	}
	
	@Override
    public Routes getRouteById(String id) {
        return routesRepo.findById(id).orElse(null);
    }

    @Override
    public Routes getRouteBySourceAndDestination(String sourceId, String destinationId) {
        return routesRepo.findBySourceIdAndDestinationId(sourceId, destinationId).orElse(null);
    }
    
    @Override
    public String updateRoute(String id, Routes updated) {
        Optional<Routes> opt = routesRepo.findById(id);
        if (opt.isPresent()) {
            Routes route = opt.get();
            route.setSource(updated.getSource());
            route.setDestination(updated.getDestination());
            route.setFare(updated.getFare());
            route.setDistance(updated.getDistance());
            routesRepo.save(route);
            return "Route updated successfully.";
        }
        return "Route not found.";
    }

    @Override
    public String deleteRoute(String id) {
        routesRepo.deleteById(id);
        return "Route deleted successfully.";
    }
	
}
