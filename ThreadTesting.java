import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class ThreadTesting {
    public static void main(String[] args) {
        ThreadTesting myThreadTest = new ThreadTesting();


        myThreadTest.initialize();
        System.out.println("running");

        while (!myThreadTest.isFinished()) {
            myThreadTest.execute();
        }
    }

    int port = 6006;
    String tcp_ip = "127.0.0.1";
    InetAddress address;
    Socket socket;
    Scanner in;
    public static double xJetson;
    public static double yJetson;
    public static double skewJetson;

    public static TcpThread t1;

    public class TcpThread implements Runnable {
        private boolean exit;
        private String name;
        Thread t;

        TcpThread(String threadname) {
            name = threadname;
            t = new Thread(this, name);
            System.out.println("New Thread: " + t);
            exit = false;
            t.start();
        }

        public void run() {
            System.out.println("starting Thread");
            try {
                address = InetAddress.getByName(tcp_ip);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("got address");
            while (!exit) { // things that used to be in execute()

                try {
                    System.out.println("About to open socket");
                    socket = new Socket(address, port);
                    System.out.println("Opened Socket");
                    in = new Scanner(socket.getInputStream());
                    System.out.println("Got Input stream");

                    String m = in.nextLine();

                    double[] vals = parse(m);
                    xJetson = vals[1];
                    yJetson = vals[2];
                    skewJetson = vals[3];

                    System.out.println(Arrays.toString(vals));


                    socket.close();
                    vals[0] = 0;

                } catch (Throwable e) {
                    System.out.println(e);
                }

                System.out.println("threading");

                // try {
                // Thread.sleep(10);
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
            }
        }

        public void stop() {
            exit = true;
        }
    }

    protected void initialize() {
        t1 = new TcpThread("JetsonComm");
    }

    protected void execute() {
        System.out.println("executing");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    
    private double[] parse(String str){
        String[] splitted = str.split(" ");
        //System.out.println(Arrays.toString(splitted));
        double[] retVar = new double[5];
        for(int i = 0; i < 5; i++){
            retVar[i] = Double.valueOf(splitted[i]);
        }

        return retVar;
    }

    protected boolean isFinished() {
        return false;
    }

    
}
