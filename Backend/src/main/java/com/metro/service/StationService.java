package com.metro.service;

import java.util.List;

import com.metro.model.Station;

public interface StationService 
{
	Station addStation(Station station);
	List<Station> getAllStations();
	Station getStationById(String id);
    String updateStation(String id, Station station);
    String deleteStation(String id);
}
