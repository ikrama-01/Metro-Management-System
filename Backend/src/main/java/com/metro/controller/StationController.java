package com.metro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.model.Station;
import com.metro.service.StationService;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin
public class StationController 
{
	@Autowired
	private StationService stationService;
	
	@PostMapping("/add")
    public ResponseEntity<Station> addStation(@RequestBody Station station) {
        Station saved = stationService.addStation(station);
        return ResponseEntity.ok(saved);
    }
	
	 @GetMapping("/all")
	    public ResponseEntity<List<Station>> getAllStations() {
	        List<Station> stations = stationService.getAllStations();
	        return ResponseEntity.ok(stations);
	 }
	 
	 @GetMapping("/{id}")
	    public ResponseEntity<Station> getStationById(@PathVariable String id) {
	        Station station = stationService.getStationById(id);
	        if (station != null) {
	            return ResponseEntity.ok(station);
	        }
	        return ResponseEntity.notFound().build();
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<String> updateStation(@PathVariable String id, @RequestBody Station updated) {
	        return ResponseEntity.ok(stationService.updateStation(id, updated));
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<String> deleteStation(@PathVariable String id) {
	        return ResponseEntity.ok(stationService.deleteStation(id));
	    }
}

