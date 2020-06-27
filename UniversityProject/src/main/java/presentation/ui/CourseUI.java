package presentation.ui;

import business.services.ClassroomService;
import business.services.CourseService;
import business.services.TeacherService;
import business.services.TimetableService;
import model.dto.Classroom;
import model.dto.Course;
import model.dto.Teacher;
import model.dto.Timetable;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class CourseUI {
    private Scanner scanner = new Scanner(System.in);
    private CourseService courseService = new CourseService();
    private TeacherService teacherService = new TeacherService();
    private TimetableService timetableService = new TimetableService();
    private TimetableUI timetableUI = new TimetableUI();
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
                    addCourse();
                    break;
                }
                case 2: {
                    viewCourses();
                    break;
                }
                case 3: {
                    updateCourse();
                    break;
                }
                case 4: {
                    deleteCourse();
                    break;
                }
                case 5: {
                    assignTimetableToCourse();
                    break;
                }
            }
        }
    }

    private void setTimetableToCourse() {
        Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if (!timetables.isEmpty()) {
            timetables.forEach(t -> {
                System.out.println(t.getTimetableID() +
                        t.getTimetableID() + ". " + t.getBegin().getHour() + ":" + t.getBegin().getMinute()
                        + " - " + t.getEnd().getHour() + ":" + t.getEnd().getMinute() + " Date: " + + t.getBegin().getDayOfMonth()
                        + "-" + t.getBegin().getMonth().name());
            });
            System.out.print("\nEnter ID of Timetable: ");
            timetable = timetableService.findTimetable(timetable, scanner.nextInt());

            Course course = new Course();
            viewCourses();
            System.out.print("Enter ID of Course: ");
            course = courseService.findCourse(course, scanner.nextInt());




            List<Course> courses = timetable.getCourses();
            courses.add(course);
            timetableService.updateTimetable(timetable);
        } else {
            System.out.println("No timetables available yet.");
        }
        /*Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if (!timetables.isEmpty()) {
            timetables.forEach(t -> {
                t.getClassrooms().forEach(c -> {
                    if (c.getTimetable().getCourses().isEmpty()) {
                        System.out.println(t.getTimetableID() + ". " + t.getDate().getDayOfMonth() + " " +
                                t.getDate().getMonthValue() + " - START: " + t.getBegin().getHour() + ":" + t.getBegin().getMinute()
                                + " END: " + t.getEnd().getHour() + ":" + t.getEnd().getMinute());
                    }
                });

            });

            System.out.print("Enter ID: ");
            timetable = timetableService.findTimetable(timetable, scanner.nextInt());
            scanner.nextLine();
            viewCourses();
            Course course = new Course();
            course = courseService.findCourse(course, scanner.nextInt());
            scanner.nextLine();
            List<Course> courses = timetable.getCourses();
            List<Timetable> timetables1 = course.getTimetables();
            courses.add(course);

            timetable.setCourses(courses);
            timetables1.add(timetable);
            course.setTimetables(timetables1);
            courseService.updateCourse(course);
            timetableService.updateTimetable(timetable);
        } else {
            System.out.println("No timetables available yet.");
        }

         */
    }


    private void assignTimetableToCourse() {
        Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if (!timetables.isEmpty()) {
            timetables.forEach(t -> {
                System.out.println(t.getTimetableID() + ". " + t.getBegin().getHour()
                        + ":" + t.getBegin().getMinute()
                        + " - " + t.getEnd().getHour() + ":" + t.getEnd().getMinute() + " Date: " + t.getBegin().getDayOfMonth()
                        + "-" + t.getBegin().getMonth().name());
            });
            System.out.print("Enter ID of Timetable: ");
            timetable = timetableService.findTimetable(timetable, scanner.nextInt());

            viewCourses();
            Course course = new Course();
            System.out.print("Enter ID of Course: ");
            course = courseService.findCourse(course, scanner.nextInt());

            List<Course> courses = timetable.getCourses();
            courses.add(course);
            timetableService.updateTimetable(timetable);
        } else {
            System.out.println("No timetables available yet.");
        }
    }

    private void deleteCourse() {
        viewCourses();
        System.out.println("Enter ID to delete: ");
        Course course = new Course();
        course = courseService.findCourse(course, scanner.nextInt());
        courseService.deleteCourse(course);
    }

    private void updateCourse() {
        viewCourses();
        System.out.print("Enter ID to update: ");
        Course course = new Course();
        course = courseService.findCourse(course, scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new name: ");
        course.setName(scanner.next());
        System.out.print("Enter new description: ");
        course.setDescription(scanner.next());
        courseService.updateCourse(course);

    }

    public void viewCourses() {
        Course course = new Course();
        List<Course> courses = courseService.getCourses(course);
        if (!courses.isEmpty()) {
            courses.forEach(course1 -> System.out.println(course1.getCourseID() + ". "
                    + course1.getName() + "(" + course1.getDescription() + ")"));
        } else {
            System.out.println("No courses available yet.");
        }
    }

    private void addCourse() {
        Course course = new Course();
        System.out.print("Enter name: ");
        course.setName(scanner.nextLine());
        System.out.print("Enter description: ");
        course.setDescription(scanner.nextLine());
        courseService.addCourse(course);
    }

    private void menu() {
        System.out.println("\nCOURSES MANAGEMENT " +
                "\n0.Back " +
                "\n1.Add Course " +
                "\n2.View Courses " +
                "\n3.Update Course " +
                "\n4.Delete Course " +
                "\n5.Assign Course to Timetable \n");
    }


}
