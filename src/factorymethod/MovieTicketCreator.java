package factorymethod;

public final class MovieTicketCreator extends TicketCreator {
    @Override
    protected Ticket createTicket() {
        return new MovieTicket();
    }
}
