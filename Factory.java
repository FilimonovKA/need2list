import java.io.IOException;

public class Factory implements IFactory {
    public Dictionary createLatinDict(String filename) throws IOException {
        return new LatinAlphabetDictionary(filename);
    }

    public Dictionary createNumbersDic(String filename) throws IOException {
        return new NumbersDictionary(filename);
    }
}
