public class Computation { //like temp, stores all the tuples and info needed
    double lambda; // ray is origin + lambda * direction, lambda shows on where the point is on the line
    double r1,r2; //refract
    Sphere shape;
    Tuple point,overPoint,underPoint;
    Tuple eye, normal, reflect;
    Boolean isInside; //if the ray and the object intersect inside the shape
    public Computation(){}
    public double Schlick(){ //calc how much light reflected, depends on material of the shape
        return 0;
    }

}
