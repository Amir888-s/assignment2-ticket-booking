package app;

import abstractfactory.CinemaChainAFactory;
import abstractfactory.CinemaChainBFactory;
import abstractfactory.VenueBookingClient;
import abstractfactory.VenueFactory;
import abstractfactory.VenuePackage;
import factorymethod.BookingReceipt;
import factorymethod.BookingRequest;
import factorymethod.ConcertTicketCreator;
import factorymethod.FlightTicketCreator;
import factorymethod.MovieTicketCreator;
import factorymethod.TicketCreator;

import java.math.BigDecimal;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        System.out.println("PART A - FACTORY METHOD");

        BookingReceipt concert = runBooking(
                new ConcertTicketCreator(),
                BookingRequest.concert(
                        "Amir", 18, "Astana Live", new BigDecimal("12000"), "VIP"));

        runBooking(
                new MovieTicketCreator(),
                BookingRequest.movie(
                        "Aruzhan", 19, "Design Patterns", new BigDecimal("2500"),
                        "PREMIUM", 16));

        runBooking(
                new FlightTicketCreator(),
                BookingRequest.flight(
                        "Daniyar", "KC622", new BigDecimal("45000"), "ECONOMY",
                        24, "Astana", "Almaty", "N1234567"));

        System.out.println("\nPART B - ABSTRACT FACTORY");
        VenueFactory selectedVenue = selectVenue(args); // family selected once
        VenueBookingClient venueClient = new VenueBookingClient(selectedVenue);
        VenuePackage venuePackage = venueClient.prepareVenuePackage(concert);

        System.out.println("Venue: " + venuePackage.venueName());
        System.out.println("Seat: " + venuePackage.seatDetails());
        System.out.println(venuePackage.invoice());
        System.out.println(venuePackage.confirmation());
    }

    private static BookingReceipt runBooking(
            TicketCreator creator, BookingRequest request) {
        BookingReceipt receipt = creator.processBooking(request);
        System.out.println(receipt.ticketType() + ": "
                + receipt.bookingDescription() + " -> "
                + receipt.finalPrice() + " KZT");
        return receipt;
    }

    /** This is the only place where the venue family is selected. */
    private static VenueFactory selectVenue(String[] args) {
        String choice = args.length == 0 ? "A" : args[0].trim().toUpperCase();
        return switch (choice) {
            case "A" -> new CinemaChainAFactory();
            case "B" -> new CinemaChainBFactory();
            default -> throw new IllegalArgumentException("Use venue A or B.");
        };
    }
}
