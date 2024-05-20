package org.example;

import java.util.List;

public class Target {
    public static List<Ticket> getTargetTickets(List<Ticket> list, String originName, String destinationName) {
        return list.stream()
                .filter(ticket -> ticket.getOrigin_name().equals(originName)
                        && ticket.getDestination_name().equals(destinationName))
                .toList();
    }
}
