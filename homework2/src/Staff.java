public abstract class Staff {

    protected String staffId;
    protected String name;
    protected String department;

    public Staff(String staffId, String name, String department) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;
    }


    public abstract void performDuties();

    public void greet() {
        System.out.println("Hello, welcome to the hotel!");
    }

    public void greet(String guestName) {
        System.out.println("Hello " + guestName + ", welcome to the hotel! How can I help you?");
    }

    // Standard Getters
    public String getStaffId() { return staffId; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
}