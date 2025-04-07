package com.metro.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.model.Station;
import com.metro.repository.StationRepo;
import com.metro.service.StationService;

@Service
public class StationServiceImpl implements StationService {
	
	@Autowired
	private StationRepo stationRepo;
	
	@Override
	public Station addStation(Station station) {
		station.setId(UUID.randomUUID().toString());
		return stationRepo.save(station);
	}
	
	@Override
	public List<Station> getAllStations(){
		return stationRepo.findAll();
	}
	
	@Override
    public Station getStationById(String id) {
        return stationRepo.findById(id).orElse(null);
    }
	
	@Override
    public String updateStation(String id, Station updated) {
        Optional<Station> opt = stationRepo.findById(id);
        if (opt.isPresent()) {
            Station station = opt.get();
            station.setName(updated.getName());
            station.setCode(updated.getCode());
            stationRepo.save(station);
            return "Station updated successfully.";
        }
        return "Station not found.";
    }
	
	@Override
    public String deleteStation(String id) {
        stationRepo.deleteById(id);
        return "Station deleted successfully.";
    }
	
	
}
