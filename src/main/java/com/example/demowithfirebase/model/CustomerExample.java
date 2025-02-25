package com.example.demowithfirebase.model;

import com.google.cloud.firestore.annotation.DocumentId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerExample {
    @DocumentId // Från firestore annotations, alternativt kan du sätta manuellt
    private String id;

    private String email;
    private String company;
    private Boolean admin;
}
