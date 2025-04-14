package com.korit.dreampath_back.repository;

import com.korit.dreampath_back.entity.Ticket;
import com.korit.dreampath_back.entity.TicketPurchaseHistory;
import com.korit.dreampath_back.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketRepository {

    @Autowired
    private TicketMapper ticketMapper;

    public List<TicketPurchaseHistory> findAllByUserId(int userId, int startIndex, int limitCount, String order) {
        return ticketMapper.getTicketPurchaseList(userId, startIndex, limitCount, order);
    }

    public int findAllTicketPurchaseCount(int userId) {
        return ticketMapper.findAllTicketPurchase(userId);
    }

    public Ticket findTicketById(int ticketId) {
        return ticketMapper.selectTicketByTicketId(ticketId);
    }

    public int saveTicket(int ticketId, int userId) {
        return ticketMapper.saveTicketPurchase(ticketId, userId);
    }
}

