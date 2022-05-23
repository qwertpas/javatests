import java.util.function.DoubleFunction;

public class RootFinder {

    //replace this with interpolatingtreemap
    static double timeAtDist(double dist){
        //random piecewise func: https://www.desmos.com/calculator/5d4w5h9hqf
        if(dist < 2){
            return Math.sqrt(2*dist);
        }else if(dist < 3){
            return dist;
        }else if(dist < 4){
            return 1/3.*dist*dist;
        }else{
            return 1/12.*dist*dist*dist;
        }
    }

    static double distanceEq(double x){
        double R = 6.5;       //distance to target as given by vision
        double vx = -1;     //velocity of target in direction of robot (radial)
        double vz = 0.7;    //velocity of target perpendicular to robot (tangential)
        return Math.pow(R + vx * timeAtDist(x), 2) + Math.pow(vz * timeAtDist(x), 2) - x*x;
    }

    static double fsolve(DoubleFunction<Double> func, double x0, double tolerance, int maxIterations){

        double EPSILON = 1e-6;
        double x = x0;
        
        //applies newton's method on func to find its roots. fsolve() in scipy uses the gauss-newton method,
        //which simplifies to just newton's method in the single variable case.
        //https://en.wikipedia.org/wiki/Gauss%E2%80%93Newton_algorithm
        for(int i = 0; i < maxIterations; i++){
            double f = func.apply(x);

            if(Math.abs(f) < tolerance){
                return x;
            }

            double f_prime = (f - func.apply(x + EPSILON)) / EPSILON;
            double adjustment = f / f_prime;
            x += adjustment;

            //print for debug:
            System.out.println(x + ": " + f + ", " + f_prime);
        }
        System.out.println("did not converge in " + maxIterations + " iterations");
        return x0;
    }
    
    public static void main(String[] args) {
        double root = fsolve((double x) -> distanceEq(x), 4, 1e-6, 10);
        System.out.println("solution: " + root);
        System.out.println("error: " + distanceEq(root));
    }
}
