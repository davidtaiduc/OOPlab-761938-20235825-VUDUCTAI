import java.util.*;
public class ex6_5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so phan tu: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Nhap mang:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int sum = 0;
        for (int x : arr) {
            sum += x;
        }

        double avg = (double) sum / n;

        Arrays.sort(arr);

        System.out.println("Mang sau khi sap xep: " + Arrays.toString(arr));
        System.out.println("Tong: " + sum);
        System.out.println("Trung binh: " + avg);

        sc.close();
    }
}

