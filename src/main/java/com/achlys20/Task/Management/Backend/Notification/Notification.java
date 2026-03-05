package com.achlys20.Task.Management.Backend.Notification;


import com.achlys20.Task.Management.Backend.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Who receives the notification
    @ManyToOne
    @JoinColumn(name = "recipient_id", nullable = false)
    private User recipient;

    // The message content
    @Column(length = 500, nullable = false)
    private String message;

    // Has the user read the notification
    private boolean read = false;

    // When the notification was created
    private LocalDateTime timestamp = LocalDateTime.now();
}