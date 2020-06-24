package presentation.ui;

import business.services.ClassroomService;
import model.dto.Classroom;
import model.dto.Course;
import model.dto.Student;
import model.dto.Timetable;

import java.util.List;
import java.util.Scanner;

public class ClassroomUI {
    private ClassroomService classroomService = new ClassroomService();
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
                    addClassroom();
                    break;
                }
                case 2: {
                    viewClassroom();
                }
                case 3: {
                    updateClassroom();
                }
                case 4: {
                    deleteClassroom();
                }
            }
        }
    }

    public void menu() {
        System.out.println("CLASSROOM MANAGEMENT" +
                "0.Exit" +
                "\n1.Add Classroom" +
                "\n2.View All Classrooms" +
                "\n3.Update Classroom" +
                "\n4.Delete Classroom");
    }

    public void addClassroom() {
        System.out.println("Enter name: ");
        Classroom classroom = new Classroom();
        classroom.setName(scanner.nextLine());
        classroomService.addClassroom(classroom);
    }

    public void updateClassroom() {
        viewClassroom();
        System.out.println("Enter ID to update: ");
        int id = scanner.nextInt();
        Classroom classroom = new Classroom();
        classroom = classroomService.findClassroom(classroom, id);
        System.out.println("Enter new name: ");
        classroom.setName(scanner.nextLine());
        classroomService.updateClassroom(classroom);
    }

    public void viewClassroom() {
        Classroom classroom = new Classroom();
        classroomService.getClassrooms(classroom).forEach(hall -> {
            System.out.println("\n" + hall.getClassroomID() + " - " + hall.getName());
            Timetable timetable = hall.getTimetable();
            if(timetable != null ){
                List<Course> courses = hall.getTimetable().getCourses();
                if (courses != null) {
                    System.out.println("Courses - ");
                    courses.forEach(course -> {
                        System.out.print(course.getName() + "\n   " +
                                course.getDescription());
                    });
                } else {
                    System.out.println();
                }
            }else {
                System.out.println("No courses today! YAY!");
            }
        });
    }

    public void deleteClassroom() {
        viewClassroom();
        System.out.println("Enter ID to delete: ");
        int id = scanner.nextInt();
        Classroom classroom = new Classroom();
        classroom = classroomService.findClassroom(classroom, id);
        classroomService.deleteClassroom(classroom);
    }
}
