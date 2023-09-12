package com.Github.ProgettoPersonale.DTO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderDTO {

    private String clientName;
    private String clientSurname;
    private String clientEmail;
    private String clientNumber;
    private List<Long> productIds;
}