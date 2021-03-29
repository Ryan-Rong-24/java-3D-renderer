public class Light {
    Point pos = new Point();
    Color intensity = new Color(1,1,1);
    public Light () {
    }
    public Light (Point pos, Color intensity){
        this.pos = pos;
        this.intensity = intensity;
    }
    public Light (Tuple pos, Color intensity){
        Point p = new Point(pos.x,pos.y,pos.z);
        this.pos = p;
        this.intensity = intensity;
    }
}
