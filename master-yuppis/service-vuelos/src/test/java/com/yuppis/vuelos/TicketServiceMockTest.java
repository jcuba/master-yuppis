package com.yuppis.vuelos;

import com.yuppis.vuelos.entity.Tickets;
import com.yuppis.vuelos.repository.TicketsRepository;
import com.yuppis.vuelos.service.ITicketsService;
import com.yuppis.vuelos.service.impl.TicketServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class TicketServiceMockTest {

    @Mock
    private TicketsRepository ticketsRepository;

    private ITicketsService iTicketsService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        iTicketsService = new TicketServiceImpl(ticketsRepository);
        Tickets ticket01 = Tickets.builder()
                .itineraryID(2L)
                .fechaSalida("20/01/2021")
                .fechaLlegada("24/01/2021")
                .horaSalida("13:00:00")
                .horaLlegada("20:59:00")
                .ciudadOrigen("Puebla")
                .ciudadDestino("San luis potosi")
                .nomPasajero("Ruber Echeverria Flores")
                .edadPasajero("45")
                .bodegaEquipaje(true)
                .precio("5600.00").build();

        Mockito.when(ticketsRepository.findById(2L))
                .thenReturn(Optional.of(ticket01));
    }

    @Test
    public void whenValidGetID_ThenReturnTickets(){
        Tickets found = iTicketsService.getTicket(2L);
        Assertions.assertThat(found.getCiudadOrigen());
    }
}
