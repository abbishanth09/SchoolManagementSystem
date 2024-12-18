public class Teacher extends Person {
    private String employeeId;
    private String subject;

    public Teacher(String name, int age, String address, String employeeId, String subject) {
        super(name, age, address);
        this.employeeId = employeeId;
        this.subject = subject;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Subject: " + subject;
    }
}
