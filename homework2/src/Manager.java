public class Manager extends Staff {

    // Constructor
    public Manager(String staffId, String name) {
        super(staffId, name, "Management");
    }

    @Override
    public void performDuties() {
        System.out.println(this.name + " is overseeing hotel operations, managing staff schedules, and handling VIP escalations.");
    }

    @Override
    public String toString() {
        return String.format("Staff [ID: %s | Role: Hotel Manager | Name: %s]", staffId, name);
    }


    public void approveDiscount(double percentage) {
        System.out.println(this.name + " has approved a discount of " + percentage + "%.");
    }
}