import java.util.Scanner;

public class ex6_4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap thang: ");
        String month = sc.next().toLowerCase();

        System.out.print("Nhap nam: ");
        int year = sc.nextInt();

        int days = 0;

        switch (month) {
            case "1": case "jan": case "january":
                days = 31; break;
            case "2": case "feb": case "february":
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0))
                    days = 29;
                else
                    days = 28;
                break;
            case "3": case "mar": case "march":
                days = 31; break;
            case "4": case "apr": case "april":
                days = 30; break;
            case "5": case "may":
                days = 31; break;
            case "6": case "jun": case "june":
                days = 30; break;
            case "7": case "jul": case "july":
                days = 31; break;
            case "8": case "aug": case "august":
                days = 31; break;
            case "9": case "sep": case "september":
                days = 30; break;
            case "10": case "oct": case "october":
                days = 31; break;
            case "11": case "nov": case "november":
                days = 30; break;
            case "12": case "dec": case "december":
                days = 31; break;
            default:
                System.out.println("Thang khong hop le!");
            
        }

        System.out.println("So ngay: " + days);
        sc.close();
    }
}