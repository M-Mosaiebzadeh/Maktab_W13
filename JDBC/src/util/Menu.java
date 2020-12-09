package util;

public class Menu {

    public static void showMenu() {
        System.out.println("Hello to DB Manager...");
        System.out.println("1.students... ");
        System.out.println("2.teachers... ");
        System.out.println("3.exit ");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void studentMenu() {
        System.out.println("student menu ....");
        System.out.println("1.add student");
        System.out.println("2.edit student");
        System.out.println("3.delete student");
        System.out.println("4.show student(s)");
        System.out.println("5.back to main menu");
        System.out.println("6.exit");
        System.out.println("****************");
        System.out.println("enter number ...");
    }
    public static void addStudentMenu() {
        System.out.println("add student...");
        System.out.println("1.add new student");
        System.out.println("2.add student to student list of teacher");
        System.out.println("3.back to student menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void editStudentMenu() {
        System.out.println("select one edit method ....");
        System.out.println("1.edit firstname");
        System.out.println("2.edit lastname");
        System.out.println("3.edit studentCode");
        System.out.println("4.back to student menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void showStudentMenu() {
        System.out.println("show student(s)...");
        System.out.println("1.show all");
        System.out.println("2.show with id");
        System.out.println("3.show with firstname");
        System.out.println("4.show with lastname");
        System.out.println("5.show with studentCode");
        System.out.println("6.show teacher list");
        System.out.println("7.back to student menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void teacherMenu() {
        System.out.println("teacher menu ....");
        System.out.println("1.add teacher");
        System.out.println("2.edit teacher");
        System.out.println("3.delete teacher");
        System.out.println("4.show teacher(s)");
        System.out.println("5.back to main menu");
        System.out.println("6.exit");
        System.out.println("****************");
        System.out.println("enter number ...");
    }
    public static void addTeacherMenu() {
        System.out.println("add teacher...");
        System.out.println("1.add new teacher");
        System.out.println("2.add teacher to teacher list of student");
        System.out.println("3.back to teacher menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void editTeacherMenu() {
        System.out.println("select one edit method ....");
        System.out.println("1.edit firstname");
        System.out.println("2.edit lastname");
        System.out.println("3.edit personalCode");
        System.out.println("4.back to teacher menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

    public static void showTeacherMenu() {
        System.out.println("show teacher(s)...");
        System.out.println("1.show all");
        System.out.println("2.show with id");
        System.out.println("3.show with firstname");
        System.out.println("4.show with lastname");
        System.out.println("5.show with personalCode");
        System.out.println("6.show student list");
        System.out.println("7.back to teacher menu");
        System.out.println("****************");
        System.out.println("enter number ...");
    }

}
