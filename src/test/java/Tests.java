import org.example.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Tests {
    private List<Ticket> list;
    private Ticket ticketOne;
    private Ticket ticketTwo;
    private Ticket ticketThree;

    @BeforeEach
    public void list() {
        list = new ArrayList<>();
        ticketOne = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив", "12.05.18",
                "16:20", "12.05.18", "22:10", "S7", 3, 12400);
        ticketTwo = new Ticket("VVO", "Владивосток", "TLV", "Тель-Авив", "12.05.18",
                "17:20", "12.05.18", "23:50", "S7", 1, 13100);
        ticketThree = new Ticket("VVO", "Владивосток", "TLV", "Уфа", "12.05.18",
                "12:10", "12.05.18", "18:10", "SU", 0, 15300);
        list.add(ticketOne);
        list.add(ticketTwo);
        list.add(ticketThree);
    }

    @Test
    public void parserTest() throws IOException {
        assertEquals(Parser.parserDataSpecificNode("src/test/resources/tickets.json", "tickets"),
                list);
    }

    @Test
    public void parserTestException() {
        IOException thrown = assertThrows(IOException.class,
                () -> Parser.parserDataSpecificNode("TestException", "tickets"));
        assertEquals("TestException (No such file or directory)", thrown.getMessage());
    }

    @Test
    public void priceTest() {
        assertEquals(Price.getAveragePrice(list), (12400 + 13100 + 15300) / 3);
        assertEquals(Price.getMedianPrice(list), 13100);
    }
    @Test
    public void targetTest() {
        List<Ticket> listResult = new ArrayList<>(list);
        listResult.remove(ticketThree);
        assertEquals(Target.getTargetTickets(list, "Владивосток", "Тель-Авив"), listResult);
    }
    @Test
    public void getDepartureDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        LocalDateTime timeDeparture = LocalDateTime.parse("12.05.18" + " " + "16:20", formatter);
        assertEquals(FlightDateTime.getDepartureDate(ticketOne), timeDeparture);
    }
    @Test
    public void getArrivalDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy H:mm");
        LocalDateTime timeArrival = LocalDateTime.parse("12.05.18" + " " + "22:10", formatter);
        assertEquals(FlightDateTime.getArrivalDate(ticketOne), timeArrival);
    }
    @Test
    public void getFlightTimeTest() {
        assertEquals(FlightDateTime.getFlightTime(ticketOne), 350);
    }
    @Test
    public void timeToStringTest() {
        assertEquals(FlightDateTime.timeToString(350), "05:50");
    }
    @Test
    public void getListWithMinimalTimeTest() {
        List<Ticket> listResult = new ArrayList<>(list);
        listResult.remove(ticketTwo);
        assertEquals(FlightDateTime.getListWithMinimalTime(list), listResult);
    }
}
