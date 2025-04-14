package com.korit.dreampath_back.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketPurchaseHistory {

    private LocalDate createdAt;

    private String ticketName;
    private int price;

}
