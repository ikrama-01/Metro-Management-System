package com.metro.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tickets")
public class Ticket {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "id")
    private Station source;

    @ManyToOne
    @JoinColumn(name = "destination_id", referencedColumnName = "id")
    private Station destination;

    private Double fare;

    private LocalDateTime createdAt;
}
