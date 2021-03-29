import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CameraTest {
    @Test
    void Camera(){
        Camera c = new Camera(125,200,Math.PI/2);
        assertEquals(c.h==125,true);
        assertEquals(c.v==200,true);
        assertEquals(c.FOV==Math.PI/2,true);
        assertEquals(c.transform.checkEqual(new Matrix(4,4).getIdentity()),true);
        System.out.println(c.pixelSize);
        assertEquals(c.pixelSize==0.01,true);

    }
    @Test
    void rayForPixel(){
        Shape s = new Shape();
        Camera c = new Camera(201,101,Math.PI/2);
        Ray r = c.rayForPixel(100,50);
        assertEquals(r.origin.equals(new Point()),true);
        assertEquals(r.direction.equals(new Vector(0,0,-1)),true);

        Camera c2 = new Camera(201,101,Math.PI/2);
        Ray r2 = c2.rayForPixel(0,0);
        assertEquals(r2.origin.equals(new Point()),true);
        assertEquals(r2.direction.equals(new Vector(0.66519,0.33259,-0.66851)),true);

        Camera c3 = new Camera(201,101,Math.PI/2);
        c3.transform = s.rotateY(45).multiplyMatrix(s.translate(0,-2,5));
        Ray r3 = c3.rayForPixel(100,50);
        r3.direction.printTuple();
        assertEquals(r3.origin.equals(new Point(0,2,-5)),true);
        assertEquals(r3.direction.equals(new Vector(0.707106,0,-0.707106)),true);
    }
    @Test
    void render(){
        Camera c = new Camera(11,11,Math.PI/2);
        World w = new World("DEFAULT");
        Point from = new Point(0,0,-5);
        Point to = new Point();
        Vector up = new Vector(0,1,0);
        c.transform = c.viewTransform(from,to,up);
        Canvas image = c.render(w);
        assertEquals(image.getPixel(5,5).equals(new Color(0.38066,0.47583,0.2855)),true);
    }
}