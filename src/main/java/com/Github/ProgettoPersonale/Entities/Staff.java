package com.Github.ProgettoPersonale.Entities;

import jakarta.persistence.*;
import lombok.*;

@Data
@Table
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nameSurname;

    @Column
    private String role;
}
