public class Tuple extends General {
    double x; //x coordinate
    double y; //y coordinate
    double z; //z coordinate
    double w; //point or vector (1-point,0-vector)

    public Tuple() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
        this.w = 0.0;
    }

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    public boolean equals(Tuple t2) {
        return doubleEqual(this.x, t2.x) && doubleEqual(this.y, t2.y) && doubleEqual(this.z, t2.z) && doubleEqual(this.w, t2.w);
    }


    public static Tuple point(double x, double y, double z) {
        return new Tuple(x, y, z, 1.0);
    }

    public static Tuple vector(double x, double y, double z) {
        return new Tuple(x, y, z, 0.0);
    }

    public Tuple add(Tuple t2) { //cannot add 2 points
        double u;
        if (w + t2.w == 2) {
            return new Tuple(0.0, 0.0, 0.0, 0.0);
        } else {
            u = w + t2.w;
        }
        return new Tuple(x + t2.x,
                y + t2.y,
                z + t2.z,
                u);
    }

    public Tuple subtract(Tuple t2) { //can subtract two points, cannot do vector-point
        double u;
        if (w - t2.w == -1) {
            return new Tuple(0.0, 0.0, 0.0, 0.0);
        } else {
            u = w - t2.w;
        }
        return new Tuple(x - t2.x,
                y - t2.y,
                z - t2.z,
                u);
    }

    public Tuple multiplyScalar(double s) {
        return new Tuple(s * x,
                s * y,
                s * z,
                s * w);
    }

    public Tuple divideScalar(double s) {
        return new Tuple(x / s,
                y / s,
                z / s,
                w / s);
    }

    public double calcMag() {
        return Math.sqrt(x * x + y * y + z * z + w * w);
    }

    public Tuple negate() {
        return new Tuple(x * -1, y * -1, z * -1, w * -1);
    }

    public void printTuple() {
        System.out.println(x + " " + y + " " + z + " " + w);
    }

    public Tuple normalize() {
        return this.divideScalar(this.calcMag());
    }

    public double dot(Tuple v2) {
        return this.x * v2.x + this.y * v2.y + this.z * v2.z + this.w * v2.w;
    }

    public Vector cross(Vector v2) { //used for calculating the normal line for reflections of light
        return new Vector(this.y*v2.z-this.z*v2.y,
                this.z*v2.x-this.x*v2.z,
                this.x*v2.y-this.y*v2.x);
    }
    public Tuple reflect(Tuple normal){
        return this.subtract(normal.multiplyScalar(2).multiplyScalar(normal.dot(this)));
    }
}

