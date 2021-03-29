import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void translate() {
        Shape s = new Shape();
        Matrix m = s.translate(-3,0,0);
        Point p = new Point();
        Tuple t = new Tuple(-3,0,0,1);

        Shape s2 = new Shape();
        Matrix m2 = s2.translate(5,-3,2);
        Point p2 = new Point(-3,4,5);
        Tuple t2 = new Tuple(2,1,7,1);
        Tuple t3 = new Tuple(-8,7,3,1);


        assertEquals(((Matrix)m2.inverse()[0]).multiplyTuple(p2).equals(t3),true);
    }

    @Test
    void scale() {
        Shape s = new Shape();
        Matrix m = s.scale(2,3,4);
        Vector p = new Vector(-4,6,8);
        Tuple t = new Tuple(-8,18,32,0);

        assertEquals(m.multiplyTuple(p).equals(t),true);
    }

    @Test
    void rotateX() {
        Shape s = new Shape();
        Point p = new Point(0,1,0);
        Matrix m = s.rotateX(45);
        Tuple t = new Tuple(0,0.7071067,0.7071067,1);

        assertEquals(m.multiplyTuple(p).equals(t),true);

        Shape s2 = new Shape();
        Point p2 = new Point(0,1,0);
        Matrix m2 = s2.rotateX(90);
        Tuple t2 = new Tuple(0,0,1,1);

        assertEquals(m2.multiplyTuple(p2).equals(t2),true);
    }
    @Test
    void rotateY() {
        Shape s = new Shape();
        Point p = new Point(0,0,1);
        Matrix m = s.rotateY(45);
        Tuple t = new Tuple(0.7071067,0,0.7071067,1);

        assertEquals(m.multiplyTuple(p).equals(t),true);

        Shape s2 = new Shape();
        Point p2 = new Point(0,0,1);
        Matrix m2 = s2.rotateY(90);
        Tuple t2 = new Tuple(1,0,0,1);

        assertEquals(m2.multiplyTuple(p2).equals(t2),true);
    }
    @Test
    void rotateZ() {
        Shape s = new Shape();
        Point p = new Point(0,1,0);
        Matrix m = s.rotateZ(45);
        Tuple t = new Tuple(-0.7071067,0.7071067,0,1);

        assertEquals(m.multiplyTuple(p).equals(t),true);

        Shape s2 = new Shape();
        Point p2 = new Point(0,1,0);
        Matrix m2 = s2.rotateZ(90);
        Tuple t2 = new Tuple(-1,0,0,1);

        assertEquals(m2.multiplyTuple(p2).equals(t2),true);
    }
    @Test
    void skew() {
        Shape s = new Shape();
        Point p = new Point(2,3,4);
        Matrix m = s.skew(1,0,0,0,0,0); //xz : x+=z
        Tuple t = new Tuple(5,3,4,1);

        assertEquals(m.multiplyTuple(p).equals(t),true);
    }
    @Test
    void viewTransform() {
        Shape s = new Shape();
        Tuple from = new Tuple(0,0,0,1);
        Tuple to = new Tuple(0,0,-1,1);
        Tuple up = new Tuple(0,1,0,0);
        Matrix m = s.viewTransform(from,to,up);
        Matrix ans = new Matrix(4,4).getIdentity();
        assertEquals(m.checkEqual(ans),true);

        Tuple from2 = new Tuple(0,0,0,1);
        Tuple to2 = new Tuple(0,0,1,1);
        Tuple up2 = new Tuple(0,1,0,0);
        Matrix m2 = s.viewTransform(from2,to2,up2);
        Matrix ans2 = s.scale(-1,1,-1);
        assertEquals(m2.checkEqual(ans2),true);

        Tuple from3 = new Tuple(0,0,8,1);
        Tuple to3 = new Tuple(0,0,0,1);
        Tuple up3 = new Tuple(0,1,0,0);
        Matrix m3 = s.viewTransform(from3,to3,up3);
        Matrix ans3 = s.translate(0,0,-8);
        assertEquals(m3.checkEqual(ans3),true);
    }
}