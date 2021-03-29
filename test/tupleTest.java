import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class tupleTest {

    @org.junit.jupiter.api.Test
    void add() {
        Tuple t1 = new Tuple(3,-2,5,1);
        Tuple t2 = new Tuple(-2,3,1,0);
        Tuple answer = new Tuple(1,1,6,1);
        assertEquals(t1.add(t2).equals(answer),true);
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Point p1 = new Point(3,2,1);
        Point p2 = new Point(5,6,7);
        Vector ans = new Vector(-2,-4,-6);
        assertEquals(p1.subtract(p2).equals(ans),true);

        Point p3 = new Point(3,2,1);
        Vector v1 = new Vector(5,6,7);
        Point ans2 = new Point(-2,-4,-6);
        assertEquals(p3.subtract(v1).equals(ans2),true);

        Vector v2 = new Vector(3,2,1);
        Vector v3 = new Vector(5,6,7);
        Vector ans3 = new Vector(-2,-4,-6);
        assertEquals(v2.subtract(v3).equals(ans3),true);

        Vector zero = new Vector(0,0,0);
        Vector v = new Vector(1,-2,3);
        Vector ans4 = new Vector(-1,2,-3);
        assertEquals(zero.subtract(v).equals(ans4),true);
    }

    @org.junit.jupiter.api.Test
    void equals() {
        Tuple t1 = new Tuple(1,2,3,1);
        Tuple t2 = new Tuple(3,2,1,0);

        assertEquals(t1.equals(t2),false);
    }

    @org.junit.jupiter.api.Test
    void multiplyScalar() {
        Tuple a = new Tuple(1,-2,3,-4);
        Tuple ans = new Tuple(3.5,-7,10.5,-14);
        assertEquals(a.multiplyScalar(3.5).equals(ans),true);

        Tuple b = new Tuple(1,-2,3,-4);
        Tuple ans2 = new Tuple(0.5,-1,1.5,-2);
        assertEquals(b.multiplyScalar(0.5).equals(ans2),true);
    }

    @org.junit.jupiter.api.Test
    void divideScalar() {
        Tuple a = new Tuple(1,-2,3,-4);
        Tuple ans = new Tuple(0.5,-1,1.5,-2);
        assertEquals(a.divideScalar(2).equals(ans),true);
    }

    @org.junit.jupiter.api.Test
    void calcMag() {
        Vector v = new Vector(1,0,0);
        double ans = 1.0;
        assertEquals(v.calcMag()==ans,true);

        Vector v2 = new Vector(0,1,0);
        double ans2 = 1.0;
        assertEquals(v2.calcMag()==ans2,true);

        Vector v3 = new Vector(0,0,1);
        double ans3 = 1.0;
        assertEquals(v3.calcMag()==ans3,true);

        Vector v4 = new Vector(1,2,3);
        double ans4 = Math.sqrt(14);
        assertEquals(v4.calcMag()==ans4,true);

        Vector v5 = new Vector(-1,-2,-3);
        double ans5 = Math.sqrt(14);
        assertEquals(v5.calcMag()==ans5,true);

    }

    @org.junit.jupiter.api.Test
    void negate() {
        Tuple a = new Tuple(1,-2,3,-4);
        Tuple ans = new Tuple(-1,2,-3,4);
        assertEquals(a.negate().equals(ans),true);
    }

    @org.junit.jupiter.api.Test
    void normalize() {
        Vector a = new Vector(4,0,0);
        Vector ans = new Vector(1,0,0);
        assertEquals(a.normalize().equals(ans),true);

        Vector b = new Vector(1,2,3);
        Vector ans2 = new Vector(1/Math.sqrt(14),2/Math.sqrt(14),3/Math.sqrt(14)); //mag of ans = 1 (归一化)
        assertEquals(b.normalize().equals(ans2),true);
    }

    @org.junit.jupiter.api.Test
    void dot() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,3,4);
        assertEquals(a.dot(b)==20,true);
    }

    @org.junit.jupiter.api.Test
    void cross() {
        Vector a = new Vector(1,2,3);
        Vector b = new Vector(2,3,4);
        Vector ans = new Vector(-1,2,-1);
        assertEquals(a.cross(b).equals(ans),true);

        Vector ans2 = new Vector(1,-2,1); //ans.negate()
        assertEquals(b.cross(a).equals(ans2),true);
    }
}