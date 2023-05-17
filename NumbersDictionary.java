import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumbersDictionary implements IDictionary {
    private HashMap<String, String> _map;
    private String fileName;

    public NumbersDictionary(String fileName) throws IOException {
        this.fileName = fileName;
        _map = new HashMap<>();
        loadFile();
    }

    private boolean isNumeric(String s){
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean countCheck(String value) {
        return value.length() <= 5;
    }

    private boolean ValidityСheck(String value) {
        return isNumeric(value) && countCheck(value);
    }

    public void loadFile() throws IOException {
        _map.clear();
        Path path = Paths.get(fileName);
        List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
        if(list.isEmpty()) {
            throw new IOException("Такого файла нет");
        }
        else {
            for (String s : list) {
                s = s.replace("\n", "").replace("\r", "");
                String[] array = s.split("-");
                if (ValidityСheck(array[0]) && ValidityСheck(array[1]))
                    _map.put(array[0], array[1]);
            }
            list.clear();
            System.out.println("___Файл загружен___");
        }
    }

    public void ReadAll(){
        for(Map.Entry<String, String> e : _map.entrySet()) {
            System.out.println(e.getKey() + "-" + e.getValue());
        }
    }

    public void Remove(String key) throws IOException {
        if(_map.containsKey(key)) {
            _map.remove(key);
            String context = "";
            FileWriter fw = new FileWriter(fileName, false);
            for (Map.Entry<String, String> e : _map.entrySet()) {
                if (context.equals(""))
                    context += e.getKey() + "-" + e.getValue();
                else
                    context += "\n" + e.getKey() + "-" + e.getValue();
            }
            fw.write(context + "\n");
            fw.close();
            System.out.println("___Запись по ключу " + key + " удалена!___");
        }
        else
            System.out.println("___Записи по ключу " + key + " нет в словаре!___");
    }

    public void Search(String key){
        System.out.println(_map.get(key));
    }

    //Проверка на повтор ключа
    private boolean KeyCheck(String value){
        String[] array = value.split("-");
        if(_map.containsKey(array[0]))
            return false;
        else
            return true;
    }

    public void Add(String value) throws IOException {
        String[] s = value.split("-");

        if(value.contains("-") && KeyCheck(value) && isNumeric(s[0]) && isNumeric(s[1])) {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(value + "\n");
            System.out.println("___Запись завершена!___");
            fw.close();
            loadFile();
        }
        else {
            throw new IOException("неверный формат записи");
        }
    }
}
