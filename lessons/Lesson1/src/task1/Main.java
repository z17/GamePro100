package task1;

public class Main
{
    public static void main(String ... args) {
        Hero man = new Hero();

        man.moveUp();
        man.moveUp();
        man.moveUp();
        man.moveUp();
        man.moveUp();
        man.moveRight();
        man.moveRight();
        man.moveRight();
        man.moveRight();
        man.moveRight();

        System.out.println(man.getPath());
    }
}
