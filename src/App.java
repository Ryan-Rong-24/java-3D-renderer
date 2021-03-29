import java.io.*;

public class App {
    public static void main(String[] args) throws IOException{
//        String name = "render";
//        PrintWriter pw = new PrintWriter(new FileWriter("render.ppm"));
//        Canvas c = new Canvas(5,3);
//        c.printCanvas();
//        Color color = new Color(1.5,0,0);
//        Color color2 = new Color(0,0.5,0);
//        Color color3 = new Color(-0.5,0,1);
//
//        c.writePixel(0,0,color);
//        c.writePixel(2,1,color2);
//        c.writePixel(4,2,color3);
//
//        c.printPPM("render");
//
//        Matrix m = new Matrix(4, 4);
//        double temp[][] = {{-5,2,6,-8},{1,-5,1,8},{7,7,-6,-7},{1,-3,7,4}};
//        m.matrix = temp;
//        Matrix i = (Matrix)m.inverse()[0];
//        i.printMatrix();
        
//        firstImage();
//        secondImage();
        thirdImage();
//        fourthImage();
    }
    public static void firstImage() throws IOException{
        String name = "sphere";
        PrintWriter pw = new PrintWriter(new FileWriter(name+".ppm"));
        Canvas c = new Canvas(100,100);
        Color color = new Color(0,0,1);
        Sphere s = new Sphere();
        Point origin = new Point(0,0,-5);
        double wallZ = 10;
        double wallSize = 7;
        double pixel = wallSize/c.width;
        double half = wallSize/2;
        for (int i = 0; i < c.width; i++) {
            double y = half-pixel*i;
            for (int j = 0; j < c.height; j++) {
                double x = -half+pixel*j;
                Point position = new Point(x,y,wallZ);
                Ray r = new Ray(origin,position.subtract(origin).normalize());
                Hit h = s.intersectionWithRay(r);
                if(h.hasIntersection){
                    c.writePixel(j,i,new Color(1,0,0));
                }
            }
        }
        c.printPPM("firstImage");
    }
    public static void secondImage() throws IOException{
        Canvas c = new Canvas(100,100);

        Sphere s = new Sphere();
//        s.transform = s.scale(1.25,1.25,1.25);
//        s.transform.printMatrix();
        s.m.c = new Color(1,0.2,1);
        Light l = new Light(new Point(-10,10,-10),new Color(1,1,1));
        double wallSize = 3;
        double pixel = wallSize/c.width;
        double half = wallSize/2;
        for (int i = 0; i < c.width; i++) {
            double y = half-pixel*i;
            for (int j = 0; j < c.height; j++) {
                double x = -half+pixel*j;
                Ray r = new Ray(new Point(x,y,-5), new Vector(0,0,1));
                Hit h = s.intersectionWithRay(r);
                Tuple eye = r.direction.multiplyScalar(-1);
                if(h.hasIntersection){
                    double hitPoint = h.intersection[0].lambda;
                    Point p = new Point(r.position(hitPoint));
                    Tuple normal = s.normal(p);
                    Computation comp = new Computation();
                    comp.point = p;
                    comp.normal = normal;
                    comp.eye = eye;
                    comp.shape = new Sphere();
                    c.writePixel(j,i,s.m.lighting(l,comp,false));
                }
            }
        }
        c.printPPM("secondImage");
    }
    public static void thirdImage() throws IOException{
        Sphere floor = new Sphere();
        floor.transform = floor.scale(10,0.01,10);
        floor.m.c = new Color(1,0.9,0.9);
        floor.m.specular = 0;

        Sphere leftWall = new Sphere();
        leftWall.transform = leftWall.scale(10,0.01,10);
        leftWall.transform = leftWall.rotateX(90).multiplyMatrix(leftWall.transform);
        leftWall.transform = leftWall.rotateY(-45).multiplyMatrix(leftWall.transform);
        leftWall.transform = leftWall.translate(0,0,5).multiplyMatrix(leftWall.transform);
        leftWall.m = floor.m;

        Sphere rightWall = new Sphere();
        rightWall.transform = rightWall.scale(10,0.01,10);
        rightWall.transform = rightWall.rotateX(90).multiplyMatrix(rightWall.transform);
        rightWall.transform = rightWall.rotateY(45).multiplyMatrix(rightWall.transform);
        rightWall.transform = rightWall.translate(0,0,5).multiplyMatrix(rightWall.transform);
        rightWall.m = floor.m;

        Sphere leftSphere = new Sphere();
        leftSphere.transform = leftSphere.translate(-1.5,0.33,-0.75).multiplyMatrix(leftSphere.scale(0.33,0.33,0.33));
        leftSphere.m.specular = 0.3;
        leftSphere.m.diffused = 0.7;
        leftSphere.m.c = new Color(0.88,1,1);

        Sphere middleSphere = new Sphere();
//        middleSphere.transform = middleSphere.scale(0.5,0.5,0.5);
        middleSphere.transform = middleSphere.translate(-0.5,1,0.5).multiplyMatrix(middleSphere.transform);
        middleSphere.m.specular = 0.3;
        middleSphere.m.diffused = 0.7;
        middleSphere.m.c = new Color(1,0,1);

        Sphere rightSphere = new Sphere();
        rightSphere.transform = rightSphere.translate(1.5,0.5,-0.5).multiplyMatrix(rightSphere.scale(0.5,0.5,0.5));
        rightSphere.m.specular = 0.3;
        rightSphere.m.diffused = 0.7;
        rightSphere.m.c = new Color(0.5,0,0.5);

        Light lightSource = new Light(new Point(-10,10,-10),new Color(1,1,1));
        Sphere[] objects = {leftWall,rightWall,floor,leftSphere,middleSphere,rightSphere};
        World w = new World(lightSource,objects);

        Camera c = new Camera(1000,500,Math.PI/3);
        Point from = new Point(0,1.5,-5);
        Point to = new Point(0,1,0);
        Vector up = new Vector(0,1,0);
        c.transform = c.viewTransform(from,to,up);

        Canvas canvas = c.render(w);
        canvas.printPPM("thirdImage");
    }
    public static void fourthImage() throws IOException{
        World d = new World("DEFAULT");
        Camera c = new Camera(100,50,Math.PI/2);
        Point from = new Point(-5,0.5,-3);
        Point to = new Point(0,0,0);
        Vector up = new Vector(0,1,0);
        c.transform = c.viewTransform(from,to,up);

        Canvas canvas = c.render(d);
        canvas.printPPM("fourthImage");
    }
}
