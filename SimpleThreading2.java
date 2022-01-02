
public class SimpleThreading2 {

    static int mainCount = 0;
    public static void main(String[] args) {

        new ModuleThread().start();
        new ModuleThread().start();

        while(true){
            System.out.println("main run: " + Thread.activeCount());
            mainCount++;

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ModuleThread extends Thread {

        private boolean stopped = false;
        Thread t;

        public void run() {
            int count = 0;
            while (!stopped) {
                System.out.println(mainCount + " : " + count);
                count++;

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
