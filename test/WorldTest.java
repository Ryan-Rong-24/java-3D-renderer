import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    @org.junit.jupiter.api.Test
    void test() {
        Light[] l = new Light[2];
        l[0] = new Light(new Point(1,1,1),new Color(1,1,1));
        l[1] = new Light(new Point(1,1,1),new Color(1,1,1));

        Sphere[] s = new Sphere[2];
        Sphere a = new Sphere();
        a.transform = a.scale(2,2,2);
        s[0] = a;
        s[1] = new Sphere();

        World w = new World(l,s);
//        assertEquals(w.objects[1].equals(new Sphere()),true);
    }
    @org.junit.jupiter.api.Test
    void shadeHit() {
        World d = new World("DEFAULT");

        Ray r2 = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere firstObjectInWorld = d.objects[0];
        Intersection i2 = new Intersection(4,r2,firstObjectInWorld);
        Computation comp2 = i2.prepareComputation(r2);
        assertEquals(d.shadeHit(comp2,0).equals(new Color(0.38066,0.47583,0.2855)),true);

        Ray r3 = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere s = new Sphere();
        Intersection i3 = new Intersection(4,r3,s);
        Computation comp3 = i3.prepareComputation(r3);
        assertEquals(comp3.isInside,false);

        Ray r4 = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Sphere s2 = new Sphere();
        Intersection i4 = new Intersection(1,r4,s2);
        Computation comp4 = i4.prepareComputation(r4);
        assertEquals(comp4.isInside,true);
        assertEquals(comp4.normal.equals(new Tuple(0,0,-1,0)),true);
        assertEquals(comp4.eye.equals(new Tuple(0,0,-1,0)),true);
        assertEquals(comp4.point.equals(new Tuple(0,0,1,1)),true);

        d.lights[0] = new Light(new Point(0, 0.25, 0), new Color(1, 1, 1));

        Ray r = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Sphere secondObjectInWorld = d.objects[1];
        Intersection i = new Intersection(0.5,r,secondObjectInWorld);
        Computation comp = i.prepareComputation(r);
        assertEquals(d.shadeHit(comp,0).equals(new Color(0.90498,0.90498,0.90498)),true);


        Light l = new Light(new Point(0,0,-10),new Color(1,1,1));
        Sphere ss1 = new Sphere();
        Sphere ss2 = new Sphere();
        ss2.transform = ss2.translate(0,0,10);
        Sphere[] spheres = {ss1,ss2};
        Light[] lights = {l};
        World w = new World(lights,spheres);
        Ray rr = new Ray(new Point(0,0,5),new Vector(0,0,1));
        Intersection ii = new Intersection(4,rr,ss2);
        Computation c = ii.prepareComputation(rr);
        assertEquals(w.shadeHit(c,0).equals(new Color(0.1,0.1,0.1)), true);
    }
    @org.junit.jupiter.api.Test
    void colorAt() {
        World d = new World("DEFAULT");
        Ray r = new Ray(new Point(0,0,-5),new Vector(0,1,0));
        assertEquals(d.colorAt(r,0).equals(new Color()),true);

        Ray r2 = new Ray(new Point(0,0,-5),new Vector(0,0,1));
        d.colorAt(r2,0).printColor();
        assertEquals(d.colorAt(r2,0).equals(new Color(0.38066,0.47583,0.2855)),true);

        d.objects[0].m.ambient = 1;
        d.objects[1].m.ambient = 1;
        Ray r3 = new Ray(new Point(0,0,0.75),new Vector(0,0,-1));
        d.colorAt(r3,0).printColor();
        d.objects[1].m.c.printColor();
        assertEquals(d.colorAt(r3,0).equals(d.objects[1].m.c),true);

    }
    @org.junit.jupiter.api.Test
    void intersectWorld(){
        World d = new World("DEFAULT");
        Ray r = new Ray(new Point(0,0,-5),new Vector(0,0,1));
        int i = (int) d.intersectWorld(r)[0];
        ArrayList<Intersection> ints = (ArrayList<Intersection>) d.intersectWorld(r)[1];
        System.out.println(i);
        for (Intersection j: ints) {
            System.out.println(j.lambda);
        }
    }
    @org.junit.jupiter.api.Test
    void isInShadow(){
        World d = new World("DEFAULT");
        Point p = new Point(0,10,0);
        assertEquals(d.isInShadow(p,d.lights[0]),false);

        Point p2 = new Point(10,-10,10);
        assertEquals(d.isInShadow(p2,d.lights[0]),true);

        Point p3 = new Point(-20,20,-20);
        assertEquals(d.isInShadow(p3,d.lights[0]),false);

        Point p4 = new Point(-2,2,-2);
        assertEquals(d.isInShadow(p4,d.lights[0]),false);
    }
}