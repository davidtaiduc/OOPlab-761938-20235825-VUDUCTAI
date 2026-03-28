import java.util.Scanner;

public class ex6_3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap n: ");
        int n = sc.nextInt();
        int k=0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n*2+1; j++) {
                if (j<=k+n && j>=n-k){
                    System.out.print("*");
                }else System.out.print(" ");
            }
            k++;
            System.out.println();
        }

        sc.close();
    }
}

