package com.Github.ProgettoPersonale.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {

    private String name;
    private String surname;
    private String cellNumber;
    private String email;
}