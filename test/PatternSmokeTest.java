package test;

import abstractfactory.CinemaChainAFactory;
import abstractfactory.CinemaChainBFactory;
import abstractfactory.VenueBookingClient;
import abstractfactory.VenuePackage;
import factorymethod.BookingReceipt;
import factorymethod.BookingRequest;
import factorymethod.ConcertTicketCreator;
import factorymethod.FlightTicketCreator;
import factorymethod.MovieTicketCreator;

import java.math.BigDecimal;

/** Small no-framework checks that can run with plain Java. */
public final class PatternSmokeTest {
    private PatternSmokeTest() {
    }

    public static void main(String[] args) {
        checkRejected(() -> new ConcertTicketCreator().processBooking(
                BookingRequest.concert(
                        "Young customer", 14, "Live Show",
                        new BigDecimal("10000"), "VIP")));

        checkRejected(() -> new MovieTicketCreator().processBooking(
                BookingRequest.movie(
                        "Teen customer", 15, "18 Plus Film",
                        new BigDecimal("2500"), "REGULAR", 18)));

        checkRejected(() -> new FlightTicketCreator().processBooking(
                BookingRequest.flight(
                        "Traveler", "KC100", new BigDecimal("40000"),
                        "ECONOMY", 20, "Astana", "Astana", "N123456")));

        BookingReceipt validBooking = new ConcertTicketCreator().processBooking(
                BookingRequest.concert(
                        "Amir", 18, "Astana Live",
                        new BigDecimal("12000"), "VIP"));

        VenuePackage familyA = new VenueBookingClient(new CinemaChainAFactory())
                .prepareVenuePackage(validBooking);
        VenuePackage familyB = new VenueBookingClient(new CinemaChainBFactory())
                .prepareVenuePackage(validBooking);

        check(familyA.seatDetails().contains("A-")
                && familyA.invoice().contains("A-")
                && familyA.confirmation().contains("A-"),
                "Chain A family is inconsistent.");
        check(familyB.seatDetails().contains("B-")
                && familyB.invoice().contains("B-")
                && familyB.confirmation().contains("B-"),
                "Chain B family is inconsistent.");

        System.out.println("All smoke tests passed.");
    }

    private static void checkRejected(Runnable invalidBooking) {
        boolean rejected = false;
        try {
            invalidBooking.run();
        } catch (IllegalArgumentException expected) {
            rejected = true;
        }
        check(rejected, "Invalid booking was not rejected.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
