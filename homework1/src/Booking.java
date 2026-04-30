import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking {
    private String bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Booking(String bookingId, Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        //checks for an invalid check-out date
        if (!checkOutDate.isAfter(checkInDate)) {
            throw new IllegalArgumentException("Check-out date must be strictly after check-in date.");
        }

        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

    public BigDecimal calculateTotalCost() {
        long nights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        return room.getDailyRate().multiply(BigDecimal.valueOf(nights));
    }

    public String getBookingId() { return bookingId; }
    public Room getRoom() { return room; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }

    @Override
    public String toString() {
        return "Booking " +
                "ID='" + bookingId + '\'' +
                " | Guest=" + guest.getName() + " " + guest.getLastName() +
                " | Room=" + room.getRoomId() +
                " | Dates=" + checkInDate + " to " + checkOutDate +
                " | Total=$" + calculateTotalCost().setScale(2, java.math.RoundingMode.HALF_UP);
    }
}