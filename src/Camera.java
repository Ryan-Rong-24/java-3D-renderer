public class Camera extends Shape{
    int h,v; //horizontal,vertical
    double halfWidth,halfHeight;
    double FOV,pixelSize;
    Matrix transform;
    public Camera(){}
    public Camera(int h, int v, double FOV){
        this.h=h;
        this.v=v;
        this.FOV=FOV;
        this.transform = new Matrix(4,4).getIdentity();
        double halfView = Math.tan(FOV/2);
        double aspect = (h*1.0)/(v*1.0);
        if(aspect>=1){
            halfWidth=halfView;
            halfHeight=halfView/aspect;
        } else {
            halfWidth=halfView*aspect;
            halfHeight=halfView;
        }
        pixelSize = (halfWidth*2)/h;
    }
    public Ray rayForPixel(double x,double y){
        double xOffset = (x+0.5)*this.pixelSize;
        double yOffset = (y+0.5)*this.pixelSize;
        double worldX = this.halfWidth-xOffset;
        double worldY = this.halfHeight-yOffset;
        Matrix m = (Matrix)this.transform.inverse()[0];
        Tuple pixel = m.multiplyTuple(new Point(worldX,worldY,-1)); //where the pixel is
        Tuple origin = m.multiplyTuple(new Point());
        Tuple direction = pixel.subtract(origin).normalize();

        return new Ray(origin,direction);
    }
    public Canvas render(World w){
        Canvas c = new Canvas(h,v);
        for (int y = 0; y < this.v; y++) {
            for (int x = 0; x < this.h; x++) {
                c.writePixel(x,y,w.colorAt(rayForPixel(x,y),4));
            }
        }
        return c;
    }
}
