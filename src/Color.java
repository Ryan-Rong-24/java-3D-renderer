public class Color {
    Vector color = new Vector();
    public Color(){
    }
    public Color(double r,double g,double b){
        if(r<0.0) r = 0.0;
        if(g<0.0) g = 0.0;
        if(b<0.0) b = 0.0;
        if(r>1.0) r = 1.0;
        if(g>1.0) g = 1.0;
        if(b>1.0) b = 1.0;
        this.color = new Vector(r,g,b);
    }
    public Color add(Color c2){
        Tuple sum = this.color.add(c2.color);
        return new Color(sum.x,sum.y,sum.z);
    }
    public Color subtract(Color c2) { //can subtract two points, cannot do vector-point
        Tuple diff = this.color.subtract(c2.color);
        return new Color(diff.x,diff.y,diff.z);
    }
    public Color multiplyScalar(double s) {
        Tuple t = this.color.multiplyScalar(s);
        return new Color(t.x,t.y,t.z);
    }
    public Color divideScalar(double s) {
        Tuple t = this.color.divideScalar(s);
        return new Color(t.x,t.y,t.z);
    }
    public Color multiply(Color c2){
        return new Color(this.color.x*c2.color.x,
                         this.color.y*c2.color.y,
                         this.color.z*c2.color.z);
    }
    public boolean equals(Color c2){
        return this.color.equals(c2.color);
    }
    public void printColor(){
        System.out.println(this.color.x+" "+this.color.y+" "+this.color.z);
    }
}
