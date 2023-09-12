package com.Github.ProgettoPersonale.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = true)
    private String cellNumber;
}