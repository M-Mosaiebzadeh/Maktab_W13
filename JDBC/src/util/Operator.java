package util;

import entities.Student;
import entities.Teacher;
import service.RelationServices;
import service.StudentServices;
import service.TeacherServices;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Operator {

    static Scanner scanInt = new Scanner(System.in);
    static Scanner scanString = new Scanner(System.in);

    public static void selectMenu() {
        Menu.showMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1:{
                    doWithStudent();
                    break;
                }
                case 2:{
                     doWithTeacher();
                    break;
                }
                case 3:{
                    System.exit(100);
                }
                default:
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
            }
            selectMenu();

        } catch (InputMismatchException exception) {
            System.out.println("just enter number");
            System.out.println("-------------------");
            scanInt.nextLine();
            selectMenu();
        }
    }


    public static void doWithStudent() {
        Menu.studentMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doAddStudentMenu();
                    break;
                }
                case 2: {
                    doEditStudentMenu();
                    break;
                }
                case 3: {
                    doDeleteStudent();
                    break;
                }
                case 4: {
                    doShowStudentMenu();
                    break;
                }
                case 5: {
                    selectMenu();
                    break;
                }
                case 6: {
                    System.exit(100);
                }
                default:
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
            }
            doWithStudent();

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doWithStudent();
        }
    }

    public static void doAddStudentMenu() {
        Menu.addStudentMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doAddStudent();
                    break;
                }
                case 2: {
                    doAddStudentToTeacherList();
                    break;
                }
                case 3: {
                    doWithStudent();
                    break;
                }
                default:
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
            }
            doAddStudentMenu();

        } catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doAddStudentMenu();
        }
    }

    public static void doAddStudent() {
        try {
            System.out.println("enter firstname: ");
            String firstname = scanString.next();
            System.out.println("enter lastname: ");
            String lastname = scanString.next();
            System.out.println("enter student code: ");
            Long studentCode = scanInt.nextLong();
            Student student = new Student(firstname,lastname,studentCode);

            StudentServices.add(student);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("enter correct value please!!");
            doAddStudentMenu();
        }
    }

    public static void doAddStudentToTeacherList() {
        try {
            System.out.println("enter teacher id: ");
            int teacherId = scanInt.nextInt();
            System.out.println("enter student id: ");
            int studentId = scanInt.nextInt();
            RelationServices.addStudentToTeacher(teacherId,studentId);

        } catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("enter correct value please!!");
            doAddStudentMenu();
        }
    }

    public static void doEditStudentMenu() {
        Menu.editStudentMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doEditStudentFirstname();
                    break;
                }
                case 2: {
                    doEditStudentLastname();
                    break;
                }
                case 3: {
                    doEditStudentCode();
                    break;
                }
                case 4: {
                    doWithStudent();
                    break;
                }
                default: {
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
                }
            }
            doEditStudentMenu();

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doEditStudentMenu();
        }
    }

    public static void doEditStudentFirstname() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new firstname");
            String firstname = scanString.next();
            StudentServices.editFirstname(id,firstname);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditStudentMenu();
        }
    }

    public static void doEditStudentLastname() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new lastname");
            String lastname = scanString.next();
            StudentServices.editLastname(id,lastname);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditStudentMenu();
        }
    }

    public static void doEditStudentCode() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new studentCode");
            long studentCode = scanInt.nextLong();
            StudentServices.editStudentCode(id,studentCode);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditStudentMenu();
        }
    }

    public static void doDeleteStudent() {
        try {
            System.out.println("Enter ID to delete");
            int id = scanInt.nextInt();
            StudentServices.delete(id);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doWithStudent();
        }

    }

    public static void doShowStudentMenu() {
        Menu.showStudentMenu();
        try{
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    StudentServices.showAll();
                    break;
                }
                case 2: {
                    doShowWithStudentId();
                    break;
                }
                case 3: {
                    doShowWithStudentFirstname();
                    break;
                }
                case 4: {
                    doShowWithStudentLastname();
                    break;
                }
                case 5: {
                    doShowWithStudentCode();
                    break;
                }
                case 6: {
                    doShowTeacherList();
                    break;
                }
                case 7: {
                    doWithStudent();
                    break;
                }
                default: {
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
                }
            }
            doShowStudentMenu();

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }

    public static void doShowWithStudentId() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            StudentServices.showWithId(id);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }

    public static void doShowWithStudentFirstname() {
        try{
            System.out.println("Enter firstname you are looking for");
            String firstname = scanString.next();
            StudentServices.showWithFirstname(firstname);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }

    public static void doShowWithStudentLastname() {
        try{
            System.out.println("Enter lastname you are looking for");
            String lastname = scanString.next();
            StudentServices.showWithLastname(lastname);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }

    public static void doShowWithStudentCode() {
        try{
            System.out.println("Enter studentCode you are looking for");
            long studentCode = scanInt.nextLong();
            StudentServices.showWithStudentCode(studentCode);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }

    public static void doShowTeacherList() {
        try{
            System.out.println("Enter student id: ");
            int studentId = scanInt.nextInt();
            RelationServices.showTeacherListOfStudent(studentId);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowStudentMenu();
        }
    }


    public static void doWithTeacher() {
        Menu.teacherMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doAddTeacherMenu();
                    break;
                }
                case 2: {
                    doEditTeacherMenu();
                    break;
                }
                case 3: {
                    doDeleteTeacher();
                    break;
                }
                case 4: {
                    doShowTeacherMenu();
                    break;
                }
                case 5: {
                    selectMenu();
                    break;
                }
                case 6: {
                    System.exit(100);
                }
                default:
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
            }
            doWithTeacher();

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doWithTeacher();
        }
    }

    public static void doAddTeacherMenu() {
        Menu.addTeacherMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doAddTeacher();
                    break;
                }
                case 2: {
                    doAddTeacherToStudentList();
                    break;
                }
                case 3: {
                    doWithTeacher();
                    break;
                }
                default:
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
            }
            doAddTeacherMenu();

        } catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doAddTeacherMenu();
        }
    }

    public static void doAddTeacher() {
        try {
            System.out.println("enter firstname: ");
            String firstname = scanString.next();
            System.out.println("enter lastname: ");
            String lastname = scanString.next();
            System.out.println("enter student code: ");
            Long personalCode = scanInt.nextLong();
            Teacher teacher = new Teacher(firstname,lastname,personalCode);

            TeacherServices.add(teacher);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("enter correct value please!!");
            doAddTeacherMenu();
        }
    }

    public static void doAddTeacherToStudentList() {
        try {
            System.out.println("enter student id: ");
            int studentId = scanInt.nextInt();
            System.out.println("enter teacher id: ");
            int teacherId = scanInt.nextInt();
            RelationServices.addTeacherToStudent(studentId,teacherId);

        } catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("enter correct value please!!");
            doAddTeacherMenu();
        }
    }

    public static void doEditTeacherMenu() {
        Menu.editTeacherMenu();
        try {
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    doEditTeacherFirstname();
                    break;
                }
                case 2: {
                    doEditTeacherLastname();
                    break;
                }
                case 3: {
                    doEditPersonalCode();
                    break;
                }
                case 4: {
                    doWithTeacher();
                    break;
                }
                default: {
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
                }
            }
            doEditTeacherMenu();

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doEditTeacherMenu();
        }
    }

    public static void doEditTeacherFirstname() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new firstname");
            String firstname = scanString.next();
            TeacherServices.editFirstname(id,firstname);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditTeacherMenu();
        }
    }

    public static void doEditTeacherLastname() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new lastname");
            String lastname = scanString.next();
            TeacherServices.editLastname(id,lastname);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditTeacherMenu();
        }
    }

    public static void doEditPersonalCode() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            System.out.println("Enter new personalCode");
            long personalCode = scanInt.nextLong();
            TeacherServices.editPersonalCode(id,personalCode);

        }catch(InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doEditTeacherMenu();
        }
    }

    public static void doDeleteTeacher() {
        try {
            System.out.println("Enter ID to delete");
            int id = scanInt.nextInt();
            TeacherServices.delete(id);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doWithTeacher();
        }

    }

    public static void doShowTeacherMenu() {
        Menu.showTeacherMenu();
        try{
            int number = scanInt.nextInt();
            switch (number) {
                case 1: {
                    TeacherServices.showAll();
                    break;
                }
                case 2: {
                    doShowWithTeacherId();
                    break;
                }
                case 3: {
                    doShowWithTeacherFirstname();
                    break;
                }
                case 4: {
                    doShowWithTeacherLastname();
                    break;
                }
                case 5: {
                    doShowWithPersonalCode();
                    break;
                }
                case 6: {
                    doShowStudentList();
                }
                case 7: {
                    doWithTeacher();
                    break;
                }
                default: {
                    System.out.println("please valid number!!");
                    System.out.println("-------------------");
                }
            }
            doShowTeacherMenu();

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("just enter number");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }

    public static void doShowWithTeacherId() {
        try{
            System.out.println("Enter ID you are looking for");
            int id = scanInt.nextInt();
            TeacherServices.showWithId(id);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }

    public static void doShowWithTeacherFirstname() {
        try{
            System.out.println("Enter firstname you are looking for");
            String firstname = scanString.next();
            TeacherServices.showWithFirstname(firstname);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }

    public static void doShowWithTeacherLastname() {
        try{
            System.out.println("Enter lastname you are looking for");
            String lastname = scanString.next();
            TeacherServices.showWithLastname(lastname);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }

    public static void doShowWithPersonalCode() {
        try{
            System.out.println("Enter personalCode you are looking for");
            long personalCode = scanInt.nextLong();
            TeacherServices.showWithStudentCode(personalCode);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }

    public static void doShowStudentList() {
        try{
            System.out.println("Enter teacher id: ");
            int teacherId = scanInt.nextInt();
            RelationServices.showStudentListOfTeacher(teacherId);

        }catch (InputMismatchException exception) {
            scanInt.nextLine();
            System.out.println("please enter valid value");
            System.out.println("-------------------");
            doShowTeacherMenu();
        }
    }
}