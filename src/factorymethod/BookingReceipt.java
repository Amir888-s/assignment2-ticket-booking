package factorymethod;

import java.math.BigDecimal;

public record BookingReceipt(
        String ticketType,
        String customerName,
        String bookingDescription,
        BigDecimal finalPrice) {
}
