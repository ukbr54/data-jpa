package com.example.transaction;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String origin;
    private String destination;
    @Column(name = "scheduled_at")
    private LocalDateTime scheduleAt;

    public Ticket(String origin, String destination, LocalDateTime scheduleAt) {
        this.origin = origin;
        this.destination = destination;
        this.scheduleAt = scheduleAt;
    }
}
