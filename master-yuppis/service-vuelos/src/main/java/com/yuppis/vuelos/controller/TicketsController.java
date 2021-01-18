package com.yuppis.vuelos.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yuppis.vuelos.entity.Tickets;
import com.yuppis.vuelos.service.ITicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tickets")
public class TicketsController {

    @Autowired
    private ITicketsService iticketsService;

    @GetMapping
    public ResponseEntity<List<Tickets>> listTickets (@RequestParam(name="itineraryID", required = false) Long itineraryID){
        List<Tickets> tiquets = new ArrayList<>();
        if (null == itineraryID) {
            tiquets = iticketsService.listAllTicket();
            if (tiquets.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.ok(tiquets);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Tickets> getById(@PathVariable("id") Long id) {
        Tickets tiquets = iticketsService.getTicket(id);
        if (null == tiquets) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tiquets);
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Tickets tiquets, UriComponentsBuilder ucBuilder, BindingResult result) {
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        iticketsService.createTicket(tiquets);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tiquet/{id}").buildAndExpand(tiquets.getItineraryID()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String>  error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
