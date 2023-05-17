import java.io.IOException;

public interface IDictionary {
    public void loadFile() throws IOException;
    public void Add(String value) throws IOException;
    public void Remove(String key) throws IOException;
    public void ReadAll();
    public void Search(String key);
}
