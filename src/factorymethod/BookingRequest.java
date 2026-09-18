package factorymethod;

import java.math.BigDecimal;

/** Input data shared by the three ticket examples. */
public record BookingRequest(
        String customerName,
        int customerAge,
        String eventName,
        BigDecimal basePrice,
        String category,
        int minimumAge,
        double baggageKg,
        String origin,
        String destination,
        String documentNumber) {

    public static BookingRequest concert(
            String customerName, int customerAge, String eventName,
            BigDecimal basePrice, String zone) {
        return new BookingRequest(customerName, customerAge, eventName, basePrice,
                zone, 16, 0, "", "", "");
    }

    public static BookingRequest movie(
            String customerName, int customerAge, String eventName,
            BigDecimal basePrice, String seatType, int minimumAge) {
        return new BookingRequest(customerName, customerAge, eventName, basePrice,
                seatType, minimumAge, 0, "", "", "");
    }

    public static BookingRequest flight(
            String customerName, String flightNumber, BigDecimal basePrice,
            String travelClass, double baggageKg, String origin,
            String destination, String documentNumber) {
        return new BookingRequest(customerName, 0, flightNumber, basePrice,
                travelClass, 0, baggageKg, origin, destination, documentNumber);
    }
}
