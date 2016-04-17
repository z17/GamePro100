package task1;



public class Main
{
    public static void main(String ... args) {
        Hero man = new Hero();

        for (int i = 0; i < 13; i++) {
            man.moveUp();
        }
        man.moveRight();
        man.moveRight();

        System.out.print(man.getPath());
    }
}
