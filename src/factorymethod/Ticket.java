package factorymethod;

import java.math.BigDecimal;

/** Product interface in the Factory Method pattern. */
public interface Ticket {
    String getType();

    void validate(BookingRequest request);

    BigDecimal calculatePrice(BookingRequest request);

    String describe(BookingRequest request);
}
