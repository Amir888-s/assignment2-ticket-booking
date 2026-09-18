package factorymethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public final class MovieTicket implements Ticket {
    private static final Set<String> VALID_SEATS = Set.of("REGULAR", "PREMIUM");
    private static final BigDecimal PREMIUM_FEE = new BigDecimal("1000.00");

    @Override
    public String getType() {
        return "Movie";
    }

    @Override
    public void validate(BookingRequest request) {
        if (request.customerName() == null || request.customerName().isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        if (request.basePrice() == null || request.basePrice().signum() <= 0) {
            throw new IllegalArgumentException("Base price must be positive.");
        }
        if (request.customerAge() < request.minimumAge()) {
            throw new IllegalArgumentException(
                    "Customer does not meet the movie age requirement of "
                            + request.minimumAge() + ".");
        }
        if (!VALID_SEATS.contains(request.category().toUpperCase())) {
            throw new IllegalArgumentException("Movie seat must be REGULAR or PREMIUM.");
        }
    }

    @Override
    public BigDecimal calculatePrice(BookingRequest request) {
        BigDecimal price = request.basePrice();
        if (request.category().equalsIgnoreCase("PREMIUM")) {
            price = price.add(PREMIUM_FEE);
        }
        return price.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String describe(BookingRequest request) {
        return request.eventName() + " movie, " + request.category().toUpperCase() + " seat";
    }
}
