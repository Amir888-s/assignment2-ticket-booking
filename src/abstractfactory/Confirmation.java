package abstractfactory;

import factorymethod.BookingReceipt;

/** Third product in a venue family. */
public interface Confirmation {
    String createMessage(BookingReceipt booking, Seat seat);
}
