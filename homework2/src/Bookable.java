import java.time.LocalDate;

public interface Bookable {
    // Standard behavior for any entity that can be reserved for specific dates
    boolean isBooked(LocalDate start, LocalDate end);
    void markAsBooked(LocalDate start, LocalDate end);
}