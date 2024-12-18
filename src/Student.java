public class Student extends Person {
    private String studentId;
    private String major;

    public Student(String name, int age, String address, String studentId, String major) {
        super(name, age, address);
        this.studentId = studentId;
        this.major = major;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return super.toString() + ", Student ID: " + studentId + ", Major: " + major;
    }
}
