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

public class TeacherServices {

    public static void add(Teacher teacher) {
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("insert into teacher(firstname,lastname,personalCode) values(?,?,?)");) {

            preparedStatement.setString(1, teacher.getFirstname());
            preparedStatement.setString(2, teacher.getLastname());
            preparedStatement.setLong(3, teacher.getPersonalCode());
            preparedStatement.executeUpdate();
            System.out.println(teacher.toString() + " add to DB");

        } catch (SQLException sqlException) {
            System.out.println("cant add this teacher" + "\n");
        }
    }

    public static void delete(int id) {

        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("delete from teacher where teacher_id = ?")) {
                RelationServices.deleteTeacherInRelation(id);

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

    public static Teacher load(int id) {
        Teacher teacher = null;
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where teacher_id = ?");) {
            preparedStatement.setInt(1, id);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    teacher = new Teacher();
                    teacher.setId(resultSet.getInt("teacher_id"));
                    teacher.setFirstname(resultSet.getString("firstname"));
                    teacher.setLastname(resultSet.getString("lastname"));
                    teacher.setPersonalCode(resultSet.getLong("personalCode"));
                }
                return teacher;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            System.out.println("your teacher not exist"+ "\n" );
        }

        return teacher;
    }

    public static void editFirstname(int id, String firstname) {
        Teacher teacher = load(id);
        if (teacher != null) {
            String oldName = teacher.getFirstname();
            try (Connection connection = CreateConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement("update teacher set firstname = ? where teacher_id = ?");) {
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
            System.out.println("your teacher doesn't exist" + "\n");
        }
    }

    public static void editLastname(int id, String lastname) {
        Teacher teacher = load(id);
        if (teacher != null) {
            String oldLastname = teacher.getLastname();
            try (Connection connection = CreateConnection.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement("update teacher set lastname = ? where teacher_id = ?");) {
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
            System.out.println("your teacher doesn't exist" + "\n");
        }
    }

    public static void editPersonalCode(int id, long personalCode) {
        Teacher teacher = load(id);
        if (teacher != null) {
            long oldStudentCode = teacher.getPersonalCode();
            try (Connection connection = CreateConnection.getConnection();

                 PreparedStatement preparedStatement = connection.prepareStatement("update teacher set personalCode = ? where teacher_id = ?");) {
                preparedStatement.setLong(1, personalCode);
                preparedStatement.setInt(2, id);
                int happen = preparedStatement.executeUpdate();
                if(happen == 1) {
                    System.out.println(oldStudentCode + " is changed to: " + personalCode + "\n");
                }

            } catch (SQLException sqlException) {
                System.out.println("duplicate value!!" +  "\n");
            }
        } else {
            System.out.println("your teacher doesn't exist" + "\n");
        }
    }

    public static List<Teacher> loadAll() {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher");
             ResultSet resultSet = preparedStatement.executeQuery();) {

            while (resultSet.next()) {
                Teacher teacher = new Teacher();
                teacher.setFirstname(resultSet.getString("firstname"));
                teacher.setLastname(resultSet.getString("lastname"));
                teacher.setPersonalCode(resultSet.getLong("personalCode"));
                teachers.add(teacher);
            }
            return teachers;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return teachers;
    }

    public static void showAll() {
        List<Teacher> teachers = loadAll();
        if (!teachers.isEmpty()) {
            for (Teacher teacher : teachers) {
                System.out.println(teacher.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your teacher list is empty" + "\n");
        }
    }

    public static void showWithId(int id) {
        Teacher teacher = load(id);
        if (teacher != null) {
            System.out.println(teacher.toString());
            System.out.println("----end----");
        }else {
            System.out.println("your teacher not find" + "\n");
        }
    }

    public static List<Teacher> loadWithFirstname(String firstname) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where firstname = ?")) {

            preparedStatement.setString(1, firstname);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet.getInt("teacher_id"));
                    teacher.setFirstname(resultSet.getString("firstname"));
                    teacher.setLastname(resultSet.getString("lastname"));
                    teacher.setPersonalCode(resultSet.getLong("personalCode"));
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

    public static void showWithFirstname(String firstname) {
        List<Teacher> teachers = loadWithFirstname(firstname);
        if (!teachers.isEmpty()) {
            for (Teacher teacher : teachers) {
                System.out.println(teacher.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your teacher not find" + "\n");
        }
    }

    public static List<Teacher> loadWithLastname(String lastname) {
        List<Teacher> teachers = new ArrayList<>();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where lastname = ?")) {

            preparedStatement.setString(1, lastname);
            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(resultSet.getInt("teacher_id"));
                    teacher.setFirstname(resultSet.getString("firstname"));
                    teacher.setLastname(resultSet.getString("lastname"));
                    teacher.setPersonalCode(resultSet.getLong("personalCode"));
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

    public static void showWithLastname(String lastname) {
        List<Teacher> teachers = loadWithLastname(lastname);
        if (!teachers.isEmpty()) {
            for (Teacher teacher : teachers) {
                System.out.println(teacher.toString());
            }
            System.out.println("----end----");
        } else {
            System.out.println("your teacher not find" + "\n");
        }
    }

    public static Teacher loadWitPersonalCode(long personalCode) {
        Teacher teacher = new Teacher();
        try (Connection connection = CreateConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("select * from teacher where personalCode = ?")) {

            preparedStatement.setLong(1, personalCode);

            try(ResultSet resultSet = preparedStatement.executeQuery();) {

                while (resultSet.next()) {
                    teacher.setId(resultSet.getInt("teacher_id"));
                    teacher.setFirstname(resultSet.getString("firstname"));
                    teacher.setLastname(resultSet.getString("lastname"));
                    teacher.setPersonalCode(resultSet.getLong("personalCode"));
                }
                return teacher;

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return teacher;
    }

    public static void showWithStudentCode(long personalCode) {
        Teacher teacher = loadWitPersonalCode(personalCode);
        if (teacher.getPersonalCode() != null) {
            System.out.println(teacher.toString());
            System.out.println("----end----");
        } else {
            System.out.println("your teacher not find" + "\n");
        }
    }

}
