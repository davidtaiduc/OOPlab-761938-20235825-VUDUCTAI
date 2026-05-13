package hust.soict.dsai.garbage;

import java.util.Random;

public class ConcatenationInLoops {
    public static void main(String[] args) {
        // Test with String
        Random r = new Random(123);
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 65536; i++) {
            s += r.nextInt(2);
        }
        System.out.println("Time for String: " + (System.currentTimeMillis() - start) + " ms");

        // Test with StringBuilder
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 65536; i++) {
            sb.append(r.nextInt(2));
        }
        s = sb.toString();
        System.out.println("Time for StringBuilder: " + (System.currentTimeMillis() - start) + " ms");

        // Test with StringBuffer
        r = new Random(123);
        start = System.currentTimeMillis();
        StringBuffer sbf = new StringBuffer();
        for (int i = 0; i < 65536; i++) {
            sbf.append(r.nextInt(2));
        }
        s = sbf.toString();
        System.out.println("Time for StringBuffer: " + (System.currentTimeMillis() - start) + " ms");
    }
}
