
class DiffModule{

    public static void main(String[] args) {
        var currentAngle = 180;
        var targetAngle = 0;

        double difference180 = (currentAngle - targetAngle) % 180;
        System.out.println("diff180: " + difference180);


        double closestAngle;
        if(Math.abs(difference180) < 90){
            closestAngle = currentAngle - difference180;
        }else{
            closestAngle = currentAngle - difference180 + Math.copySign(180, difference180);
        }

        double difference360 = (int)(closestAngle - targetAngle) % 360;
        boolean reversed = (Math.abs(difference360) == 180);


        System.out.println("closestAngle: " + closestAngle);
        System.out.println("reversed: " + reversed);
    }

    




}