import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//PART4
public class Hotel {
    private String hotelName;
    private List<Room> rooms;
    private List<Booking> bookings;

    //initialization
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    //inventory management
    public void addRoom(Room room) {
        if (room != null && !rooms.contains(room)) {
            rooms.add(room);
        }
    }

    //Room availability check
    public List<Room> getAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        if (!checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Invalid date range.");
        }

        List<Room> availableRooms = new ArrayList<>();

        for (Room room : rooms) {
            boolean isRoomFree = true;
            for (Booking booking : bookings) {
                if (booking.getRoom().equals(room)) {
                    boolean datesOverlap = checkIn.isBefore(booking.getCheckOutDate()) &&
                            checkOut.isAfter(booking.getCheckInDate());
                    if (datesOverlap) {
                        isRoomFree = false;
                        break;
                    }
                }
            }
            if (isRoomFree) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    //make a new booking
    public boolean makeBooking(String bookingId, Room requestedRoom, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        List<Room> availableRooms = getAvailableRooms(checkIn, checkOut);

        if (availableRooms.contains(requestedRoom)) {
            Booking newBooking = new Booking(bookingId, requestedRoom, guest, checkIn, checkOut);
            bookings.add(newBooking);
            return true;
        }
        return false;
    }

    //cancel booking
    public String cancelBooking(String bookingId) {
        Booking bookingToRemove = null;

        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookingToRemove = booking;
                break;
            }
        }

        if (bookingToRemove != null) {
            bookings.remove(bookingToRemove);
            bookingToRemove.getRoom().setAvailable(true); // Free the room

            // Return the booking details as a String back to Main
            return "Success: Cancelled Booking -> \n" + bookingToRemove.toString();
        } else {
            return "Error: Booking " + bookingId + " not found.";
        }
    }
    //show all the rooms on the hotel
    public void displayAllRooms() {
        System.out.println("\n|||| " + hotelName + " Inventory |||");
        rooms.forEach(System.out::println);
    }

    //show all active bookings
    public void displayAllBookings() {
        System.out.println("\n||| Active Bookings |||");
        bookings.forEach(System.out::println);
    }

    //Observation
    public void displayOperationalReport() {
        System.out.println("\n||| Observation Report: " + hotelName + " |||");

        System.out.println("||| All Rooms Inventory (" + rooms.size() + " total) |||");
        for (Room room : rooms) {
            System.out.println(room.toString());
        }

        System.out.println("\n||| Active Bookings (" + bookings.size() + " total) |||");
        if (bookings.isEmpty()) {
            System.out.println("No active bookings at this time.");
        } else {
            for (Booking booking : bookings) {
                System.out.println(booking.toString());
            }
        }
    }
}


