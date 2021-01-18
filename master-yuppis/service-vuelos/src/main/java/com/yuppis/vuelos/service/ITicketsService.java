package com.yuppis.vuelos.service;

import com.yuppis.vuelos.entity.Tickets;

import java.util.List;

public interface ITicketsService {
    public List<Tickets> listAllTicket();
    public Tickets getTicket(Long id);
    public Tickets createTicket(Tickets tickets);
    public void deleteTicket(Long id);
    boolean existsById(long itineraryID);
}
