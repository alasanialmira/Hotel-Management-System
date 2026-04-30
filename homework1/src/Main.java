import java.math.BigDecimal;
import java.time.LocalDate;

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

        // booking and cancelation
        System.out.println("Processing Booking 1 (Valid):");
        boolean b1 = myHotel.makeBooking("B-100", room201, Guest1, date1, date2);
        System.out.println("Status: " + (b1 ? "Success" : "Failed"));

        System.out.println("\nProcessing Booking 2 (Overlap Expected):");
        boolean b2 = myHotel.makeBooking("B-101", room201, Guest2, date3, date4);
        System.out.println("Status: " + (b2 ? "Success" : "Failed"));

        System.out.println("\nProcessing Booking 3 (Alternative Room):");
        boolean b3 = myHotel.makeBooking("B-102", room101, Guest2, date3, date4);
        System.out.println("Status: " + (b3 ? "Success" : "Failed"));


        System.out.println("\nCancellation of booking:");
        System.out.println(myHotel.cancelBooking("B-100"));


        // Operational Reporting
        myHotel.displayAllRooms();
        myHotel.displayAllBookings();
    }
}