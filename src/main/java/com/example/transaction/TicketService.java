package com.example.transaction;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository){
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public void bookTicket(Ticket ticket) throws InterruptedException {
        Thread.sleep(40000);
        ticketRepository.save(ticket);
        //throw new RuntimeException("Payment Failed");
        Thread.sleep(40000);
    }
}