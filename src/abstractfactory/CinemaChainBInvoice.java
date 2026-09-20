package abstractfactory;

import factorymethod.BookingReceipt;

final class CinemaChainBInvoice implements Invoice {
    @Override
    public String generate(BookingReceipt booking, Seat seat) {
        requireCompatibleSeat(seat);
        return "CHAIN-B RECEIPT\nCustomer: " + booking.customerName()
                + "\nBooking: " + booking.bookingDescription()
                + "\nLocation code: " + seat.getSeatId()
                + "\nAmount due: " + booking.finalPrice() + " KZT";
    }

    private void requireCompatibleSeat(Seat seat) {
        if (!seat.getSeatId().startsWith("B-")) {
            throw new IllegalArgumentException(
                    "CinemaChainB invoice cannot use a non-B seat ID.");
        }
    }
}
