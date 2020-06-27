package presentation.ui;

import business.services.ClassroomService;
import business.services.CourseService;
import business.services.TimetableService;
import model.dto.Classroom;
import model.dto.Course;
import model.dto.Teacher;
import model.dto.Timetable;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TimetableUI {
    private TimetableService timetableService = new TimetableService();
    private Scanner scanner = new Scanner(System.in);
    private ClassroomUI classroomUI = new ClassroomUI();
    private ClassroomService classroomService = new ClassroomService();

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
                    addTimetable();
                    break;
                }
                case 2: {
                    viewTimetables();
                    break;
                }
                case 3: {
                    updateTimetable();
                    break;
                }
                case 4: {
                    deleteTimetable();
                    break;
                }
                case 5: {
                    assignTimetableToClassroom();
                    break;
                }
            }
        }
    }

    private void assignTimetableToClassroom() {
        Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if(!timetables.isEmpty()){
            viewTimetables();
        }
        System.out.print("Enter ID of Timetable: ");
        timetable = timetableService.findTimetable(timetable,scanner.nextInt());

        classroomUI.viewClassroom();
        Classroom classroom = new Classroom();
        System.out.print("Enter ID of Classroom: ");
        classroom = classroomService.findClassroom(classroom,scanner.nextInt());

        List<Classroom> classrooms = timetable.getClassrooms();
        classrooms.add(classroom);
        timetableService.updateTimetable(timetable);
        /*
        Classroom classroom = new Classroom();
        List<Classroom> classrooms = classroomService.getClassrooms(classroom);
        classrooms.stream().forEach(c -> System.out.println(c.getClassroomID() + ". " +
                c.getName()));

        System.out.print("Enter ID of Classroom: ");
        classroom = classroomService.findClassroom(classroom, scanner.nextInt());

        Timetable timetable = new Timetable();
        viewTimetables();
        System.out.print("\nEnter ID of Timetable: ");
        timetable = timetableService.findTimetable(timetable, scanner.nextInt());

        classroom.setTimetable(timetable);
        timetableService.updateTimetable(timetable);

         */
    }

    private void deleteTimetable() {
        viewTimetables();
        System.out.println("Enter ID to delete: ");
        Timetable timetable = new Timetable();
        timetable = timetableService.findTimetable(timetable, scanner.nextInt());
        timetableService.deleteTimetable(timetable);
    }

    private void updateTimetable() {
        viewTimetables();
        System.out.print("Enter ID to update: ");
        Timetable timetable = new Timetable();
        timetable = timetableService.findTimetable(timetable, scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new begin date: ");
        String begin = scanner.next();
        LocalDateTime beginTime = LocalDateTime.parse(begin);
        System.out.print("Enter new end date: ");
        String end = scanner.next();
        LocalDateTime endTime = LocalDateTime.parse(end);
        timetable.setBegin(beginTime);
        timetable.setEnd(endTime);
        timetableService.updateTimetable(timetable);

    }

    public void viewTimetables() {
        /*Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if (!timetables.isEmpty()) {
            timetables.forEach(timetab -> {
                System.out.println(timetab.getTimetableID() + ". " + timetab.getBegin().getHour() + ":" + timetab.getBegin().getMinute()
                        + " - " + timetab.getEnd().getHour() + ":" + timetab.getEnd().getMinute() + " Date: " + timetab.getDate());
                List<Classroom> classrooms = timetab.getClassrooms();
            });
        } else {
            System.out.println("No timetables available yet.");
        }
         */
        Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        timetables.forEach(t -> System.out.print("" + t.getTimetableID() + ". " + t.getBegin().getHour()
                + ":" + t.getBegin().getMinute()
                + " - " + t.getEnd().getHour() + ":" + t.getEnd().getMinute() + " Date: " + t.getBegin().getDayOfMonth()
                + "-" + t.getBegin().getMonth().name()));

    }

    private void addTimetable() {
        Timetable timetable = new Timetable();
        System.out.print("Enter begin date: ");
        String begin = scanner.next();
        LocalDateTime beginTime = LocalDateTime.parse(begin);
        System.out.print("Enter end date: ");
        String end = scanner.next();
        LocalDateTime endTime = LocalDateTime.parse(end);
        timetable.setBegin(beginTime);
        timetable.setEnd(endTime);


        timetableService.addTimetable(timetable);
    }

    private void menu() {
        System.out.println("\nTIMETABLE MANAGEMENT " +
                "\n0.Back " +
                "\n1.Add Timetable " +
                "\n2.View Timetables " +
                "\n3.Update Timetable " +
                "\n4.Delete Timetable" +
                "\n5.Assign Timetable to Classroom");
    }
}