import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        IDictionary first =  new LatinAlphabetDictionary("FirstDictionary.txt");
        IDictionary second =  new NumbersDictionary("FirstDictionary.txt");
        Scanner sc = new Scanner(System.in);
        System.out.println(InfoClass.getInfo());
        while(true) {
            String command = sc.nextLine();
            String[] arg = command.split(" ");
            //Документация
            if (arg[0].equals("Info")) {
                System.out.println(InfoClass.getInfo());
            }
            //Чтение всего первого словаря
            else if (arg[0].equals("ReadAll") && arg[1].equals("First")) {
                first.ReadAll();
            }
            //Чтение всего второго словаря
            else if (arg[0].equals("ReadAll") && arg[1].equals("Second")) {
                second.ReadAll();
            }
            //Поиск значение по ключу первого словаря
            else if (arg[0].equals("Search") && arg[1].equals("First") && arg[2] != null) {
                first.Search(arg[2]);
            }
            //Поиск значение по ключу второго словаря
            else if (arg[0].equals("Search") && arg[1].equals("Second") && arg[2] != null) {
                second.Search(arg[2]);
            }
            //Удаление значения по ключу первого словаря
            else if (arg[0].equals("Remove") && arg[1].equals("First") && arg[2] != null) {
                first.Remove(arg[2]);
            }
            else if (arg[0].equals("Remove") && args[1].equals("Second") && arg[2] != null) {
                first.Remove(arg[2]);
            }
            //Добавление записей
            else if (arg[0].equals("Add") && arg[1].equals("First") && arg[2] != null) {
                first.Add(arg[2]);
            }
            else if (arg[0].equals("Add") && arg[1].equals("Second") && arg[2] != null) {
                second.Add(arg[2]);
            }
            else if (arg[0].equals("Exit")) {
                break;
            }
        }
        System.exit(0);
    }
}
