public class Guest {
    private String id;
    private String name;
    private String lastName;
    private String email;

    public Guest(String id, String name, String lastName, String email) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    public String getId() {return id;}
    public String getName() {return name;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}

    public void setName(String name) {this.name = name;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setEmail(String email) {this.email = email;}

    @Override
    public String toString() {
        return "ID: " + id +
                " | Name: " + name +
                " | Last Name: " + lastName +
                " | Email: " + email;
    }
}
