package presentation.ui;

import business.services.StudentService;
import model.dto.*;

import java.util.List;
import java.util.Scanner;

public class StudentUI {
    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    AppUI.start();
                    break;
                }
                case 1: {
                    add();
                    break;
                }
                case 2: {
                    view();
                    break;
                }
                case 3: {
                    update();
                    break;
                }
                case 4: {
                    delete();
                    break;
                }
            }
        }
    }

    public void menu() {
        System.out.println("STUDENTS MANAGEMENT" +
                "\n0.Exit" +
                "\n1.Add Student" +
                "\n2.View Students" +
                "\n3.Update Student" +
                "\n4.Delete Student\n");
    }

    public void add() {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String firstName = scanner.nextLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentService.addStudent(student);
    }

    public void update() {
        view();
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        Student student = new Student();
        student = studentService.findStudent(student, id);
        System.out.print("Enter new last name: ");
        student.setLastName(scanner.nextLine());
        scanner.nextLine();
        System.out.print("Enter new first name: ");
        student.setFirstName(scanner.nextLine());
        studentService.updateStudent(student);
    }

    public void view() {
        Student student = new Student();
        studentService.getStudents(student).forEach(stud -> {
            System.out.println("\n" + stud.getStudentID() + " - " + stud.getLastName() + " " + stud.getFirstName() + "\n");
            SubGroup subGroup = stud.getSubGroup();
            Group group = stud.getSubGroup().getGroup();
            if (subGroup != null) {
                System.out.println("Subgroup: " + subGroup.getName());
            } else {
                System.out.println();
            }
            if (group != null) {
                System.out.println("Group: " + group.getName());
            } else {
                System.out.println();
            }
            List<Course> courses = stud.getCourses();
            if (courses != null) {
                System.out.println("Courses - ");
                courses.forEach(course -> {
                    System.out.print(course.getName() + "\n   " +
                            course.getDescription());
                });
            } else {
                System.out.println();
            }
        });
    }

    public void delete() {
        view();
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        Student student = new Student();
        student = studentService.findStudent(student, id);
        studentService.deleteStudent(student);
    }
}