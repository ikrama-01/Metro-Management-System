package com.metro.service;

import java.util.List;

import com.metro.model.Routes;

public interface RoutesService 
{
	Routes addRoute(Routes route);
	List<Routes> getAllRoutes();
	Routes getRouteById(String id);
	Routes getRouteBySourceAndDestination(String sourceId, String destinationId);
    String updateRoute(String id, Routes route);
    String deleteRoute(String id);
}
