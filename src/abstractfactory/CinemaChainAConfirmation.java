package abstractfactory;

import factorymethod.BookingReceipt;

final class CinemaChainAConfirmation implements Confirmation {
    @Override
    public String createMessage(BookingReceipt booking, Seat seat) {
        if (!seat.getSeatId().startsWith("A-")) {
            throw new IllegalArgumentException("CinemaChainA confirmation needs an A seat.");
        }
        return "CinemaChainA confirms " + booking.customerName()
                + " at " + seat.getSeatId() + ". Show the A-QR code at the entrance.";
    }
}
