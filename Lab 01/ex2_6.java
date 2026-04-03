import java.util.Scanner;
public class ex2_6 {
    //bac nhat
    public static void nhapdulieucuahambacnhat(Scanner sc){
        System.out.println("nhap a");
        double a= sc.nextDouble();
        System.out.println("nhap b");
        double b= sc.nextDouble();
        bacnhat(a,b);
    }
    public static void bacnhat(double a,double b){
        if (a==0){
            if(b==0) System.out.println(" vo so nghiem"); 
            else     System.out.println("vo nghiem");
        } 
        else {
            double x=-b/a;
            System.out.println("x= "+x);
        }
    }
    ///bac nhat hai an
    public static void bacnhathaian(Scanner sc){
        System.out.print("Nhap a11: ");
        double a11 = sc.nextDouble();
        System.out.print("Nhap a12: ");
        double a12 = sc.nextDouble();
        System.out.print("Nhap b1: ");
        double b1 = sc.nextDouble();
        System.out.print("Nhap a21: ");
        double a21 = sc.nextDouble();
        System.out.print("Nhap a22: ");
        double a22 = sc.nextDouble();
        System.out.print("Nhap b2: ");
        double b2 = sc.nextDouble();

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;
     if (D != 0) {
            double x1 = D1 / D;
            double x2 = D2 / D;
            System.out.println("x1 = " + x1 + ", x2 = " + x2);
        } else {
            if (D1 == 0 && D2 == 0)
                System.out.println("Vo so nghiem");
            else
                System.out.println("Vo nghiem");
        }
    }

    public static void bachai(Scanner sc){
        System.out.print("Nhap a: ");
        double a = sc.nextDouble();
        System.out.print("Nhap b: ");
        double b = sc.nextDouble();
        System.out.print("Nhap c: ");
        double c = sc.nextDouble();

        if (a == 0) {
            bacnhat(b, c); 
        return;
        } else {
            double delta = b * b - 4 * a * c;

            if (delta < 0) {
                System.out.println("Vo nghiem");
            } else if (delta == 0) {
                double x = -b / (2 * a);
                System.out.println("Nghiem kep x = " + x);
            } else {
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("x1 = " + x1 + ", x2 = " + x2);
            }
        }
    }
    /// menu
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== MENU =====");
            System.out.println("1. Phuong trinh bac nhat");
            System.out.println("2. He phuong trinh 2 an");
            System.out.println("3. Phuong trinh bac hai");
            System.out.println("0. Thoat");
            System.out.print("Chon: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    nhapdulieucuahambacnhat(sc);
                    break;
                case 2:
                    bacnhathaian(sc);
                    break;
                case 3:
                    bachai(sc);
                    break;
                case 0:
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Lua chon khong hop le");
            }
        }
    }
}
