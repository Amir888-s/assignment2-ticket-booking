package abstractfactory;

final class CinemaChainASeat implements Seat {
    private final String seatId;

    CinemaChainASeat(String row, int number) {
        this.seatId = "A-" + row + "-S" + String.format("%02d", number);
    }

    @Override
    public String getSeatId() {
        return seatId;
    }

    @Override
    public String getDescription() {
        return "CinemaChainA row-and-seat numbering: " + seatId;
    }
}
