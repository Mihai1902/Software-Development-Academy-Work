package presentation.ui;

import business.services.StudentService;
import model.dto.Course;
import model.dto.Student;
import model.dto.Teacher;

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
                    System.exit(0);
                    break;
                }
                case 1: {
                    add();
                    break;
                }
                case 2:{
                    view();
                }
                case 3:{
                    update();
                }
                case 4:{
                    delete();
                }
            }
        }
    }

        public void menu () {
            System.out.println("STUDENT MANAGEMENT" +
                    "0.Exit" +
                    "\n1.Add Student" +
                    "\n2.View All Students" +
                    "\n3.Update Student" +
                    "\n4.Delete Student");
        }

        public void add(){
            System.out.println("Enter last name: ");
            String lastName = scanner.nextLine();
            scanner.nextLine();
            System.out.println("Enter last name: ");
            String firstName = scanner.nextLine();
            scanner.nextLine();
            Student student = new Student();
            student.setFirstName(firstName);
            student.setLastName(lastName);
            studentService.addStudent(student);
        }

        public void update(){
            view();
            System.out.println("Enter ID: ");
            int id = scanner.nextInt();
            Student student = studentService.findStudent(id);
            System.out.println("Enter new last name: ");
            student.setLastName(scanner.nextLine());
            scanner.nextLine();
            System.out.println("Enter new first name: ");
            student.setFirstName(scanner.nextLine());
            studentService.updateStudent(student);
        }

        public void view(){
            studentService.getStudents().stream().forEach(parameter ->{
                System.out.println("\n" + parameter.getId() + " - " + parameter.getLastName() + " " + parameter.getFirstName() +"\n"
                + "Subgroup: " + parameter.getSubGroup().getName()
                + "Group: " + parameter.getSubGroup().getGroup().getName());
                List<Course> courses = parameter.getCourses();
                if(courses != null){
                    System.out.println("Courses - ");
                    courses.stream().forEach(course -> { System.out.print(course.getName()+ "\n   " +
                            course.getDescription());
                    });}else{
                    System.out.println();
                }
            });
        }

        public void delete(){
        view();
            System.out.println("Enter ID");
            int id = scanner.nextInt();
            Student student = studentService.findStudent(id);
            studentService.deleteStudent(student);
        }
    }
