package com.metro.controller;

import com.metro.model.Routes;
import com.metro.service.RoutesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/routes")
@CrossOrigin
public class RoutesController {

    @Autowired
    private RoutesService routeService;

    @PostMapping("/add")
    public ResponseEntity<Routes> addRoute(@RequestBody Routes route) {
        Routes saved = routeService.addRoute(route);
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Routes>> getAllRoutes() {
        List<Routes> routes = routeService.getAllRoutes();
        return ResponseEntity.ok(routes);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Routes> getRouteById(@PathVariable String id) {
        Routes route = routeService.getRouteById(id);
        if (route != null) {
            return ResponseEntity.ok(route);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/getBySourceAndDestination")
    public ResponseEntity<Routes> getRouteBySourceAndDestination(@RequestParam String sourceId, @RequestParam String destinationId) {
        Routes route = routeService.getRouteBySourceAndDestination(sourceId, destinationId);
        if (route != null) {
            return ResponseEntity.ok(route);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable String id, @RequestBody Routes updated) {
        return ResponseEntity.ok(routeService.updateRoute(id, updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRoute(@PathVariable String id) {
        return ResponseEntity.ok(routeService.deleteRoute(id));
    }
}
