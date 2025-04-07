package com.metro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metro.model.Ticket;
import com.metro.service.TicketService;

@RestController
@RequestMapping("/api/tickets") 
@CrossOrigin
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/book")
	public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket){
		Ticket booked = ticketService.bookTicket(ticket);
		return ResponseEntity.ok(booked);
	}
	
	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Ticket>> getUserTickets(@PathVariable String userId){
		List<Ticket> tickets = ticketService.getTicketsByUser(userId);
		return ResponseEntity.ok(tickets);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ticket> getTicketsById(@PathVariable String id){
		Ticket ticket = ticketService.getTicketById(id);
		if(ticket != null) {
			return ResponseEntity.ok(ticket);
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Ticket>> getAllTickets() {
	    return ResponseEntity.ok(ticketService.getAllTickets());
	}

	
}
