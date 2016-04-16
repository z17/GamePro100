package task1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private int x;
    private int y;
    private int xEnd;
    private int yEnd;
    private CommandBuffer commands;
    private List<String> map;
    private boolean active;

    public Hero() {
        readFile();
        commands = new CommandBuffer();
        active = true;
    }

    public void moveUp() {
        move(Test.UP);
    }
    public void moveDown() {
        move(Test.DOWN);
    }
    public void moveLeft() {
        move(Test.LEFT);
    }
    public void moveRight() {
        move(Test.RIGHT);
    }

    public String getPath() {
        return commands.getCommands();
    }

    private void readFile() {
        map = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\java\\LessonExample\\src\\task1\\map"));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                map.add(sCurrentLine);

                int posStart = sCurrentLine.indexOf("S");
                int posEnd = sCurrentLine.indexOf("E");
                if (posStart >= 0) {
                    x = posStart;
                    y = map.size();
                }
                if (posEnd >= 0) {
                    xEnd = posStart;
                    yEnd = map.size();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Test {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private void move(Test test) {
        if (!active) {
            return;
        }
        switch (test) {
            case UP:
                y++;
                break;
            case DOWN:
                y--;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        if (x < 0 || y < 0 || x > 10 || y > 10){
            commands.add("ERROR");
            active = false;
        } else {
            commands.add(test.toString());
        }
    }
}
