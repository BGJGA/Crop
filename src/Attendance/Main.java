package Attendance;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final AttendanceService service = new AttendanceService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) { 
        String adminUsername = null;
        String adminPassword = null;
        
        try {
            adminUsername = Config.getProperty("admin.username");
            adminPassword = Config.getProperty("admin.password");
        } catch (Exception exception) {
            logger.error("Error loading configuration properties", exception);
            System.out.println("Failed to load configuration. Exiting application.");
            return; 
        }

        while (true) {
            try {
                System.out.println("=== Attendance Management System ===");
                System.out.println("1. Login as Admin");
                System.out.println("2. Login as Student");
                System.out.println("3. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 1) {
                    if (adminLogin(adminUsername, adminPassword)) {
                        adminMenu();
                    }
                } else if (choice == 2) {
                    studentLogin();
                } else if (choice == 3) {
                    break;
                }
            } catch (Exception exception) {
                logger.error("Unexpected error", exception);
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private static boolean adminLogin(String username, String password) {
        try {
            System.out.print("Enter admin username: ");
            String enteredUsername = scanner.nextLine();
            System.out.print("Enter admin password: ");
            String enteredPassword = scanner.nextLine();

            if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                logger.info("Admin logged in successfully");
                System.out.println("Admin logged in successfully.");
                return true;
            } else {
                logger.error("Invalid admin login attempt");
                System.out.println("Invalid admin credentials.");
                return false;
            }
        } catch (Exception exception) {
            logger.error("Error during admin login", exception);
            System.out.println("Error occurred during login.");
            return false;
        }
    }

    private static void adminMenu() {
        while (true) {
            try {
                System.out.println("Admin Menu:");
                System.out.println("1. Add Student");
                System.out.println("2. Remove Student");
                System.out.println("3. View All Attendance Records");
                System.out.println("4. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    addStudent();
                } else if (choice == 2) {
                    removeStudent();
                } else if (choice == 3) {
                    service.generateAttendanceReport();
                } else if (choice == 4) {
                    break;
                }
            } catch (Exception exception) {
                logger.error("Error in admin menu", exception);
                System.out.println("An error occurred while processing your request.");
            }
        }
    }

    private static void addStudent() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String studentName = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (service.addStudent(studentId, studentName, password)) {
                System.out.println("Student added successfully.");
            } else {
                System.out.println("Student with this ID already exists.");
            }
        } catch (Exception exception) {
            logger.error("Error adding student", exception);
            System.out.println("An error occurred while adding the student.");
        }
    }

    private static void removeStudent() {
        try {
            System.out.print("Enter Student ID to remove: ");
            String studentId = scanner.nextLine();

            if (service.removeStudent(studentId)) {
                System.out.println("Student removed successfully.");
            } else {
                System.out.println("Student ID not found.");
            }
        } catch (Exception exception) {
            logger.error("Error removing student", exception);
            System.out.println("An error occurred while removing the student.");
        }
    }

    private static void studentLogin() {
        try {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Password: ");
            String password = scanner.nextLine();

            if (service.authenticateStudent(studentId, password)) {
                studentMenu(studentId);
                logger.info("Student {} logged in successfully.", studentId);
            } else {
                logger.error("Invalid student login attempt");
                System.out.println("Invalid credentials.");
            }
        } catch (Exception exception) {
            logger.error("Error during student login", exception);
            System.out.println("Invalid Credentials.");
        }
    }

    private static void studentMenu(String studentId) {
        while (true) {
            try {
                System.out.println("Student Menu:");
                System.out.println("1. Mark Attendance");
                System.out.println("2. View Attendance");
                System.out.println("3. Logout");

                int choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == 1) {
                    service.markAttendance(studentId);
                    System.out.println("Attendance marked for today.");
                } else if (choice == 2) {
                    viewAttendance(studentId);
                } else if (choice == 3) {
                    break;
                }
            } catch (Exception exception) {
                logger.error("Error in student menu", exception);
                System.out.println("An error occurred. Please try again.");
            }
        }
    }

    private static void viewAttendance(String studentId) {
        try {
            List<LocalDate> attendanceList = service.getAttendance(studentId);
            System.out.println("Attendance for student " + studentId + ":");
            for (LocalDate date : attendanceList) {
                System.out.println(date);
            }
            System.out.println("Total days attended: " + attendanceList.size());
        } catch (Exception exception) {
            logger.error("Error viewing attendance", exception);
            System.out.println("An error occurred while fetching attendance.");
        }
    }
}
