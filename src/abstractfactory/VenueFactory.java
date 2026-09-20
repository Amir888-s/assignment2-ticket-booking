package abstractfactory;

/** Abstract Factory: creates one complete, compatible venue product family. */
public interface VenueFactory {
    Seat createSeat();

    Invoice createInvoice();

    Confirmation createConfirmation();

    String getVenueName();
}
