public class HousekeepingStaff extends Staff {

    public HousekeepingStaff(String staffId, String name) {
        super(staffId, name, "Housekeeping");
    }

    @Override
    public void performDuties() {
        System.out.println(this.name + " is cleaning rooms, replacing linens, and restocking supplies.");
    }

    @Override
    public String toString() {
        return String.format("Staff [ID: %s | Role: Housekeeping | Name: %s]", staffId, name);
    }
}