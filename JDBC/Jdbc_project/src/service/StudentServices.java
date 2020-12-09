package service;

import entities.Student;
import factory.CreateConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServices {

    public static void add(Student student) {
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into student(firstname,lastname,studentCode) values(?,?,?)");) {

            preparedStatement.setString(1, student.getFirstname());
            preparedStatement.setString(2, student.getLastname());
            preparedStatement.setLong(3, student.getStudentCode());
            preparedStatement.executeUpdate();
            System.out.println(student.toString() + " add to DB" + "\n");

        } catch (SQLException sqlException) {
            System.out.println("cant add this student"+ "\n");
        }
    }

    public static void delete(int id) {
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from student where student_id = ?")) {
                RelationServices.deleteStudentInRelation(id);

            preparedStatement.setInt(1, id);
            Integer happen = preparedStatement.executeUpdate();
            try {
                if (happen == 1)
                    System.out.println("delete successfully");
                else
                    System.out.println("delete not successfully");

            }catch (NullPointerException exception) {
                exception.printStackTrace();
            }
            System.out.println();

        }catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static Student load(int id) {
        Student student = null;
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student where student_id = ?");) {
            preparedStatement.setInt(1, id);
            try {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    student = new Student();
                    student.setId(resultSet.getInt("student_id"));
                    student.setFirstname(resultSet.getString("firstname"));
                    student.setLastname(resultSet.getString("lastname"));
                    student.setStudentCode(resultSet.getLong("studentCode"));
                }
                return student;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            System.out.println("your student not exist"+ "\n" );
        }

        return student;
    }

    public static void editFirstname(int id, String firstname) {
        Student student = load(id);
        if (student != null) {
            String oldName = student.getFirstname();
            try (Connection connection = CreateConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("update student set firstname = ? where student_id = ?");) {
                preparedStatement.setString(1, firstname);
                preparedStatement.setInt(2, id);
                int happen = preparedStatement.executeUpdate();
                if(happen == 1) {
                    System.out.println(oldName + " is changed to: " + firstname + "\n");
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } else {
            System.out.println("your student doesn't exist" + "\n");
        }
    }

    public static void editLastname(int id, String lastname) {
        Student student = load(id);
        if (student != null) {
            String oldLastname = student.getLastname();
            try (Connection connection = CreateConnection.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement("update student set lastname = ? where student_id = ?");) {
                preparedStatement.setString(1, lastname);
                preparedStatement.setInt(2, id);
                int happen = preparedStatement.executeUpdate();
                if(happen == 1) {
                    System.out.println(oldLastname + " is changed to: " + lastname + "\n");
                }

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } else {
            System.out.println("your student doesn't exist" + "\n");
        }
    }

    public static void editStudentCode(int id, long studentCode) {
        Student student = load(id);
        if (student != null) {
            long oldStudentCode = student.getStudentCode();
            try (Connection connection = CreateConnection.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement("update student set studentCode = ? where student_id = ?");) {
                preparedStatement.setLong(1, studentCode);
                preparedStatement.setInt(2, id);
                int happen = preparedStatement.executeUpdate();
                if(happen == 1) {
                    System.out.println(oldStudentCode + " is changed to: " + studentCode + "\n");
                }

            } catch (SQLException sqlException) {
                System.out.println("duplicate value!!" +  "\n");
            }
        } else {
            System.out.println("your student doesn't exist" + "\n");
        }
    }

    public static List<Student> loadAll() {
        List<Student> students = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student");
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                Student student = new Student();
                student.setFirstname(resultSet.getString("firstname"));
                student.setLastname(resultSet.getString("lastname"));
                student.setStudentCode(resultSet.getLong("studentCode"));
                students.add(student);
            }
            return students;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return students;
    }

    public static void showAll() {
        List<Student> students = loadAll();
        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println(student.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your student list is empty" + "\n");
        }
    }

    public static void showWithId(int id) {
        Student student = load(id);
        if (student != null) {
            System.out.println(student.toString());
            System.out.println("----end----");
        }else {
            System.out.println("your student not find" + "\n");
        }
    }

    public static List<Student> loadWithFirstname(String firstname) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student where firstname = ?")) {

            preparedStatement.setString(1, firstname);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("student_id"));
                    student.setFirstname(resultSet.getString("firstname"));
                    student.setLastname(resultSet.getString("lastname"));
                    student.setStudentCode(resultSet.getLong("studentCode"));
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

    public static void showWithFirstname(String firstname) {
        List<Student> students = loadWithFirstname(firstname);
        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println(student.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your student not find" + "\n");
        }
    }

    public static List<Student> loadWithLastname(String lastname) {
        List<Student> students = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student where lastname = ?")) {

            preparedStatement.setString(1, lastname);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt("student_id"));
                    student.setFirstname(resultSet.getString("firstname"));
                    student.setLastname(resultSet.getString("lastname"));
                    student.setStudentCode(resultSet.getLong("studentCode"));
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

    public static void showWithLastname(String lastname) {
        List<Student> students = loadWithLastname(lastname);
        if (!students.isEmpty()) {
            for (Student student : students) {
                System.out.println(student.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your student not find" + "\n");
        }
    }

    public static Student loadWitStudentCode(long studentCode) {
        Student student = new Student();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from student where studentCode = ?")) {

            preparedStatement.setLong(1, studentCode);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    student.setId(resultSet.getInt("student_id"));
                    student.setFirstname(resultSet.getString("firstname"));
                    student.setLastname(resultSet.getString("lastname"));
                    student.setStudentCode(resultSet.getLong("studentCode"));
                }
                return student;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return student;
    }

    public static void showWithStudentCode(long studentCode) {
        Student student = loadWitStudentCode(studentCode);
        if (student.getStudentCode() != null) {
            System.out.println(student.toString());
            System.out.println("----end----");
        } else {
            System.out.println("your student not find" + "\n");
        }
    }

}
