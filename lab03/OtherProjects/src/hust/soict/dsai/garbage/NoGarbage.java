package hust.soict.dsai.garbage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NoGarbage {
    public static void main(String[] args) {
        String filename = "test.txt"; // Thay bằng đường dẫn tới tệp lớn của bạn
        try {
            byte[] inputBytes = Files.readAllBytes(Paths.get(filename));
            long start = System.currentTimeMillis();
            StringBuilder sb = new StringBuilder();
            for (byte b : inputBytes) {
                sb.append((char)b);
            }
            String s = sb.toString();
            System.out.println("Time for NoGarbage: " + (System.currentTimeMillis() - start) + " ms");
            System.out.println("Length of resulting string: " + s.length());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }
}