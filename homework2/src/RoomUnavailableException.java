public class RoomUnavailableException extends Exception {

    public RoomUnavailableException() {
        super("The requested room is currently unavailable for these dates.");
    }

    public RoomUnavailableException(String message) {
        super(message);
    }
}