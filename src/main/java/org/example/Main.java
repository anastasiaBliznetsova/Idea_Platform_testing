package org.example;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        List<Ticket> listTicket = null;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите путь до файла:");

        String pathName = scanner.next();
        scanner.close();

        try {
            listTicket = Parser.parserDataSpecificNode(
                    pathName,
                    "tickets");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        listTicket = Target.getTargetTickets(listTicket, "Владивосток", "Тель-Авив");

        List<Ticket> listWithMinimalTime = FlightDateTime.getListWithMinimalTime(listTicket);
        for (Ticket ticket: listWithMinimalTime) {
            System.out.println("Минимальное время полета для "
                    + ticket.getCarrier() + ": "
                    + FlightDateTime.timeToString(FlightDateTime.getFlightTime(ticket)));
        }

        System.out.println("Разница между средней ценой и медианой: "
                + Math.abs(Price.getAveragePrice(listTicket) - (Price.getMedianPrice(listTicket))));
    }
}
