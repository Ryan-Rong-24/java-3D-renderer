import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntersectionTest {

    @Test
    void prepareComputation() {
        World deFault = new World();
        Ray r = new Ray(new Point(0,0,-5), new Vector(0,0,1));
        Sphere s = new Sphere();
        Intersection i = new Intersection(4,r,s);
        Computation comp = i.prepareComputation(r);
        assertEquals(comp.isInside,false);

        Ray r2 = new Ray(new Point(0,0,0), new Vector(0,0,1));
        Intersection i2 = new Intersection(1,r2,s);
        Computation comp2 = i2.prepareComputation(r2);
        assertEquals(comp2.isInside,true);
        Vector ansNormal = new Vector(0,0,-1);
        Vector ansEye = new Vector(0,0,-1);
        Point ansPoint = new Point(0,0,1);
        assertEquals(ansNormal.equals(comp2.normal),true);
        assertEquals(ansEye.equals(comp2.eye),true);
        assertEquals(ansPoint.equals(comp2.point),true);

        World d = new World("DEFAULT");
        Sphere firstObjectInWorld = (Sphere)d.objects[0];
        Intersection i3 = new Intersection(4,r,firstObjectInWorld);
        Computation comp3 = i3.prepareComputation(r);
        assertEquals(comp.isInside,false);
    }
}