package abstractfactory;

import factorymethod.BookingReceipt;

final class CinemaChainBConfirmation implements Confirmation {
    @Override
    public String createMessage(BookingReceipt booking, Seat seat) {
        if (!seat.getSeatId().startsWith("B-")) {
            throw new IllegalArgumentException("CinemaChainB confirmation needs a B seat.");
        }
        return "[Chain B SMS] Booking accepted for " + booking.customerName()
                + ". Zone seat: " + seat.getSeatId() + ". Use your B-PIN at entry.";
    }
}
