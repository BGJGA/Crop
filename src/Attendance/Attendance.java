package Attendance;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Attendance {
    private HashMap<String, List<LocalDate>> attendanceRecords = new HashMap<>();

    public void markAttendance(String studentId) {
        LocalDate currentDate = LocalDate.now();
        if (!attendanceRecords.containsKey(studentId)) {
            attendanceRecords.put(studentId, new ArrayList<>());
        }

        List<LocalDate> dates = attendanceRecords.get(studentId);
        if (!dates.contains(currentDate)) {
            dates.add(currentDate);
        }
    }

    public List<LocalDate> getAttendance(String studentId) {
        return attendanceRecords.getOrDefault(studentId, new ArrayList<>());
    }
}
