public class Shape extends General{
    Material m;
    Point center;
    Matrix transform;
    public Shape(){
        Matrix i = new Matrix(4,4);
        m = new Material();
        center = new Point();
        transform = i.getIdentity();
    }
    public Tuple worldToObject(Point p){ //give it a point of the world, return that point in terms of the shape (this point is on the sphere)
        Matrix m = (Matrix)this.transform.inverse()[0];
        return m.multiplyTuple(p);
    }
    public Tuple normalToWorld(Tuple t){ //after you find normal of sphere in terms of the sphere, return to the world
        Matrix m = (Matrix)this.transform.inverse()[0];
        Tuple ans = m.transpose().multiplyTuple(t);
        ans.w = 0;
        return ans.normalize();
    }

    //ALL TYPES OF TRANSFORMATION
    public Matrix translate(double x,double y,double z){
        Matrix m = transform.getIdentity();
        m.assignValue(0,3,x);
        m.assignValue(1,3,y);
        m.assignValue(2,3,z);

        return m;
    }
    public Matrix scale(double x,double y,double z){
        Matrix m = transform.getIdentity();
        m.assignValue(0,0,x);
        m.assignValue(1,1,y);
        m.assignValue(2,2,z);
        return m;
    }
    public Matrix rotateX(double theta){
        theta = theta*Math.PI/180;
        Matrix m = transform.getIdentity();
        m.assignValue(1,1,Math.cos(theta));
        m.assignValue(1,2,-Math.sin(theta));
        m.assignValue(2,1,Math.sin(theta));
        m.assignValue(2,2,Math.cos(theta));

        return m;
    }
    public Matrix rotateY(double theta){
        theta = theta*Math.PI/180;
        Matrix m = transform.getIdentity();
        double temp[][] = {{Math.cos(theta),0,Math.sin(theta),0},{0,1,0,0},{-Math.sin(theta),0,Math.cos(theta),0},{0,0,0,1}};
        m.matrix = temp;

        return m;
    }
    public Matrix rotateZ(double theta){
        theta = theta*Math.PI/180;
        Matrix m = transform.getIdentity();
        m.assignValue(0,0,Math.cos(theta));
        m.assignValue(0,1,-Math.sin(theta));
        m.assignValue(1,0,Math.sin(theta));
        m.assignValue(1,1,Math.cos(theta));

        return m;
    }
    public Matrix skew(double xy,double xz,double yx,double yz,double zx,double zy){
        Matrix m = transform.getIdentity();
        m.assignValue(0,1,xy);
        m.assignValue(0,2,xz);
        m.assignValue(1,0,yx);
        m.assignValue(1,2,yz);
        m.assignValue(2,0,zx);
        m.assignValue(2,1,zy);

        return m;
    }
    public void setTransform(Matrix m){
        this.transform = m;
    }
    public Matrix viewTransform(Tuple from, Tuple to, Tuple up){ //like the x,y,z of a point, determines where the camera is facing
        Matrix m = new Matrix(4,4).getIdentity();
        Tuple subtract = to.subtract(from);
        Tuple forward = subtract.normalize();
        Tuple upNormal = up.normalize();
        Tuple left = forward.cross(new Vector(upNormal));
        Tuple trueUp = left.cross(new Vector(forward));
        m.assignValue(0,0,left.x);
        m.assignValue(0,1,left.y);
        m.assignValue(0,2,left.z);
        m.assignValue(1,0,trueUp.x);
        m.assignValue(1,1,trueUp.y);
        m.assignValue(1,2,trueUp.z);
        m.assignValue(2,0,-forward.x);
        m.assignValue(2,1,-forward.y);
        m.assignValue(2,2,-forward.z);
        //this basically sets the direction of the camera
        //you can translate m to move your eye looking at that camera
        Matrix transform = this.translate(-from.x,-from.y,-from.z); //from determines where you view the camera
        return m.multiplyMatrix(transform); //this is the what you see in the camera based on where you view it
    }
}
