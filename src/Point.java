public class Point extends Tuple{
    public Point() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 1.0;
    }
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = 1.0;
    }
    public Point(Tuple t) {
        this.x = t.x;
        this.y = t.y;
        this.z = t.z;
        this.w = 1.0;
    }
}

