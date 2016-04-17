package task1;


public class Main
{
    public static void main(String ... args) {
        Hero man = new Hero();

//        for (int i = 0; i < 13; i++) {
//            man.moveUp();
//        }
//        man.moveRight();
//        man.moveRight();

        // move left top
        boolean flag = true;
        while(flag) {
            if (man.checkUp() == Hero.Type.empty) {
                man.moveUp();
            } else if (man.checkLeft() == Hero.Type.empty){
                man.moveLeft();
            } else {
                flag = false;
            }
        }

        // find finish
        flag = true;
        while(flag) {
            if (man.checkRight() == Hero.Type.empty) {
                if (man.checkRight() == Hero.Type.finish) {
                    flag = false;
                }
                man.moveRight();
            } else if (man.checkDown() == Hero.Type.empty) {
                if (man.checkDown() == Hero.Type.finish) {
                    flag = false;
                }
                man.moveDown();
                while(man.checkLeft() == Hero.Type.empty) {
                    man.moveLeft();
                }

            } else {
                flag = false;
            }
        }

        System.out.println(man.getPath());
    }
}
