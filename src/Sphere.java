public class Sphere extends Shape{
    Point center = new Point();
    double radius = 1;
    public Sphere(){}

    public Hit intersectionWithRay(Ray r){
        //是否
        //几个
        //分别是什么

        Matrix i = (Matrix)transform.inverse()[0];
        Ray n = r.transform(i);
        double a = Math.pow(n.direction.calcMag(),2);
        Tuple o = n.origin.subtract(new Tuple(0,0,0,1));
        double b = n.direction.dot(o)*2;
        double c = o.calcMag()*o.calcMag()-1;
        double disc = b*b-4*a*c;
        Intersection[] arr = new Intersection[2];

        if(doubleEqual(disc,0)){
            arr[0] = new Intersection(-(b/(2*a)),r,this);
            return new Hit(true,1,arr);
        } else if(disc > 0){
            arr[0] = new Intersection(-(b+Math.sqrt(disc))/(2*a),r,this);
            arr[1] = new Intersection(-(b-Math.sqrt(disc))/(2*a),r,this);
            return new Hit(true,2,arr);
        } else {
            return new Hit(false,0,arr);
        }
    }
    public Tuple normal(Point p) {
        Tuple t = this.worldToObject(p);
        t = t.subtract(new Point(0, 0, 0)); //new normal
        return this.normalToWorld(t);
//        return p.subtract(new Point(0,0,0)); //returns direction of normal line
    }
}
