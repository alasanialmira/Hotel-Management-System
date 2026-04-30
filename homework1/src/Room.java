import java.math.BigDecimal;
import java.util.Objects;

public class Room {
    private String roomId;
    private String roomType;
    private BigDecimal dailyRate;
    private boolean isAvailable; // General status

    //Default configuration for a standard room
    public Room() {
        this.roomId = "S-000";
        this.roomType = "Standard";
        this.dailyRate = new BigDecimal("50.00");
        this.isAvailable = true;
    }

    //Parametrized for specific versions
    public Room(String roomId, String roomType, BigDecimal dailyRate) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.isAvailable = true;
    }

    // Getters
    public String getRoomId() { return roomId; }
    public String getRoomType() { return roomType; }
    public BigDecimal getDailyRate() { return dailyRate; }
    public boolean isAvailable() { return isAvailable; }

    // Setters
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setDailyRate(BigDecimal dailyRate) { this.dailyRate = dailyRate; }
    public void setAvailable(boolean available) { isAvailable = available; }

    //if roomIds match, rooms are identical
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId.equals(room.roomId);
    }

    @Override
    public int hashCode() { return Objects.hash(roomId); }

    //Display
    @Override
    public String toString() {
        return "Room ID: " + roomId +
                " | Type: " + roomType +
                " | Daily Rate: " + dailyRate + "$" +
                " | Available: " + isAvailable;
    }
}