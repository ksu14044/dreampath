package com.korit.dreampath_back.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    private int ticketId;
    private String ticketName;
    private int price;
    private int entryCount;

    private LocalDateTime createdAt;
}
