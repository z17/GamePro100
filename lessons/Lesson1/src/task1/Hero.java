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
        move(Direction.UP);
    }
    public void moveDown() {
        move(Direction.DOWN);
    }
    public void moveLeft() {
        move(Direction.LEFT);
    }
    public void moveRight() {
        move(Direction.RIGHT);
    }

    public String getPath() {
        if (active) {
            commands.add("FAIL");
        }
        return commands.getCommands();
    }

    private void readFile() {
        map = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("D:\\GamePro100\\lessons\\Lesson1\\src\\task1\\map"));

            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null) {
                map.add(sCurrentLine);

                int posStart = sCurrentLine.indexOf("S");
                int posEnd = sCurrentLine.indexOf("E");
                if (posStart >= 0) {
                    x = posStart;
                    y = map.size()- 1;
                }
                if (posEnd >= 0) {
                    xEnd = posEnd;
                    yEnd = map.size()- 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    private void move(Direction direction) {
        char currentSymbol = map.get(y).charAt(x);
        if (!active) {
            return;
        }
        switch (direction) {
            case UP:
                y--;
                break;
            case DOWN:
                y++;
                break;
            case LEFT:
                x--;
                break;
            case RIGHT:
                x++;
                break;
        }
        if (map.get(y).charAt(x) == '*'){
            commands.add("ERROR");
            active = false;
        } else if(xEnd == x && yEnd == y) {
            commands.add("FINISH");
            active = false;
        }
        else {
            commands.add(direction.toString());
        }
    }
}
