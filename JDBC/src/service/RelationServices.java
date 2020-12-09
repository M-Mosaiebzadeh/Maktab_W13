package service;

import entities.Student;
import entities.Teacher;
import factory.CreateConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RelationServices {

//    public static void addTeacherToStudent(int studentId, int teacherId) {
//        Student student = StudentServices.load(studentId);
//        if (student != null) {
//            try (Connection connectionStudent = CreateConnection.getConnection();
//                 PreparedStatement preparedStatementStudent = connectionStudent.prepareStatement("insert into relation(student_id) values (?)")) {
//                connectionStudent.setAutoCommit(false);
//                preparedStatementStudent.setInt(1,studentId);
//                preparedStatementStudent.executeUpdate();
//                Teacher teacher = TeacherServices.load(teacherId);
//
//
//                if (teacher != null){
//                    try(Connection connectionTeacher = CreateConnection.getConnection();
//                        PreparedStatement preparedStatementTeacher = connectionStudent.prepareStatement("insert into relation(teacher_id) values (?)")) {
//                        connectionTeacher.setAutoCommit(false);
//                        preparedStatementTeacher.setInt(1,teacherId);
//                        preparedStatementTeacher.executeUpdate();
//                        connectionStudent.commit();
//                        connectionTeacher.commit();
//                    }catch (SQLException sqlException) {
//
//                        sqlException.printStackTrace();
//                    }
//                } else {
//                    System.out.println("not find your student" + "\n");
//                }
//            } catch (SQLException sqlException) {
//                sqlException.printStackTrace();
//            }
//
//        } else {
//            System.out.println("not find your student" + "\n");
//        }
//    }

    public static void addTeacherToStudent(int studentId, int teacherId) {
        Student student = StudentServices.load(studentId);
        if (student != null) {
            Teacher teacher = TeacherServices.load(teacherId);
            if (teacher != null) {
                try (Connection connection = CreateConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement("insert into relation(student_id,teacher_id) values(?,?)")) {
                    preparedStatement.setInt(1, studentId);
                    preparedStatement.setInt(2, teacherId);
                    preparedStatement.executeUpdate();
                    List<Teacher> list = student.getTeachers();
                    list.add(teacher);
                    student.setTeachers(list);
                } catch (SQLException sqlException) {
                    System.out.println("duplicate value!!" + "\n");
                }
            } else {
                System.out.println("not find your teacher" + "\n");
            }
        } else {
            System.out.println("not find your student" + "\n");
        }
    }

    public static void addStudentToTeacher(int teacherId, int studentId) {
        Teacher teacher = TeacherServices.load(teacherId);
        if (teacher != null) {
            Student student = StudentServices.load(studentId);
            if (student != null) {
                try (Connection connection = CreateConnection.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement("insert into relation(teacher_id,student_id) values(?,?)")) {
                    preparedStatement.setInt(1, teacherId);
                    preparedStatement.setInt(2, studentId);
                    preparedStatement.executeUpdate();
                    List<Student> list = teacher.getStudents();
                    list.add(student);
                    teacher.setStudents(list);
                } catch (SQLException sqlException) {
                    System.out.println("duplicate value!!" + "\n");
                }
            } else {
                System.out.println("not find your student" + "\n");
            }
        } else {
            System.out.println("not find your teacher" + "\n");
        }
    }

    public static List<Teacher> loadTeacherListOfStudent(int studentId) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select teacher_id from relation where student_id = ? ")) {
            preparedStatement.setInt(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Teacher teacher = TeacherServices.load(resultSet.getInt("teacher_id"));
                    teachers.add(teacher);
                }
                return teachers;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return teachers;
    }

    public static void showTeacherListOfStudent(int studentId) {
        List<Teacher> teachers = loadTeacherListOfStudent(studentId);
        if (!teachers.isEmpty()) {
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        } else {
            System.out.println("your teacher list is empty" + "\n");
        }
    }

    public static List<Student> loadStudentListOfTeacher(int teacherId) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select student_id from relation where teacher_id = ? ")) {
            preparedStatement.setInt(1, teacherId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Student student = StudentServices.load(resultSet.getInt("student_id"));
                    students.add(student);
                }
                return students;
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return students;
    }

    public static void showStudentListOfTeacher(int teacherId) {
        List<Student> students = loadStudentListOfTeacher(teacherId);
        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println(student);
            }
        } else {
            System.out.println("your student list is empty" + "\n");
        }
    }

    public static void deleteStudentInRelation(int student_id) throws SQLException {
        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from relation where student_id = ?")) {
            preparedStatement.setInt(1,student_id);
            preparedStatement.executeUpdate();
        }
    }

    public static void deleteTeacherInRelation(int teacher_id) throws SQLException {
        try(Connection connection = CreateConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from relation where teacher_id = ?")) {
            preparedStatement.setInt(1,teacher_id);
            preparedStatement.executeUpdate();
        }
    }
}

