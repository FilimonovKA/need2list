import java.io.IOException;

public interface IFactory {
    Dictionary createLatinDict(String filename) throws IOException;
    Dictionary createNumbersDic(String filename) throws IOException;
}
