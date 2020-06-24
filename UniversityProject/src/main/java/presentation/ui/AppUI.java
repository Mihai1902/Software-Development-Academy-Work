package presentation.ui;

import java.util.Scanner;

public class AppUI {
    private static ClassroomUI classroomUI = new ClassroomUI();
    private static CourseUI courseUI = new CourseUI();
    private static GroupUI groupUI = new GroupUI();
    private static StudentUI studentUI = new StudentUI();
    private static SubGroupUI subGroupUI = new SubGroupUI();
    private static TeacherUI teacherUI = new TeacherUI();
    private static TimetableUI timetableUI = new TimetableUI();
    private static Scanner scanner = new Scanner(System.in);

    public static void start() {
        while (true) {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 0: {
                    UserUI.start();
                    break;
                }
                case 1: {
                    studentUI.start();
                    break;
                }
                case 2: {
                    teacherUI.start();
                    break;
                }
                case 3: {
                    groupUI.start();
                    break;
                }
                case 4: {
                    subGroupUI.start();
                    break;
                }
                case 5: {
                    courseUI.start();
                    break;
                }
                case 6: {
                    classroomUI.start();
                    break;
                }
                case 7: {
                    timetableUI.start();
                    break;
                }
            }
        }
    }

    private static void menu() {
        System.out.println("UNIVERSITY APPLICATION " +
                "\n0.LOGOUT " +
                "\n1.STUDENT MANAGEMENT " +
                "\n2.TEACHER MANAGEMENT " +
                "\n3.GROUP MANAGEMENT " +
                "\n4.SUBGROUP MANAGEMENT " +
                "\n5.COURSE MANAGEMENT " +
                "\n6.CLASSROOM MANAGEMENT " +
                "\n7.TIMETABLE MANAGEMENT" +
                "\n");
    }
}
