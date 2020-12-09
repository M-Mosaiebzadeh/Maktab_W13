package entities;


import java.util.ArrayList;
import java.util.List;

public class Teacher {
    private Integer id;
    private String firstname;
    private String lastname;
    private Long personalCode;
    private List<Student> students = new ArrayList<>();

    public Teacher() {

    }

    public Teacher(String firstname, String lastname, Long personalCode) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.personalCode = personalCode;
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

    public Long getPersonalCode() {
        return personalCode;
    }

    public void setPersonalCode(Long personalCode) {
        this.personalCode = personalCode;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return
                "firstname: " + firstname +
                " lastname: " + lastname +
                " personalCode: " + personalCode;
    }
}
