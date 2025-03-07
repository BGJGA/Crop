package Attendance;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AttendanceService {
	private static final Logger logger = LogManager.getLogger(AttendanceService.class);

    private Map<String, String> studentCredentials = new HashMap<>();
    private Map<String, Student> students = new HashMap<>();
    private Attendance attendance = new Attendance();

    public boolean addStudent(String studentId, String name, String password) {
        if (students.containsKey(studentId)) {
        	logger.info("Admin added new student with ID: {} and Name: {}", studentId, name);
            return false;
        }
        Student student = new Student(studentId, name, password);
        students.put(studentId, student);
        studentCredentials.put(studentId, password);
        return true;
    }

    public boolean removeStudent(String studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            studentCredentials.remove(studentId);
            logger.info("Admin removed student with ID: {}", studentId);
            return true;
        }
        logger.error("Attempt to remove non-existent student with ID: {}", studentId); return false;
    }

    public Student getStudent(String studentId) {
        return students.get(studentId);
    }

    public boolean authenticateStudent(String studentId, String password) {
        return studentCredentials.get(studentId).equals(password);
    }

    public void markAttendance(String studentId) {
        attendance.markAttendance(studentId);
        logger.info("Marked attendance for student ID: " + studentId);
    }

    public List<LocalDate> getAttendance(String studentId) {
        return attendance.getAttendance(studentId);
        
    }

    public Map<String, Student> getAllStudents() {
        return students;
    }

    public void generateAttendanceReport() {
        for (Map.Entry<String, Student> entry : students.entrySet()) {
            String studentId = entry.getKey();
            List<LocalDate> dates = attendance.getAttendance(studentId);
            System.out.println("Student: " + entry.getValue().getName());
            System.out.println("Attendance: " + dates.size() + " days attended.");
            System.out.println("Total days: " + dates.size());
            System.out.println();
        }
    }
}
