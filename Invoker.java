import java.nio.file.AccessDeniedException;
import java.util.Scanner;

public class Invoker implements ICommand {
    private String command;
    private static Invoker instance;
    private Dictionary receiver;
    private Factory factory;
    private Scanner sc;

    private Invoker(){
        sc = new Scanner(System.in);
        factory = new Factory();
    }

    private String input() {
        return sc.nextLine();
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public  void execute() {
        try {
            switch (command) {
                case "openF" -> {
                    System.out.println("Введите название файла");
                    receiver = factory.createLatinDict(input());
                }
                case "openS" -> {
                    System.out.println("Введите название файла");
                    receiver = factory.createNumbersDic(input());
                }
                case "readAll" -> receiver.ReadAll();
                case "search" -> {
                    System.out.println("Введите ключ: ");
                    receiver.Search(input());
                }
                case "remove" -> {
                    System.out.println("Введите ключ: ");
                    receiver.Remove(input());
                }
                case "add" -> {
                    System.out.println("Введите ключ-значение: ");
                    receiver.Add(input());
                }
                case "exit" -> System.exit(0);
                default -> System.out.println("Не верная команда!");
            }
        }
        catch (AccessDeniedException ade) {
            System.out.println("Файл с таким названием не найден!");
        }
        catch (NullPointerException npe) {
            System.out.println("Словарь не выбран!");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public static Invoker getInstance() {
        if(instance == null)
            instance = new Invoker();
        return instance;
    }
}
