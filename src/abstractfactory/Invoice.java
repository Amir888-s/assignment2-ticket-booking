package abstractfactory;

import factorymethod.BookingReceipt;

/** Second product in a venue family. */
public interface Invoice {
    String generate(BookingReceipt booking, Seat seat);
}
