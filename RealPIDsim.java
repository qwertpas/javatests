import java.awt.*;

public class RealPIDsim {

    static double kP = 50;//30
    static double kI = 0;
    static double kD = 60;//30
    static double kF = 5000;//30

	static final double kineFric = 4000;
    static final double statFric = 0;
    static final double maxPower = 8000;
	static final double mass = 45;
    
    static double power = 0;
    static double position = 10;
	static double velocity = 0;
	static double acceleration = 0;
	static double direction = 1;
    static double P = 0;
    static double I = 0;
    static double D = 0;
    static double F = 0;
    static double target;
    static double error;
    


    public static void main(String[] args){
        
        Plant plant = new Plant("plant");
        double lasttime = System.nanoTime(); 
        

        while(true){
            double time = System.nanoTime();
            double dtime = Math.round(time-lasttime)*1e-9;
            target = MouseInfo.getPointerInfo().getLocation().getX() * (180/1438.0);

            double fnet = power - kineFric*direction;
            acceleration = fnet / mass;
            velocity = velocity + acceleration*dtime;
            position = position + velocity*dtime;

            
            direction = Math.copySign(1, velocity);

            if (Math.abs(power) > statFric) {
                
            }else{
                power = 0.0;
            } 

            System.out.println("fnet: " + fnet +  "    P: " + P + "     D: " + D + "     kine: " + -kineFric*direction);
            
            System.out.print("||");
            for(int i = 0; i < position; i++) {
                System.out.print(" ");
            }
            if((int) direction == 1) System.out.println("> " + error);
            else 			   System.out.println("< " + error);
            
            lasttime = time;
            // System.out.println(System.nanoTime());
            //END main().while{}
        }
    } //END main()

    public static class Plant implements Runnable{

        private boolean exit;
        private String name;
        Thread t;

        Plant(String threadname) {
            name = threadname;
            t = new Thread(this, name);
            System.out.println("New Thread: " + t);
            exit = false;
            t.start();
        }
        public void run(){
            double lasterror = target;
            double lasttime = System.nanoTime(); 
            while(!exit) {
                /** PID CODE HERE */
                double time = System.nanoTime();
                double dtime = (time - lasttime)*1e-9;
                error = target - position;
                double derror = error - lasterror;

                P = kP * error;
                I = kI * (I + error);
                D = kD * (derror / dtime);
                F = Math.copySign(kF, error);

                power = P + I + D + F;
                if(power > maxPower) {
                    power = maxPower;
                }else if(power < -maxPower) {
                    power = -maxPower;
                }

                lasterror = error;
                lasttime = time;

                //20 millisecond delay or 50 times a second
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {}
				
            } //END of Plant.run().while{}
            
        } //END of Plant.run()

        public void stop(){
            exit = true;
        }
        
        
        //END of Plant
    }







}