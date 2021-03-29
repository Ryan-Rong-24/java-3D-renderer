import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void intersectionWithRay() {
        Sphere t1 = new Sphere();
        Sphere t2 = new Sphere();
        t1.m.c = new Color(0.8, 1.0, 0.6);
        t1.m.diffused = 0.7;
        t1.m.specular = 0.2;
        t2.transform = t2.scale(0.5, 0.5, 0.5);
        Ray tRay = new Ray(new Point(0,0,-5), new Vector(0,0,1));
        Hit tHit = t2.intersectionWithRay(tRay);
        System.out.println("h");
        for(Intersection i: tHit.intersection)
            System.out.println(i.lambda);

        Ray r = new Ray(new Point(0,0,-5), new Vector(0,0,1));
        Sphere s = new Sphere();
        Hit a =  s.intersectionWithRay(r);
        assertEquals(a.numOfIntersection,2);
        assertEquals(a.hasIntersection,true);
        assertEquals(a.intersection[0].lambda,4);
        assertEquals(a.intersection[1].lambda,6);

        Ray r2 = new Ray(new Point(0,1,-5), new Vector(0,0,1));
        Sphere s2 = new Sphere();
        Hit a2 =  s2.intersectionWithRay(r2);
        assertEquals(a2.numOfIntersection,1);
        assertEquals(a2.hasIntersection,true);
        assertEquals(a2.intersection[0].lambda,5);

        Ray r3 = new Ray(new Point(0,2,-5), new Vector(0,0,1));
        Sphere s3 = new Sphere();
        Hit a3 =  s3.intersectionWithRay(r3);
        assertEquals(a3.numOfIntersection,0);
        assertEquals(a3.hasIntersection,false);

        Ray r4 = new Ray(new Point(0,0,0), new Vector(0,0,1));
        Sphere s4 = new Sphere();
        Hit a4 =  s4.intersectionWithRay(r4);
        assertEquals(a4.numOfIntersection,2);
        assertEquals(a4.hasIntersection,true);
        assertEquals(a4.intersection[0].lambda,-1);
        assertEquals(a4.intersection[1].lambda,1);

        Ray r5 = new Ray(new Point(0,0,5), new Vector(0,0,1));
        Sphere s5 = new Sphere();
        Hit a5 =  s5.intersectionWithRay(r5);
        assertEquals(a5.numOfIntersection,2);
        assertEquals(a5.hasIntersection,true);
        assertEquals(a5.intersection[0].lambda,-6);
        assertEquals(a5.intersection[1].lambda,-4);

    }
    @Test
    void normal() {
        Sphere s = new Sphere();
        Point p = new Point(1,0,0);
        Tuple t = s.normal(p);
        Tuple ans = new Tuple(1,0,0,0);
        assertEquals(ans.equals(t),true);

        Sphere s2 = new Sphere();
        Matrix m = s2.translate(0,1,0);
        s2.setTransform(m);
        Point p2 = new Point(0,1.70711,-0.70711);
        Tuple t2 = s2.normal(p2);
        Tuple ans2 = new Tuple(0,0.70711,-0.70711,0);
        assertEquals(ans2.equals(t2),true);
    }
    @Test
    void reflect() {
        Tuple in = new Tuple(0,-1,0,0);
        Tuple normal = new Tuple(0.7071067,0.7071067,0,0);
        Tuple ans = new Tuple(1,0,0,0);
        in.reflect(normal).printTuple();
        assertEquals(in.reflect(normal).equals(ans),true);
    }
    @Test
    void material() {
        Sphere s = new Sphere();
        assertEquals(s.m.ambient,0.1);
        s.m.ambient = 0.2;
        assertEquals(s.m.ambient,0.2);

    }
    //1. ambient reflection, 2. diffused reflection, 3. specular reflection
}