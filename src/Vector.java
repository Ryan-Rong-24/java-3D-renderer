public class Vector extends Tuple {
    public Vector(){
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 0.0;
    }
    public Vector(Tuple t) {
        this.x = t.x;
        this.y = t.y;
        this.z = t.z;
        this.w = 0.0;
    }
}

