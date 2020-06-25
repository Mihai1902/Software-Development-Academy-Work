package presentation.ui;

import business.services.CourseService;
import business.services.TeacherService;
import model.dto.Course;
import model.dto.Teacher;

import java.util.List;
import java.util.Scanner;

public class TeacherUI {
    private TeacherService teacherService = new TeacherService();
    private Scanner scanner = new Scanner(System.in);
    private CourseUI courseUI = new CourseUI();
    private CourseService courseService = new CourseService();
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
                    addTeacher();
                    break;
                }
                case 2: {
                    viewTeachers();
                    break;
                }
                case 3: {
                    updateTeacher();
                    break;
                }
                case 4: {
                    deleteTeacher();
                    break;
                }
            }
        }
    }

    private void deleteTeacher() {
        viewTeachers();
        System.out.println("Enter ID to delete: ");
        Teacher teacher = new Teacher();
        teacher = teacherService.findTeacher(teacher, scanner.nextInt());
        teacherService.deleteTeacher(teacher);
    }

    private void updateTeacher() {
        viewTeachers();
        System.out.print("Enter ID to update: ");
        Teacher teacher = new Teacher();
        teacher = teacherService.findTeacher(teacher, scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new first name: ");
        teacher.setFirstName(scanner.next());
        System.out.print("Enter new last name: ");
        teacher.setLastName(scanner.next());
        teacherService.updateTeacher(teacher);

    }

    private void viewTeachers() {
        Teacher teacher = new Teacher();
        List<Teacher> teachers = teacherService.getTeachers(teacher);
        if (!teachers.isEmpty()) {
            teachers.forEach(professor -> {
                System.out.println(professor.getTeacherID() + ". " + professor.getFirstName()
                        + " " + professor.getLastName() + " - COURSES: ");
                List<Course> courses = professor.getCourses();
                if (courses != null) {
                    courses.forEach(course -> {
                        System.out.print(course.getName() + " " + course.getDescription() + " \n");
                    });
                } else {
                    System.out.println();
                }
            });
        } else {
            System.out.println("No teachers available yet.");
        }
    }

    private void addTeacher() {
        Teacher teacher = new Teacher();
        System.out.print("Enter first name: ");
        teacher.setFirstName(scanner.next());
        System.out.print("Enter last name: ");
        teacher.setLastName(scanner.next());
        courseUI.viewCourses();
        Course course = new Course();
        System.out.print("Enter ID of course: ");
        course = courseService.findCourse(course, scanner.nextInt());
        List<Course> courses = teacher.getCourses();
        courses.add(course);
        teacher.setCourses(courses);
        teacherService.addTeacher(teacher);
    }

    private void menu() {
        System.out.println("\nTEACHERS MANAGEMENT " +
                "\n0.Back " +
                "\n1.Add Teacher " +
                "\n2.View Teachers " +
                "\n3.Update Teacher " +
                "\n4.Delete Teacher\n");
    }
}
