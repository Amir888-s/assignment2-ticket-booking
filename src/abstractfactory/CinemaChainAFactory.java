package abstractfactory;

public final class CinemaChainAFactory implements VenueFactory {
    @Override
    public Seat createSeat() {
        return new CinemaChainASeat("R05", 12);
    }

    @Override
    public Invoice createInvoice() {
        return new CinemaChainAInvoice();
    }

    @Override
    public Confirmation createConfirmation() {
        return new CinemaChainAConfirmation();
    }

    @Override
    public String getVenueName() {
        return "CinemaChainA";
    }
}
