package presentation.ui;

import business.services.GroupService;
import business.services.StudentService;
import business.services.SubGroupService;
import model.dto.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StudentUI {
    private StudentService studentService = new StudentService();
    private Scanner scanner = new Scanner(System.in);
    private SubGroupUI subGroupUI = new SubGroupUI();
    private SubGroupService subGroupService = new SubGroupService();

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
                case 5: {
                    assignStudentToSubgroup();
                    break;
                }
            }
        }
    }

    private void assignStudentToSubgroup() {
        Student student = new Student();
        List<Student> students = studentService.getStudents(student);
        if (!students.isEmpty()) {
            students.forEach(s -> {
                System.out.println(s.getStudentID() + ". " + s.getFirstName() + " "
                        + s.getLastName());
            });
            System.out.print("Enter ID of Student: ");
            student = studentService.findStudent(student, scanner.nextInt());

            subGroupUI.viewSubGroups();
            SubGroup subGroup = new SubGroup();
            System.out.print("Enter ID of Subgroup: ");
            subGroup = subGroupService.findSubGroup(subGroup, scanner.nextInt());

            student.setSubGroup(subGroup);
            studentService.updateStudent(student);
        } else {
            System.out.println("No Students available yet.");
        }
    }
    public void menu() {
        System.out.println("\nSTUDENTS MANAGEMENT" +
                "\n0.Back" +
                "\n1.Add Student" +
                "\n2.View Students" +
                "\n3.Update Student" +
                "\n4.Delete Student" +
                "\n5.Assign Student to Subgroup\n");
    }

    public void add() {
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        studentService.addStudent(student);
    }

    public void update() {
        view();
        System.out.print("Enter ID: ");
        Student student = new Student();
        student = studentService.findStudent(student, scanner.nextInt());
        System.out.print("Enter new last name: ");
        student.setLastName(scanner.nextLine());
        scanner.nextLine();
        System.out.print("Enter new first name: ");
        student.setFirstName(scanner.nextLine());
        System.out.println("Enter new subgroup: ");
        subGroupUI.viewSubGroups();
        SubGroup subGroup = new SubGroup();
        subGroup = subGroupService.findSubGroup(subGroup, scanner.nextInt());
        student.setSubGroup(subGroup);
        studentService.updateStudent(student);
    }

    public void view() {
        Student student = new Student();
        List<Student> students = studentService.getStudents(student);
        students.forEach(stud -> {
            System.out.println("\n" + stud.getStudentID() + " - " + stud.getLastName() + " " + stud.getFirstName() + "\n");
            SubGroup subGroup = stud.getSubGroup();
            if (subGroup != null) {
                System.out.println("Subgroup: " + subGroup.getName());
            } else {
                System.out.println();
            }
            try {
                Group group = Objects.requireNonNull(subGroup).getGroup();
                System.out.println("Group " + group.getName());
            } catch (NullPointerException e) {
                System.out.println();
            }
            List<Course> courses = stud.getCourses();
            if (courses != null) {
                System.out.println("Courses - ");
                courses.forEach(course -> {
                    System.out.print(course.getName() + "\n " +
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