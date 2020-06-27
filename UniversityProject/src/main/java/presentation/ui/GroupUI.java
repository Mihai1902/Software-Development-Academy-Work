package presentation.ui;

import business.services.CourseService;
import business.services.GroupService;
import model.dto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GroupUI {
    private GroupService groupService = new GroupService();
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
                    addGroup();
                    break;
                }
                case 2: {
                    viewGroups();
                    break;
                }
                case 3: {
                    updateGroup();
                    break;
                }
                case 4: {
                    deleteGroup();
                    break;
                }
                case 5: {
                    assignCourseToGroup();
                    break;
                }
            }
        }
    }

    private void assignCourseToGroup() {
        Group group = new Group();
        List<Group> groups = groupService.getGroups(group);
        if (!groups.isEmpty()) {
            groups.forEach(g -> {
                System.out.println(g.getGroupID() + ". " + g.getName());
            });
            System.out.print("Enter ID of Group: ");
            group = groupService.findGroup(group, scanner.nextInt());

            courseUI.viewCourses();
            Course course = new Course();
            System.out.print("Enter ID of Course: ");
            course = courseService.findCourse(course, scanner.nextInt());

            List<Course> courses = group.getCourses();
            courses.add(course);
            groupService.updateGroup(group);
        } else {
            System.out.println("No groups available yet.");
        }
    }

    private void deleteGroup() {
        viewGroups();
        System.out.print("Enter ID: ");
        Group group = new Group();
        group = groupService.findGroup(group, scanner.nextInt());
        groupService.deleteGroup(group);
    }

    private void updateGroup() {
        viewGroups();
        System.out.print("Enter ID: ");
        Group group = new Group();
        group = groupService.findGroup(group, scanner.nextInt());
        scanner.nextLine();
        group.setName(scanner.next());
        groupService.updateGroup(group);
    }

    public void viewGroups() {
        Group group = new Group();
        List<Group> groups = groupService.getGroups(group);
        groups.forEach(grp -> {
            System.out.print(" \n" + grp.getGroupID() + ". Group - " + grp.getName() + ". ");
            List<SubGroup> subGroups = grp.getSubgroups();
            if (subGroups.isEmpty()) {
                System.out.print("No subgroups yet.");
            } else {
                subGroups.forEach(subGroup -> {
                    System.out.print(subGroup.getName() + "\n");
                    List<Student> students = subGroup.getStudents();
                    if (students.isEmpty()) {
                        System.out.println();
                    } else {
                        students.forEach(student -> System.out.println(student.getFirstName() +
                                        " " + student.getLastName()));
                    }
                });
            }
        });
    }

    private void addGroup() {
        Group group = new Group();
        System.out.print("Enter name: ");
        group.setName(scanner.next());
        groupService.addGroup(group);
    }

    private void menu() {
        System.out.println("\nGROUPS MANAGEMENT " +
                "\n0.Back " +
                "\n1.Add Group " +
                "\n2.View Groups " +
                "\n3.Update Group " +
                "\n4.Delete Group" +
                "\n5.Assign Group to Course\n");
    }
}
