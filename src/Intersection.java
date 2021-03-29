public class Intersection {
    double lambda;
    Ray r;
    Sphere shape;
    public Intersection(){}
    public Intersection(double l, Ray r, Sphere s){
        lambda = l;
        this.r = r;
        this.shape = s;
    }
    public Computation prepareComputation(Ray r){
        Computation comp = new Computation();
        comp.lambda = this.lambda;
        comp.shape = this.shape;
        comp.point = r.position(this.lambda);
        comp.eye = r.direction.multiplyScalar(-1);
        comp.normal = comp.shape.normal(new Point(comp.point));
        double temp = comp.normal.dot(comp.eye);
        if(temp<0){
            comp.isInside = true;
            comp.normal = comp.normal.multiplyScalar(-1);
        } else {
            comp.isInside = false;
        }
        Tuple multi = comp.normal.multiplyScalar(0.00001);
        comp.overPoint = comp.point.add(multi);
        comp.underPoint = comp.point.subtract(multi);
        return comp;
    }
}

