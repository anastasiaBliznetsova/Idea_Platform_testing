package org.example;

import java.util.Comparator;
import java.util.List;

public class Price {
    public static int getMedianPrice(List<Ticket> listTicket) {
        int price;
        listTicket = listTicket.stream().sorted(Comparator.comparingInt(Ticket::getPrice)).toList();
        int index = listTicket.size() / 2;
        if (listTicket.size() % 2 == 0) {
            price = (listTicket.get(index - 1).getPrice() + listTicket.get(index).getPrice()) / 2;
        } else {
            price = listTicket.get(index).getPrice();
        }
        return price;
    }
    public static int getAveragePrice(List<Ticket> listTicket) {
        int sum = 0;
        for (Ticket ticket: listTicket) {
            sum += ticket.getPrice();
        }
        return sum / listTicket.size();
    }
}
