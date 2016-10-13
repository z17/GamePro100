import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandBuffer {
    private List<String> commands;

    CommandBuffer() {
        commands = new ArrayList<>();
    }

    public void add(String s) {
        commands.add(s);
    }

    public String getCommands() {
        return String.join(";", commands);
    }
}
