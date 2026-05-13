package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class GarbageCreator {
    public static void main(String[] args) {
        String filename = "test.txt"; // Cập nhật đường dẫn nếu file không trong thư mục dự án
        try {
            // Kiểm tra kích thước tệp
            long fileSize = Files.size(Paths.get(filename));
            System.out.println("File size: " + fileSize + " bytes");

            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            long start = System.currentTimeMillis();
            String s = "";
            for (byte b : inputBytes) {
                s += (char)b; // Tạo garbage do nối chuỗi bằng +
            }
            System.out.println("Time for GarbageCreator: " + (System.currentTimeMillis() - start) + " ms");
            System.out.println("Length of resulting string: " + s.length());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Out of memory: " + e.getMessage());
        }
    }
}
