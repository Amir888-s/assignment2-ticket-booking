package abstractfactory;

import factorymethod.BookingReceipt;

/**
 * Abstract Factory client. It receives a factory by composition and knows only
 * VenueFactory, Seat, Invoice, and Confirmation interfaces.
 */
public final class VenueBookingClient {
    private final VenueFactory venueFactory;

    public VenueBookingClient(VenueFactory venueFactory) {
        this.venueFactory = venueFactory;
    }

    public VenuePackage prepareVenuePackage(BookingReceipt booking) {
        Seat seat = venueFactory.createSeat();
        Invoice invoice = venueFactory.createInvoice();
        Confirmation confirmation = venueFactory.createConfirmation();

        return new VenuePackage(
                venueFactory.getVenueName(),
                seat.getDescription(),
                invoice.generate(booking, seat),
                confirmation.createMessage(booking, seat));
    }
}
