package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FlightDateTime {
    public static int getFlightTime(Ticket ticket) {
        return (int) Duration.between(getDepartureDate(ticket), getArrivalDate(ticket)).toMinutes();
    }
    public static LocalDateTime getDepartureDate(Ticket ticket) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        return LocalDateTime.parse(ticket.getDeparture_date() + " " + ticket.getDeparture_time(), formatter);
    }

    public static LocalDateTime getArrivalDate(Ticket ticket) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        return LocalDateTime.parse(ticket.getArrival_date() + " " + ticket.getArrival_time(), formatter);
    }
    public static List<Ticket> getListWithMinimalTime(List<Ticket> list) {
        List<Ticket> listWithMinimalTime = new ArrayList<>(list);
        for (int i = 0; i < listWithMinimalTime.size(); i++) {
            for (int j = listWithMinimalTime.size() - 1; j >= 0; j--) {
                if (listWithMinimalTime.get(j).getCarrier().equals(listWithMinimalTime.get(i).getCarrier())) {
                    if (getFlightTime(listWithMinimalTime.get(i)) < getFlightTime(listWithMinimalTime.get(j))) {
                        listWithMinimalTime.remove(j);
                    }
                }
            }
        }
        return listWithMinimalTime;
    }

    public static String timeToString(int minutes) {
        int hour = minutes / 60;
        int min = minutes % 60;
        return String.format("%02d:%02d", hour, min);
    }
}
