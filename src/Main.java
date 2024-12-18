import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "persons.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> persons = new ArrayList<>();

        // Getting data from file
        loadFromFile(persons);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Add a student");
            System.out.println("2. Add a teacher");
            System.out.println("3. View all persons");
            System.out.println("4. Remove a person");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    addStudent(scanner, persons);
                    break;
                case 2:
                    addTeacher(scanner, persons);
                    break;
                case 3:
                    viewPersons(persons);
                    break;
                case 4:
                    removePerson(scanner, persons);
                    break;
                case 5:
                    saveToFile(persons);
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addStudent(Scanner scanner, List<Person> persons) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter major: ");
        String major = scanner.nextLine();

        Student student = new Student(name, age, address, studentId, major);
        persons.add(student);
    }

    private static void addTeacher(Scanner scanner, List<Person> persons) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();

        Teacher teacher = new Teacher(name, age, address, employeeId, subject);
        persons.add(teacher);
    }

    private static void viewPersons(List<Person> persons) {
        if (persons.isEmpty()) {
            System.out.println("No persons found.");
        } else {
            for (Person person : persons) {
                System.out.println(person);
            }
        }
    }

    private static void removePerson(Scanner scanner, List<Person> persons) {
        System.out.println("Enter the name of the person to remove:");
        String name = scanner.nextLine();
        Person personToRemove = null;

        for (Person person : persons) {
            if (person.getName().equalsIgnoreCase(name)) {
                personToRemove = person;
                break;
            }
        }

        if (personToRemove != null) {
            persons.remove(personToRemove);
            System.out.println(name + " has been removed.");
        } else {
            System.out.println("Person not found.");
        }
    }

    private static void saveToFile(List<Person> persons) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Person person : persons) {
                writer.write(person.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e.getMessage());
        }
    }

    private static void loadFromFile(List<Person> persons) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                    if (line.contains("Student ID:")) {
                    String[] parts = line.split(", ");
                    String name = parts[0].split(": ")[1];
                    int age = Integer.parseInt(parts[1].split(": ")[1]);
                    String address = parts[2].split(": ")[1];
                    String studentId = parts[3].split(": ")[1];
                    String major = parts[4].split(": ")[1];
                    persons.add(new Student(name, age, address, studentId, major));
                } else if (line.contains("Employee ID:")) {
                    String[] parts = line.split(", ");
                    String name = parts[0].split(": ")[1];
                    int age = Integer.parseInt(parts[1].split(": ")[1]);
                    String address = parts[2].split(": ")[1];
                    String employeeId = parts[3].split(": ")[1];
                    String subject = parts[4].split(": ")[1];
                    persons.add(new Teacher(name, age, address, employeeId, subject));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading from file: " + e.getMessage());
        }
    }
}
