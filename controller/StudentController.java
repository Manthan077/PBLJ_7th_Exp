package controller;

import model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private static final String url = "jdbc:mysql://localhost:3306/companyDB";
    private static final String user = "Root";
    private static final String password = "Abc123";

    private Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(url, user, password);
    }

    public boolean addStudent(Student s) {
        String sql = "INSERT INTO Student(StudentID, Name, Department, Marks) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, s.getStudentID());
            ps.setString(2, s.getName());
            ps.setString(3, s.getDepartment());
            ps.setInt(4, s.getMarks());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Student> getAllStudents() {
        String sql = "SELECT * FROM Student";
        List<Student> students = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("StudentID"),
                        rs.getString("Name"),
                        rs.getString("Department"),
                        rs.getInt("Marks")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public boolean updateStudent(Student s) {
        String sql = "UPDATE Student SET Name=?, Department=?, Marks=? WHERE StudentID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getDepartment());
            ps.setInt(3, s.getMarks());
            ps.setInt(4, s.getStudentID());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(int studentID) {
        String sql = "DELETE FROM Student WHERE StudentID=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, studentID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
