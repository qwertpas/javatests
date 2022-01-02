
public class Inheritance{


    public static class Superclass{

        void commonFunc(){
            System.out.println("commonFunc from super");
        }
        
        void superFunc(){
            System.out.println("superFunc");
        }

    }

    public static class Subclass extends Superclass{

        void commonFunc(){
            System.out.println("commonFunc from sub");
        }

        void subFunc(){
            System.out.println("subFunc");
        }

    }


    public static void main(String[] args) {
        Superclass superclass = new Superclass();
        Superclass subclass = new Subclass();

        superclass.commonFunc();
        subclass.commonFunc();

        superclass.superFunc();
        ((Subclass) subclass).subFunc();
        
    }






}