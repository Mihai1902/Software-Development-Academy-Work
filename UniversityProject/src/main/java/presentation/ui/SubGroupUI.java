package presentation.ui;

import business.services.GroupService;
import business.services.SubGroupService;
import model.dto.*;

import java.util.List;
import java.util.Scanner;

public class SubGroupUI {
    private SubGroupService subGroupService = new SubGroupService();
    private GroupService groupService = new GroupService();
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
                    addSubGroup();
                    break;
                }
                case 2: {
                    viewSubGroups();
                    break;
                }
                case 3: {
                    updateSubGroup();
                    break;
                }
                case 4: {
                    deleteSubGroup();
                    break;
                }
            }
        }
    }

    private void deleteSubGroup() {
        System.out.println("Enter ID to delete: ");
        SubGroup subGroup = new SubGroup();
        subGroup = subGroupService.findSubGroup(subGroup, scanner.nextInt());
        subGroupService.deleteSubGroup(subGroup);
    }

    private void updateSubGroup() {
        viewSubGroups();
        System.out.print("Enter ID to update: ");
        SubGroup subGroup = new SubGroup();
        subGroup = subGroupService.findSubGroup(subGroup, scanner.nextInt());
        scanner.nextLine();
        System.out.print("Enter new name: ");
        subGroup.setName(scanner.next());
        subGroupService.updateSubGroup(subGroup);

    }

    private void viewSubGroups() {
        SubGroup subGroup = new SubGroup();
        List<SubGroup> subGroups = subGroupService.getSubGroups(subGroup);
        subGroups.forEach(sg -> {
            System.out.println(sg.getSubgroupID() + ". " + sg.getName());
            List<Student> students = sg.getStudents();
            if (students.isEmpty()) {
                System.out.println("No students assigned yet.");
            } else {
                students.forEach(student -> System.out.println(student.getStudentID() +
                        "." + student.getFirstName() +
                        " " + student.getLastName()));
            }
        });
    }

    private void addSubGroup() {
        SubGroup subGroup = new SubGroup();
        System.out.print("Enter name: ");
        subGroup.setName(scanner.next());
        subGroupService.addSubGroup(subGroup);
    }

    private void menu() {
        System.out.println("SUBGROUPS MANAGEMENT " +
                "\n0.Exit " +
                "\n1.Add Subgroup " +
                "\n2.View Subgroups " +
                "\n3.Update Subgroup " +
                "\n4.Delete Subgroup\n");
    }
}
