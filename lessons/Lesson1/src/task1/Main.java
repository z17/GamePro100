package task1;



public class Main
{
    public static void main(String ... args) {
        Hero man = new Hero();

        man.moveDown();
        man.moveDown();
        man.moveDown();
        man.moveRight();
        man.moveRight();
        man.moveDown();
        man.moveDown();
        man.moveDown();
        man.moveDown();
        man.moveDown();

        System.out.println(man.getPath());
    }
}
