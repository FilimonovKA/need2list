import java.io.IOException;
import java.util.HashMap;

public abstract class Dictionary {
    //Проверка на повтор ключа
    protected boolean KeyCheck(String value, HashMap<String, String> _map){
        String[] array = value.split("-");
        return !_map.containsKey(array[0]);
    }

    protected boolean isNumeric(String s){
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public abstract void loadFile() throws IOException;
    public abstract void Add(String value) throws IOException;
    public abstract void Remove(String key) throws IOException;
    public abstract void ReadAll();
    public abstract void Search(String key) throws IOException;
}
