import java.util.Scanner;
public class ex2_5 {

    public static void sum(double a,double b){
        double x=a+b;
        System.out.println("sum: "+x);
    }
    public static void difference(double a,double b){
        double x=a-b;
        System.out.println("diffenence: "+x);
    }
    public static void product(double a,double b){
        double x=a*b;
        System.out.println("product: "+x);
    }
    public static void quotient(double a,double b){
        double x=a/b;
        System.out.println("quotient: "+x);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("nhap so a: ");
        double a= sc.nextDouble();
        System.out.println("nhap so b");
        double b= sc.nextDouble();
        sum(a,b);difference(a, b);product(a, b);quotient(a, b); 
        sc.close();
    }
}
    
