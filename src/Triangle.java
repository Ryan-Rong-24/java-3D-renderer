import java.util.ArrayList;

public class Cube extends Shape{
    public Cube(){}

    public boolean cubeEqual(Cube p){
        return shapeEquals(p);
    }

    public Hit localIntersect(Ray n) {

//        Matrix i = (Matrix) transform.inverse()[0];
//        Ray n = r.transform(i);
        double[] x = checkAxis(n.origin.x,n.direction.x);
        double[] y = checkAxis(n.origin.y,n.direction.y);
        double[] z = checkAxis(n.origin.z,n.direction.z);
        double tmin = Math.max(x[0],Math.max(y[0],z[0]));
        double tmax = Math.min(x[1],Math.min(y[1],z[1]));
        ArrayList<Intersection> ii = new ArrayList<>();
        if(tmin>tmax){//no intersection
            return new Hit(false,0,ii);
        } else if(tmin<tmax){
            ii.add(new Intersection(tmin,n,this));
            ii.add(new Intersection(tmax,n,this));
            return new Hit(true ,2,ii);
        }
        ii.add(new Intersection(tmin,n,this));
        return new Hit(true ,1, ii);
    }

    public Tuple localNormal(Tuple p) {
        double max = Math.max(Math.abs(p.x),Math.max(Math.abs(p.y),Math.abs(p.z)));
        if(max==Math.abs(p.x)){
            return new Vector(p.x,0,0);
        } else if(max==Math.abs(p.y)){
            return new Vector(0,p.y,0);
        } else {
            return new Vector(0,0,p.z);
        }
    }

    public double[] checkAxis(double orig, double dir){ //if checkXaxis, send in x of orig of ray and x of dir of ray
        double tmin = -1-orig; //-1 extends the edges of cube for analysis
        double tmax = 1-orig;
        if(Math.abs(dir)>0.0001) { //if there is an intersection
            tmin = tmin/dir;
            tmax = tmax/dir;
        } else { //else
            tmax *= Integer.MAX_VALUE;
            tmin *= Integer.MAX_VALUE;
        }
        if(tmin>tmax){
            double x = tmin;
            tmin = tmax;
            tmax = x;
        }
        return new double[]{tmin,tmax};
    }
}
