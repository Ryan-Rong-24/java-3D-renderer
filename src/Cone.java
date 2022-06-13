import java.util.ArrayList;

public class Cylinder extends Shape{
    double max = Integer.MAX_VALUE;
    double min = Integer.MIN_VALUE;
    boolean closed = false; //has lid
    public Cylinder(){}
    public Cylinder(double ma,double mi,boolean c){
        max = ma;
        min = mi;
        closed = c;
    }


    public boolean cylinderEqual(Cylinder p){
        return shapeEquals(p);
    }

    public Hit localIntersect(Ray n) {

//        Matrix i = (Matrix) transform.inverse()[0];
//        Ray n = r.transform(i);
        ArrayList<Intersection> ii = new ArrayList<>();
        double a = n.direction.x*n.direction.x+n.direction.z*n.direction.z; //if parallel to y axis
        if(doubleEqual(a,0)){
            return intersectCap(n,ii);
        }
        double b = 2*n.origin.x*n.direction.x+2*n.origin.z*n.direction.z;
        double c = n.origin.x*n.origin.x+n.origin.z*n.origin.z-1;
        double determinant = b*b-4*a*c;
        if(determinant<0){
            return new Hit(false,0,ii);
        }

        double t0 = (-b-Math.sqrt(determinant))/(2*a);
        double t1 = (-b+Math.sqrt(determinant))/(2*a);
        if(t0>t1){
            double temp = t0;
            t0 = t1;
            t1 = temp;
        }

        double y0 = n.origin.y+t0*n.direction.y;
        if(y0<max&&y0>min){
            ii.add(new Intersection(t0,n,this));
        }
        double y1 = n.origin.y+t1*n.direction.y;
        if(y1<max&&y1>min){
            ii.add(new Intersection(t1,n,this));
        }
        return intersectCap(n,ii);
    }

    public Tuple localNormal(Tuple p) {
        double dist = p.x*p.x+p.z*p.z;
        if(dist<1&&p.y>=max-0.0001){
            return new Tuple(0,1,0,0);
        }
        if(dist<1&&p.y<=min+0.0001){
            return new Tuple(0,-1,0,0);
        }
        return new Tuple(p.x,0,p.z,0);
    }

    public boolean checkCap(double t,Ray r){
        double x = r.origin.x+t*r.direction.x;
        double z = r.origin.z+t*r.direction.z;
        return (x*x+z*z)<=1; //1 is radius, whether intersection is on the lid
    }
    public Hit intersectCap(Ray r,ArrayList<Intersection> ii){
        if(closed==false||Math.abs(r.direction.y)<0.0001){
            int size = ii.size();
            return new Hit(size!=0,size,ii);
        }
        double t = (min-r.origin.y)/r.direction.y;
        if(checkCap(t,r)){
            ii.add(new Intersection(t,r,this));
        }
        double t2 = (max-r.origin.y)/r.direction.y;
        if(checkCap(t2,r)){
            ii.add(new Intersection(t2,r,this));
        }
        int size = ii.size();
        return new Hit(size!=0,size,ii);
    }
}
