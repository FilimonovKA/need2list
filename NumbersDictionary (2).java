import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NumbersDictionary extends Dictionary {
    private HashMap<String, String> _map;
    private List<String> list;
    private String fileName;

    public NumbersDictionary(String fileName) throws IOException {
        this.fileName = fileName;
        _map = new HashMap<>();
        loadFile();
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
        list = Files.readAllLines(path, StandardCharsets.UTF_8);
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
        }
    }

    public void ReadAll(){
        for(Map.Entry<String, String> e : _map.entrySet()) {
            System.out.println(e.getKey() + "-" + e.getValue());
        }
    }

    public void Remove(String key) throws IOException {
        if(_map.containsKey(key)) {
            String value = _map.get(key);
            list.remove(key + "-" + value);
            _map.remove(key);
            FileWriter fw = new FileWriter(fileName, false);
            for (String arg : list) {
                fw.write(arg + "\n");
            }
            fw.close();
            System.out.println("Запись " + value + " по ключу " + key + " удалена!");
        }
        else
            System.out.println("Записи по ключу " + key + " нет в словаре!");
    }

    public void Search(String key) throws IOException {
        if(_map.get(key) != null)
            System.out.println(_map.get(key));
        else
            System.out.println("Элемента с ключом " + key + "нет!");
    }

    public void Add(String value) throws IOException {
        String[] s = value.split("-");

        if(value.contains("-") && KeyCheck(value, _map) && isNumeric(s[0]) && isNumeric(s[1])) {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(value + "\n");
            System.out.println("'" + value+ "'" + " добавлено");
            fw.close();
            loadFile();
        }
        else {
            throw new IOException("неверный формат записи");
        }
    }
}
