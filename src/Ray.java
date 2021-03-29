public class Ray {
    Point origin;
    Vector direction;
    public Ray(Point o, Vector d){
        origin = o;
        direction = d;
    }
    public Ray(Tuple o, Tuple d){
        origin = new Point(o.x,o.y,o.z);
        direction = new Vector(d.x,d.y,d.z);
    }
    public Tuple position(double lambda){ //
        return this.origin.add(this.direction.multiplyScalar(lambda));
    }
    public Ray transform(Matrix m){ //find where the light is relative to the shape after you move the shape
        return new Ray(m.multiplyTuple(origin),m.multiplyTuple(direction));
    }
}
