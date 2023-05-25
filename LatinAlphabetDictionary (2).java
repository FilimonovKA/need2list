import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LatinAlphabetDictionary extends Dictionary {
    private HashMap<String, String> _map;
    private String fileName;
    private List<String> list;

    public LatinAlphabetDictionary(String fileName) throws IOException {
        this.fileName = fileName;
        _map = new HashMap<>();
        loadFile();
    }

    private boolean countCheck(String value) {
        return value.length() == 4;
    }

    private boolean ValidityСheck(String value) {
        boolean validCheckValue = value.matches("^[a-zA-Z]+$");
        return validCheckValue && !isNumeric(value) && countCheck(value);
    }

    @Override
    public void loadFile() throws IOException {
        _map.clear();
        Path path = Paths.get(fileName);
        list = Files.readAllLines(path, StandardCharsets.UTF_8);
        for(String s : list){
            s = s.replace("\n", "").replace("\r", "");
            String[] array = s.split("-");
            if(ValidityСheck(array[0]) && ValidityСheck(array[1]))
                _map.put(array[0], array[1]);
        }
    }

    @Override
    public void ReadAll(){
        for(Map.Entry<String, String> e : _map.entrySet()) {
            System.out.println(e.getKey() + "-" + e.getValue());
        }
    }

    @Override
    public void Remove(String key) throws IOException {
        if(_map.containsKey(key)) {
            String value = _map.get(key).toString();
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

    @Override
    public void Search(String key) {
        if(_map.get(key) != null)
            System.out.println(_map.get(key));
        else
            System.out.println("Элемента с ключом " + key + "нет!");
    }

    @Override
    public void Add(String value) throws IOException {
        String[] s = value.split("-");
        if(value.contains("-") && KeyCheck(value, _map) && ValidityСheck(s[0]) && ValidityСheck(s[1])) {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(value + "\n");
            fw.close();
            loadFile();
            System.out.println("'" + value+ "'" + " добавлено");
        }
        else {
            System.out.println("неверный формат записи"); // можешь вместо вывода вкинуть исключение, но оно крашит программу
        }
    }
}
