package data;
import bussiness.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import java.sql.Date;
public class StudentDAO {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "SELECT * FROM Student";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlString);
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("studentName");
                int gender = rs.getInt("gender");
                String genderString = (gender == 0) ? "Male" : "Female";
                String dobString = rs.getString("DOB");
                Student student = new Student(id, name, genderString, dobString);
                students.add(student);
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e);
        } finally {
            DBUtils.closeResultSet(rs);
            DBUtils.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return students;
    }
    public static void addStudent(Student student) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "INSERT INTO Student (studentName, gender, DOB) VALUES (?, ?, ?)";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGender().equalsIgnoreCase("Male") ? 0 : 1);
            ps.setDate(3, new Date(student.getDOBForDB().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtils.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static void deleteStudent(int id) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "DELETE FROM Student WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtils.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static Student getStudent(int id) {
        Student student = null;
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "SELECT * FROM Student WHERE id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("studentName");
                int gender = rs.getInt("gender");
                String genderString = (gender == 0) ? "Male" : "Female";
                String dobString = rs.getString("DOB");
                student = new Student(id, name, genderString, dobString);
            }
        } catch (SQLException | ParseException e) {
            System.out.println(e);
        } finally {
            DBUtils.closePreparedStatement(ps);
            DBUtils.closeResultSet(rs);
            pool.freeConnection(connection);
        }
        return student;
    }
    public static void updateStudent(Student student) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        String sqlString = "UPDATE Student SET studentName=?, gender=?, DOB=? WHERE id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sqlString);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getGender().equalsIgnoreCase("Male") ? 0 : 1);
            ps.setDate(3, new Date(student.getDOBForDB().getTime()));
            ps.setInt(4, student.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            DBUtils.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
