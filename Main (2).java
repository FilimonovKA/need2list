import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Invoker invoker = Invoker.getInstance();
        while (true) {
            System.out.println("Введите команду: ");
            invoker.setCommand(sc.nextLine());
            invoker.execute();
        }
    }
}
