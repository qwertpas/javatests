
public class SimpleThreading {

    public static void main(String[] args) {

        int mainCount = 0;
        ModuleThread thread1 = new ModuleThread("thread1", mainCount);
        ModuleThread thread2 = new ModuleThread("thread2", mainCount);

        while(true){
            System.out.println("main run: " + Thread.activeCount());
            thread1.setMainCount(mainCount);
            thread2.setMainCount(mainCount);
            mainCount++;

            if(mainCount > 5){
                thread1.stop();
                thread2.stop();
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class ModuleThread implements Runnable {

        boolean stopped = false;
        int mainCount;

        ModuleThread(String name, int mainCount_input) {
            mainCount = mainCount_input;
            Thread t = new Thread(this, name);
            t.start();
        }

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

        public void stop(){
            stopped = true;
        }

        public void setMainCount(int mainCount_input){
            mainCount = mainCount_input;
        }
    }


}
