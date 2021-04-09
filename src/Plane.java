public class Plane extends Shape{ //new plane class
    public Plane(){}

    public Hit intersectionWithRay(Ray r){
        Matrix i = (Matrix)transform.inverse()[0];
        Ray n = r.transform(i);
        if(Math.abs(n.direction.y)<0.0001){
            return new Hit(false,0);
        }

        double lambda = -(n.origin.y)/(n.direction.y); //intersection lambda
        Intersection[] ii = new Intersection[1];
        ii[0] = new Intersection(lambda,n,new Sphere());
        return new Hit(true,1, ii);
        //only a plane intersect with ray has 1 intersection point
    }
    public Tuple normal(Point p) {
        //every point on the plane has normal 0,1,0, so it renders a plane
        p = new Point(worldToObject(p));
        Tuple normal = new Tuple(p.x, p.y,p.z,1);
        normalToWorld(normal).printTuple();
        return normalToWorld(normal); //local is 0,1,0, but to world it is not

    }
}
