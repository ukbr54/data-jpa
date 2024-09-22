package com.example;

import com.example.transaction.Ticket;
import com.example.transaction.TicketRepository;
import com.example.transaction.TicketService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

@SpringBootTest
public class TransactionTest {


    private @Autowired TicketService ticketService;
    private @Autowired TicketRepository ticketRepository;

    @Test
    public void testBookingTicket(){
        try{
            Ticket ticket =
                    new Ticket("Bus Stop 1", "Bus Stop 2", LocalDateTime.of(2024, Month.OCTOBER, 20, 12, 0));
            ticketService.bookTicket(ticket);
        }catch (Exception e){
            System.out.println("------------> "+e.getMessage());
        }finally {
            Assertions.assertTrue((!ticketRepository.findAll().isEmpty()));
        }
    }
}
