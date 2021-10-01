import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        System.out.println("Введите число с билета ");
        Scanner scanner = new Scanner(System.in);
        String n = scanner.nextLine();
        System.out.println(luckyTicket(n));
    }

    public static boolean luckyTicket(String n) {
        char [] arr = n.toCharArray();
        return ((int) arr[0] + (int) arr[1] +(int) arr[2]==(int) arr[3]+(int) arr[4]+(int) arr[5]);
    }
}
