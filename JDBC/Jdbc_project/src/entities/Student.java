package entities;


import java.util.ArrayList;
import java.util.List;

public class Student {
    private Integer id;
    private String firstname;
    private String lastname;
    private Long studentCode;
    private List<Teacher> teachers = new ArrayList<>();

    public Student() {

    }

    public Student(String firstname, String lastname, Long studentCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.studentCode = studentCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Long getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(Long studentCode) {
        this.studentCode = studentCode;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return
                "firstname: " + firstname +
                " lastname: " + lastname +
                " studentCode: " + studentCode;
    }
}
