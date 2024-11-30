import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentManager {
    private ArrayList<Student> danhSachSV = new ArrayList<>();
    private String filePath;

    public StudentManager(String filePath) {
        this.filePath = filePath;
        docDuLieuTuCSV(); // Đọc dữ liệu từ CSV khi khởi tạo
    }

    // Đọc dữ liệu từ file CSV
    public void docDuLieuTuCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean isHeader = true; // Bỏ qua dòng đầu tiên (header)
            while ((line = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String maSV = parts[0].trim();
                    String tenSV = parts[1].trim();
                    double diemTB = Double.parseDouble(parts[2].trim());
                    danhSachSV.add(new Student(maSV, tenSV, diemTB));
                }
            }
            System.out.println("Read data from CSV file successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Ghi danh sách sinh viên ra file CSV
    public void ghiDanhSachRaCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("StudentID,Studentname,DiemTB");
            bw.newLine();
            for (Student sv : danhSachSV) {
                bw.write(sv.getMaSV() + "," + sv.getTenSV() + "," + sv.getDiemTB());
                bw.newLine();
            }
            System.out.println("Save student list to CSV file successfully.");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Thêm sinh viên mới
    public void themSinhVien(Student sv) {
        danhSachSV.add(sv);
        ghiDanhSachRaCSV();
        System.out.println("Add student successfully.");
    }

    // Sửa thông tin sinh viên
    public void suaSinhVien(String maSV, String tenMoi, double diemMoi) {
        for (Student sv : danhSachSV) {
            if (sv.getMaSV().equals(maSV)) {
                sv.setTenSV(tenMoi);
                sv.setDiemTB(diemMoi);
                ghiDanhSachRaCSV();
                System.out.println("Edit student successfully.");
                return;
            }
        }
        System.out.println("No students found for Edit");
    }

    // Xóa sinh viên theo mã
    public void xoaSinhVien(String maSV) {
        if (danhSachSV.removeIf(sv -> sv.getMaSV().equals(maSV))) {
            ghiDanhSachRaCSV();
            System.out.println("Delete student successfully.");
        } else {
            System.out.println("No students found for Delete");
        }
    }

    // Hiển thị danh sách sinh viên
    public void hienThiSinhVien() {
        if (danhSachSV.isEmpty()) {
            System.out.println("Empty student list");
        } else {
            for (Student sv : danhSachSV) {
                System.out.println(sv);
            }
        }
    }

    // Tìm kiếm sinh viên theo mã
    public Student timKiemSinhVienTheoMa(String maSV) {
        for (Student sv : danhSachSV) {
            if (sv.getMaSV().equalsIgnoreCase(maSV)) {
                return sv;
            }
        }
        return null; // Không tìm thấy
    }
    public void sapXepSinhVienTheoDiem(boolean tangDan) {
    if (tangDan) {
        danhSachSV.sort(Comparator.comparingDouble(Student::getDiemTB)); // Tăng dần
    } else {
        danhSachSV.sort(Comparator.comparingDouble(Student::getDiemTB).reversed()); // Giảm dần
    }
    System.out.println("Sort students by average score successfully.");
}

}