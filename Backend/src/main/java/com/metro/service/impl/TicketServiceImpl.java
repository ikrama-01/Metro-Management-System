package com.metro.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metro.model.Ticket;
import com.metro.model.User;
import com.metro.repository.TicketRepo;
import com.metro.repository.UserRepository;
import com.metro.service.TicketService;

@Service
public class TicketServiceImpl implements TicketService 
{
	@Autowired
	private TicketRepo ticketRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Ticket bookTicket(Ticket ticket) {
		Optional<User> userOpt = userRepo.findById(ticket.getUser().getId());
		if(userOpt.isPresent()) {
			User user = userOpt.get();
			double fare = ticket.getFare();
			if(user.getWalletBalance() >= fare) {
				user.setWalletBalance(user.getWalletBalance() - fare);
				ticket.setId(UUID.randomUUID().toString());
				ticket.setCreatedAt(LocalDateTime.now());
				userRepo.save(user);
				return ticketRepo.save(ticket);
			} else {
				throw new RuntimeException("Insufficient Balance");
			}
		} else {
			throw new RuntimeException("User Not Found");
		}
	}
	
	@Override
	public List<Ticket> getTicketsByUser(String userId){
		return ticketRepo.findByUserId(userId);
	}
	
	@Override
	public Ticket getTicketById(String id) {
		return ticketRepo.findById(id).orElse(null);	
	}
	
	@Override
	public List<Ticket> getAllTickets() {
	    return ticketRepo.findAll();
	}

}
