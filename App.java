
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        String filePath = "student.csv";
        StudentManager qlsv = new StudentManager(filePath);
        Scanner scanner = new Scanner(System.in);

        int choice;
        do {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Sort Students by Average Score");
            System.out.println("0. Exit");
            System.out.print("Select function: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    String maSV = scanner.nextLine();
                    System.out.print("Enter student name: ");
                    String tenSV = scanner.nextLine();
                    double diemTB = 0;

                    while (true) {
                        try {
                            System.out.print("Enter average score: ");
                            diemTB = Double.parseDouble(scanner.nextLine());
                            if (diemTB < 0 || diemTB > 10) {
                                throw new IllegalArgumentException("The average score must be in the range of 0 to 10.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    
                    qlsv.themSinhVien(new Student(maSV, tenSV, diemTB));
                    break;

                case 2:
                    System.out.print("Enter the student ID to edit: ");
                    maSV = scanner.nextLine();
                    System.out.print("Enter new name: ");
                    tenSV = scanner.nextLine();

                    while (true) {
                        try {
                            System.out.print("Enter new average score: ");
                            diemTB = Double.parseDouble(scanner.nextLine());
                            if (diemTB < 0 || diemTB > 10) {
                                throw new IllegalArgumentException("The average score must be in the range of 0 to 10.");
                            }
                            break;
                        } catch (Exception e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    qlsv.suaSinhVien(maSV, tenSV, diemTB);
                    break;

                case 3:
                    System.out.print("Enter the student ID to Delete: ");
                    maSV = scanner.nextLine();
                    qlsv.xoaSinhVien(maSV);
                    break;

                case 4:
                    qlsv.hienThiSinhVien();
                    break;

                case 5:
                    System.out.print("Enter the student ID to Search: ");
                    maSV = scanner.nextLine();
                    Student sinhVienTimThay = qlsv.timKiemSinhVienTheoMa(maSV);
                    if (sinhVienTimThay != null) {
                        System.out.println("Student Information:");
                        System.out.println(sinhVienTimThay);
                    } else {
                        System.out.println("No student found with ID " + maSV);
                    }
                    break;

                case 6:
                    System.out.print("Sort in ascending order? (true/false): ");
                    boolean tangDan = Boolean.parseBoolean(scanner.nextLine());
                    qlsv.sapXepSinhVienTheoDiem(tangDan);
                    qlsv.hienThiSinhVien();
                    break;
                case 0:
                    System.out.println("Exit.");
                    break;

                default:
                    System.out.println("Invalid selection.");
            }
        } while (choice != 0);
    }
}
