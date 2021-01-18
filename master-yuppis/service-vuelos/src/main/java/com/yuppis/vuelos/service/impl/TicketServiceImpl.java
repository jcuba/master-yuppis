package com.yuppis.vuelos.service.impl;

import com.yuppis.vuelos.entity.Tickets;
import com.yuppis.vuelos.repository.TicketsRepository;
import com.yuppis.vuelos.service.ITicketsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements ITicketsService {

    private final TicketsRepository ticketsRepository;

    @Override
    public List<Tickets> listAllTicket() {
        return ticketsRepository.findAll();
    }

    @Override
    public Tickets getTicket(Long id) {
        return ticketsRepository.findById(id).orElse(null);
    }

    @Override
    public Tickets createTicket(Tickets tickets) {
        return ticketsRepository.save(tickets);
    }

    @Override
    public void deleteTicket(Long id) {
        ticketsRepository.deleteById(id);
    }

    @Override
    public boolean existsById(long itineraryID) {
        return ticketsRepository.existsById(itineraryID);
    }
}
