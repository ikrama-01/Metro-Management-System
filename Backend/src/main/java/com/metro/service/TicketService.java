package com.metro.service;

import java.util.List;


import com.metro.model.Ticket;

public interface TicketService {
    Ticket bookTicket(Ticket ticket);
    List<Ticket> getTicketsByUser(String userId);
    Ticket getTicketById(String id);
    List<Ticket> getAllTickets();

}
