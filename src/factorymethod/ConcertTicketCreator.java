package factorymethod;

public final class ConcertTicketCreator extends TicketCreator {
    @Override
    protected Ticket createTicket() {
        return new ConcertTicket();
    }
}
