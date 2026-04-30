import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class Hotel {
    private String hotelName;
    private List<Room> rooms;
    private List<Booking> bookings;
    private List<HotelService> services;
    private List<Staff> staffMembers;

    //initialization
    public Hotel(String hotelName) {
        this.hotelName = hotelName;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.services = new ArrayList<>();
        this.staffMembers = new ArrayList<>();
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
            // Because Room implements Bookable, we just ask the room directly!
            if (!room.isBooked(checkIn, checkOut)) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }

    //make a new booking
    public boolean makeBooking(String bookingId, Room requestedRoom, Guest guest, LocalDate checkIn, LocalDate checkOut)
            throws InvalidBookingDatesException, RoomUnavailableException {

        if (!checkOut.isAfter(checkIn)) {
            throw new InvalidBookingDatesException("Error: Checkout must be after check-in.");
        }

        List<Room> availableRooms = getAvailableRooms(checkIn, checkOut);

        if (availableRooms.contains(requestedRoom)) {
            Booking newBooking = new Booking(bookingId, requestedRoom, guest, checkIn, checkOut);
            bookings.add(newBooking);

            // HW2: Mark the room as booked internally using the Bookable interface
            requestedRoom.markAsBooked(checkIn, checkOut);
            return true;
        } else {
            throw new RoomUnavailableException("Failed: Room " + requestedRoom.getRoomId() + " is already booked for these dates.");
        }
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

    public void addService(HotelService service) {
        if (service != null && !services.contains(service)) {
            services.add(service);
        }
    }

    public void displayAllServices() {
        System.out.println("\n||| Available Hotel Services |||");
        services.forEach(System.out::println); // Polymorphism automatically calls the correct toString()
    }


    public void addStaff(Staff staff) {
        if (staff != null && !staffMembers.contains(staff)) {
            staffMembers.add(staff);
        }
    }

    public void displayAllStaff() {
        System.out.println("\n||| Hotel Staff |||");
        staffMembers.forEach(System.out::println);
    }

    public void executeStaffDuties() {
        System.out.println("\n||| Executing Staff Duties |||");
        for (Staff employee : staffMembers) {
            employee.performDuties(); // Polymorphism automatically calls the correct specific duty
        }
    }

    public BigDecimal calculateGuestTab(List<Chargeable> purchasedItems) {
        BigDecimal totalBill = BigDecimal.ZERO;

        for (Chargeable item : purchasedItems) {
            // Because every item implements Chargeable, we know it has a getCost() method
            totalBill = totalBill.add(item.getCost());
        }

        return totalBill;
    }


    // Show all the rooms on the hotel
    // public void displayAllRooms() {
    //   System.out.println("\n|||| " + hotelName + " Inventory |||");
    //    rooms.forEach(System.out::println);
    //}

    // Show all active bookings
    public void displayAllBookings() {
        System.out.println("\n||| Active Bookings |||");
        bookings.forEach(System.out::println);
    }

    // Observation
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


