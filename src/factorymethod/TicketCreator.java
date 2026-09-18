package factorymethod;

import java.math.BigDecimal;

/**
 * Creator in the Factory Method pattern. The business method works only with
 * the Ticket interface; subclasses decide which concrete Ticket is created.
 */
public abstract class TicketCreator {

    protected abstract Ticket createTicket();

    public BookingReceipt processBooking(BookingRequest request) {
        Ticket ticket = createTicket();
        ticket.validate(request);
        BigDecimal finalPrice = ticket.calculatePrice(request);

        return new BookingReceipt(
                ticket.getType(),
                request.customerName(),
                ticket.describe(request),
                finalPrice);
    }
}
