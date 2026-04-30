import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//PART5
public class Main {
    public static void main(String[] args) {

        System.out.println("\n ****** HOTEL MANAGEMENT SYSTEM ******  \n");
        System.out.println("\n ****** FLOWER HOTEL ******  \n");

        // System Initialization
        Hotel myHotel = new Hotel("FLOWER HOTEL");

        //populate
        Room room101 = new Room("101", "Standard", new BigDecimal(125.00));
        Room room102 = new Room("102", "Standard", new BigDecimal(125.00));
        Room room103 = new Room("103", "Standard", new BigDecimal(125.00));
        Room room104 = new Room("104", "Standard", new BigDecimal(125.00));
        Room room201 = new Room("201", "Deluxe",  new BigDecimal(225.00));
        Room room304 = new Room("304", "Suite",  new BigDecimal(325.00));


        myHotel.addRoom(room101);
        myHotel.addRoom(room102);
        myHotel.addRoom(room103);
        myHotel.addRoom(room104);
        myHotel.addRoom(room201);
        myHotel.addRoom(room304);

        //register guest
        Guest Guest1 = new Guest("G-01", "Ali", "Amzai", "ali@example.com");
        Guest Guest2 = new Guest("G-02", "Dona", "Dika", "dona@example.com");
        Guest Guest3 = new Guest("G-03", "Det", "Dika", "det@example.com");

        //simulation dates
        LocalDate date1 = LocalDate.of(2026, 6, 1);
        LocalDate date2 = LocalDate.of(2026, 6, 5);
        LocalDate date3 = LocalDate.of(2026, 6, 4);
        LocalDate date4 = LocalDate.of(2026, 6, 10);

        //register HotelServices
        HotelService breakfast = new RoomService("SRV-01", "Pancake", new BigDecimal("20.00"), new BigDecimal("5.00"));
        HotelService massage = new SpaTreatment("SRV-02", " Massage", new BigDecimal("120.00"), new BigDecimal("0.15")); // 15% tip
        HotelService laundry = new LaundryService("SRV-03", "Dry Cleaning", new BigDecimal("10.00"), 3, new BigDecimal("4.50"));

        myHotel.addService(breakfast);
        myHotel.addService(massage);
        myHotel.addService(laundry);

        //register Staff
        Staff frontDesk = new FrontDeskStaff("EMP-01", "Nora");
        Staff housekeeper = new HousekeepingStaff("EMP-02", "Meriton");
        Staff manager = new Manager("EMP-03", "Hana");

        myHotel.addStaff(frontDesk);
        myHotel.addStaff(housekeeper);
        myHotel.addStaff(manager);

        //Method Flexibility (Overloading)
        System.out.println("|||  Flexibility Demonstration - (Overloading) |||");
        frontDesk.greet();               // General greeting
        frontDesk.greet("Mr. Ali");    // Specific greeting

        //make booking
        System.out.println("||| Booking |||");
        Guest guest1 = new Guest("G-001","Dona","Dika","dona@example.com");
        Booking successfulBooking = null;

        // try and catch to check and throw exception where needed
        try {
            // Attempt a Valid Booking
            System.out.println("Attempting valid booking for Room 101...");
            myHotel.makeBooking("B-01", room101, guest1, LocalDate.now(), LocalDate.now().plusDays(3));
            System.out.println("-> Success! Room 101 is booked.");

            // To save it for the final bill later
            successfulBooking = new Booking("B-01", room101, guest1, LocalDate.now(), LocalDate.now().plusDays(3));

            // Attempt an Invalid Booking
            System.out.println("Attempting invalid booking (checkout before check-in)...");
            myHotel.makeBooking("B-02", room102, guest1, LocalDate.now().plusDays(5), LocalDate.now());

        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.err.println("-> EXCEPTION CAUGHT: " + e.getMessage());
        }

        try {
            // Attempt an Invalid Booking (Room already booked to trigger the other custom exception)
            System.out.println("Attempting to double-book Room 101...");
            myHotel.makeBooking("B-03", room101, guest1, LocalDate.now(), LocalDate.now().plusDays(1));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.err.println("-> EXCEPTION CAUGHT: " + e.getMessage());
        }


        // display all
        //myHotel.displayAllRooms();
        myHotel.displayAllServices();
        myHotel.displayAllStaff();

        myHotel.executeStaffDuties();
        System.out.println("\n||| Final Guest Checkout Tab |||");
        System.out.println("Compiling bill for " + guest1.getName() + "...");

        List<Chargeable> guestTab = new ArrayList<>();
        if (successfulBooking != null) guestTab.add(successfulBooking); // The room stay
        guestTab.add(breakfast); // Room Service
        guestTab.add(massage);   // Spa Treatment

        // The Hotel class calculates the total without needing to know WHAT the items are
        BigDecimal totalDue = myHotel.calculateGuestTab(guestTab);
        System.out.println("Total Amount Due: $" + totalDue);
    }
}