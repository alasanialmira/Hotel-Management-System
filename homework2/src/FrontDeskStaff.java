public class FrontDeskStaff extends Staff {

    public FrontDeskStaff(String staffId, String name) {
        super(staffId, name, "Front Desk");
    }

    @Override
    public void performDuties() {
        System.out.println(this.name + " is checking in guests and managing room keys.");
    }

    @Override
    public String toString() {
        return String.format("Staff [ID: %s | Role: Front Desk | Name: %s]", staffId, name);
    }
}