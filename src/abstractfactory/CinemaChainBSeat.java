package abstractfactory;

final class CinemaChainBSeat implements Seat {
    private final String seatId;

    CinemaChainBSeat(String zone, int number) {
        this.seatId = "B-" + zone + "-" + String.format("%03d", number);
    }

    @Override
    public String getSeatId() {
        return seatId;
    }

    @Override
    public String getDescription() {
        return "CinemaChainB zone numbering: " + seatId;
    }
}
