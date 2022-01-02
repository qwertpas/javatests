class TurncenterSwerve{

    public static void main(String[] args) {
        double turnCenterx = -1;
        double turnCentery = 0.1;
        double HALF_DIST_BETWEEN_WHEELS = 0.2;


        double targetLeftModuleAngle = Math.IEEEremainder((Math.atan2(turnCentery - HALF_DIST_BETWEEN_WHEELS, turnCenterx) - Math.PI/2.0), Math.PI);
        double targetRightModuleAngle = (Math.atan2(turnCentery + HALF_DIST_BETWEEN_WHEELS, turnCenterx) - Math.PI/2.0) % (Math.PI/2.0);


        System.out.println(Math.toDegrees(targetLeftModuleAngle));
        System.out.println(Math.toDegrees(targetRightModuleAngle));
    }



}