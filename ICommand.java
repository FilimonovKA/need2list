import java.io.IOException;

public interface ICommand {
    void setCommand(String command);
    void execute() throws IOException;
}
