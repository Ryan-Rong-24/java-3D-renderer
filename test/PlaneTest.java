import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void intersectionWithRay() {
        Plane p = new Plane();
        Ray r1 = new Ray(new Point(0,10,0),new Vector(0,0,1));
        assertEquals(p.intersectionWithRay(r1).hasIntersection,false);

        Ray r2 = new Ray(new Point(0,0,0),new Vector(0,0,1));
        assertEquals(p.intersectionWithRay(r2).hasIntersection,false);

        Ray r3 = new Ray(new Point(0,1,0),new Vector(0,-1,0));
        assertEquals(p.intersectionWithRay(r3).intersection[0].lambda==1,true);
        Ray r4 = new Ray(new Point(0,-1,0),new Vector(0,1,0));
        assertEquals(p.intersectionWithRay(r4).intersection[0].lambda==1,true);

        Ray r5 = new Ray(new                                                                                                                                          Point(0,0,-5),new Vector(0,0,1));
        p.transform = p.translate(5,0,0);
        p.intersectionWithRay(r5);
    }

    @Test
    void normal() {
//        Plane p = new Plane();
//        for (int i = 0; i < 3; i++) {
//            assertEquals(p.normal(new Point(i,0,0)).equals(new Tuple(0,1,0,0)),true);
//        }
        Plane p = new Plane();
        p.transform = p.translate(0,1,0);
        Point x1 = new Point(0,1.70711,-0.70711);
        Vector ans = new Vector(0,0.70711,-0.70711);
        assertEquals(p.normal(x1).equals(ans),true);
    }
}