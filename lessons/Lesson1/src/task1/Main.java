package task1;


public class Main {
    public static void main(String... args) {
        Hero man = new Hero();

//        for (int i = 0; i < 13; i++) {
//            man.moveUp();
//        }
//        man.moveRight();
//        man.moveRight();
/*

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
*/


        class MyNode {
            MyNode l;
            MyNode r;
            MyNode u;
            MyNode d;
            boolean marck;

            MyNode() {
                marck = false;
            }

            boolean isMark() {
                return this.marck;
            }

            public MyNode getL() {
                return l;
            }

            public void setL(MyNode l) {
                this.l = l;
            }

            public MyNode getR() {
                return r;
            }

            public void setR(MyNode r) {
                this.r = r;
            }

            public MyNode getU() {
                return u;
            }

            public void setU(MyNode u) {
                this.u = u;
            }

            public MyNode getD() {
                return d;
            }

            public void setD(MyNode d) {
                this.d = d;
            }

            public boolean isMarck() {
                return marck;
            }

            public void setMarck(boolean marck) {
                this.marck = marck;
            }

            void creatAllNode() {
                if (getD() == null) {

                }
                if (getU() == null){

                }
                if (getL() == null){

                }
                if (getR() == null){

                }

            }

            MyNode go = new MyNode();
        }

        System.out.println(man.getPath());
    }
}
