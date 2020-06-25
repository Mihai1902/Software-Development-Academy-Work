package presentation.ui;

import business.services.CourseService;
import business.services.TeacherService;
import business.services.TimetableService;
import model.dto.Course;
import model.dto.Teacher;
import model.dto.Timetable;

import java.util.List;
import java.util.Scanner;

public class CourseUI {
    private Scanner scanner = new Scanner(System.in);
    private CourseService courseService = new CourseService();
    private TeacherService teacherService = new TeacherService();
    private TimetableService timetableService = new TimetableService();
    private TimetableUI timetableUI = new TimetableUI();

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
                    assignCourseToTeacher();
                    break;
                }
                case 6: {
                    setCourseToTimetable();
                    break;
                }
            }
        }
    }

    private void setCourseToTimetable() {
        Timetable timetable = new Timetable();
        List<Timetable> timetables = timetableService.getTimetables(timetable);
        if (!timetables.isEmpty()) {
            timetables.forEach(t -> {
                t.getClassrooms().forEach(c -> {
                    if (!c.getTimetable().getCourses().isEmpty()) {
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
    }


    private void assignCourseToTeacher() {
        Teacher teacher = new Teacher();
        List<Teacher> teachers = teacherService.getTeachers(teacher);
        if (!teachers.isEmpty()) {
            teachers.forEach(professor -> {
                System.out.println(professor.getTeacherID() + ". " + professor.getFirstName() + " "
                        + professor.getLastName());
                List<Course> courses = professor.getCourses();
                if (courses != null) {
                    viewCourses();
                } else {
                    System.out.println();
                }
            });
            System.out.print("Enter ID: ");
            teacher = teacherService.findTeacher(teacher, scanner.nextInt());
            scanner.nextLine();
            viewCourses();
            Course course = new Course();
            course = courseService.findCourse(course, scanner.nextInt());
            scanner.nextLine();
            List<Course> courses = teacher.getCourses();
            courses.add(course);
            teacherService.updateTeacher(teacher);
        } else {
            System.out.println("No teachers available yet.");
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
        course.setName(scanner.next());
        System.out.print("Enter description: ");
        course.setDescription(scanner.next());
        courseService.addCourse(course);
    }

    private void menu() {
        System.out.println("\nCOURSES MANAGEMENT " +
                "\n0.Back " +
                "\n1.Add Course " +
                "\n2.View Courses " +
                "\n3.Update Course " +
                "\n4.Delete Course " +
                "\n5.Assign Course to Teacher " +
                "\n6.Assign Course to Timetable \n");
    }


}
