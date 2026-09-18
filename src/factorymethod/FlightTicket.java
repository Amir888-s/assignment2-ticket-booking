package factorymethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Set;

public final class FlightTicket implements Ticket {
    private static final Set<String> VALID_CLASSES = Set.of("ECONOMY", "BUSINESS");
    private static final BigDecimal TAX_RATE = new BigDecimal("0.12");
    private static final BigDecimal EXTRA_BAGGAGE_PER_KG = new BigDecimal("1500.00");

    @Override
    public String getType() {
        return "Flight";
    }

    @Override
    public void validate(BookingRequest request) {
        if (request.customerName() == null || request.customerName().isBlank()) {
            throw new IllegalArgumentException("Passenger name is required.");
        }
        if (request.basePrice() == null || request.basePrice().signum() <= 0) {
            throw new IllegalArgumentException("Base price must be positive.");
        }
        if (request.origin() == null || request.destination() == null
                || request.origin().isBlank() || request.destination().isBlank()
                || request.origin().equalsIgnoreCase(request.destination())) {
            throw new IllegalArgumentException("Origin and destination must be different.");
        }
        if (request.documentNumber() == null
                || !request.documentNumber().matches("[A-Z0-9]{6,12}")) {
            throw new IllegalArgumentException(
                    "Document number must contain 6-12 uppercase letters or digits.");
        }
        if (!VALID_CLASSES.contains(request.category().toUpperCase())) {
            throw new IllegalArgumentException("Travel class must be ECONOMY or BUSINESS.");
        }
        if (request.baggageKg() < 0) {
            throw new IllegalArgumentException("Baggage weight cannot be negative.");
        }
    }

    @Override
    public BigDecimal calculatePrice(BookingRequest request) {
        BigDecimal classPrice = request.category().equalsIgnoreCase("BUSINESS")
                ? request.basePrice().multiply(new BigDecimal("1.80"))
                : request.basePrice();

        double includedKg = request.category().equalsIgnoreCase("BUSINESS") ? 30.0 : 20.0;
        double extraKg = Math.max(0, request.baggageKg() - includedKg);
        BigDecimal baggageFee = EXTRA_BAGGAGE_PER_KG
                .multiply(BigDecimal.valueOf(extraKg));
        BigDecimal tax = classPrice.multiply(TAX_RATE);

        return classPrice.add(tax).add(baggageFee)
                .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public String describe(BookingRequest request) {
        return "Flight " + request.eventName() + " from " + request.origin()
                + " to " + request.destination() + ", "
                + request.category().toUpperCase() + " class";
    }
}
