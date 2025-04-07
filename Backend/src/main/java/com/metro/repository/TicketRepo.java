package com.metro.repository;

import com.metro.model.Ticket;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket, String> {
	List<Ticket> findByUserId(String userId);
}
