package abstractfactory;

import factorymethod.BookingReceipt;

final class CinemaChainAInvoice implements Invoice {
    @Override
    public String generate(BookingReceipt booking, Seat seat) {
        requireCompatibleSeat(seat);
        return "A-INVOICE | " + booking.ticketType() + " | Seat " + seat.getSeatId()
                + " | Total " + booking.finalPrice() + " KZT";
    }

    private void requireCompatibleSeat(Seat seat) {
        if (!seat.getSeatId().startsWith("A-")) {
            throw new IllegalArgumentException(
                    "CinemaChainA invoice cannot use a non-A seat ID.");
        }
    }
}
