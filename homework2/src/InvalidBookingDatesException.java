public class InvalidBookingDatesException extends Exception {

    public InvalidBookingDatesException() {
        super("Check-out date must be strictly after the check-in date.");
    }

    public InvalidBookingDatesException(String message) {

        super(message);
    }
}