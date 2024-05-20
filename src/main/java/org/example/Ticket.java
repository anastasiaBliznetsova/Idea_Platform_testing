package org.example;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
    private String origin;
    private String origin_name;
    private String destination;
    private String destination_name;
    private String departure_date;
    private String departure_time;
    private String arrival_date;
    private String arrival_time;
    private String carrier;
    private int stops;
    private int price;

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) object;
        return stops == ticket.stops
                && price == ticket.price
                && Objects.equals(origin, ticket.origin)
                && Objects.equals(origin_name, ticket.origin_name)
                && Objects.equals(destination, ticket.destination)
                && Objects.equals(destination_name, ticket.destination_name)
                && Objects.equals(departure_date, ticket.departure_date)
                && Objects.equals(departure_time, ticket.departure_time)
                && Objects.equals(arrival_date, ticket.arrival_date)
                && Objects.equals(arrival_time, ticket.arrival_time)
                && Objects.equals(carrier, ticket.carrier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origin, origin_name, destination,
                destination_name, departure_date, departure_time,
                arrival_date, arrival_time, carrier, stops, price);
    }
}
