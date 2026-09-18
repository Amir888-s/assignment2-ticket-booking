package factorymethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public final class ConcertTicket implements Ticket {
    private static final Set<String> VALID_ZONES = Set.of("STANDARD", "VIP");
    private static final BigDecimal SERVICE_FEE = new BigDecimal("500.00");

    @Override
    public String getType() {
        return "Concert";
    }

    @Override
    public void validate(BookingRequest request) {
        requireCommonData(request);
        if (request.customerAge() < 16) {
            throw new IllegalArgumentException("Concert customers must be at least 16.");
        }
        if (!VALID_ZONES.contains(request.category().toUpperCase())) {
            throw new IllegalArgumentException("Concert zone must be STANDARD or VIP.");
        }
    }

    @Override
    public BigDecimal calculatePrice(BookingRequest request) {
        BigDecimal multiplier = request.category().equalsIgnoreCase("VIP")
                ? new BigDecimal("1.50")
                : BigDecimal.ONE;
        return request.basePrice().multiply(multiplier)
                .add(SERVICE_FEE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String describe(BookingRequest request) {
        return request.eventName() + " concert, " + request.category().toUpperCase() + " zone";
    }

    private void requireCommonData(BookingRequest request) {
        if (request.customerName() == null || request.customerName().isBlank()) {
            throw new IllegalArgumentException("Customer name is required.");
        }
        if (request.basePrice() == null || request.basePrice().signum() <= 0) {
            throw new IllegalArgumentException("Base price must be positive.");
        }
    }
}
