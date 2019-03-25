

public class vision_conversion_trig{
    public static void main(String[] args) {

        double[] xyskew = convertTrig(new double[] {-18, 90, -20});

        System.out.println(xyskew[0]);
        System.out.println(xyskew[1]);
        System.out.println(xyskew[2]);
    }

    public static double[] convertTrig(double[] robotRelative){
        double a1 = robotRelative[0];  //x dist to target relative to robot
        double b1 = robotRelative[1];  //y dist to target relative to robot
        double s1 = robotRelative[2];  //measured as if looking at robot from the target, left is positive.

        s1 = s1 * (Math.PI/180);  //converting to radians for trig
        double h = Math.atan2(a1, b1);  //horizontal angle
        double d = Math.sqrt(a1*a1 + b1*b1);  //pythagorean theorem for distance

        //with a 2 after it means it is measurement to robot relative from target
        double a2 = d * Math.sin(s1);
        double b2 = d * Math.cos(s1);
        double s2 = (h + s1) * (180/Math.PI);  //skew in degrees (directly facing target is 0, to the right is positive)

        return new double[] {a2, b2, s2}; //new x, y, skew
    }
    
}

