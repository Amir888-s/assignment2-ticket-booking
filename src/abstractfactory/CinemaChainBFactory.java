package abstractfactory;

public final class CinemaChainBFactory implements VenueFactory {
    @Override
    public Seat createSeat() {
        return new CinemaChainBSeat("Z2", 45);
    }

    @Override
    public Invoice createInvoice() {
        return new CinemaChainBInvoice();
    }

    @Override
    public Confirmation createConfirmation() {
        return new CinemaChainBConfirmation();
    }

    @Override
    public String getVenueName() {
        return "CinemaChainB";
    }
}
