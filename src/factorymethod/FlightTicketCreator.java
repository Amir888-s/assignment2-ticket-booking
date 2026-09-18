package factorymethod;

public final class FlightTicketCreator extends TicketCreator {
    @Override
    protected Ticket createTicket() {
        return new FlightTicket();
    }
}
