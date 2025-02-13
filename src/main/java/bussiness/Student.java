package bussiness;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rio
 */
public class Student {

    private int id;
    private String name;
    private String gender;
    private Date DOB;

    public Student() {
    }

    public Student(int id, String name, String gender, String DOB) throws ParseException {
        this.id = id;
        this.name = name;
        this.gender = gender;
        setDOB(DOB);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getDOBForDB() {
        return DOB;
    }

    public String getDOB() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(DOB);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDOB(String DOBString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.DOB = sdf.parse(DOBString);
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", DOB=" + DOB + '}';
    }
}
