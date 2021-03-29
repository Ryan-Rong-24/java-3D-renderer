import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class MaterialTest {

    @Test
    void lighting() {
        Tuple eye = new Vector(0,0,-1);
        Tuple normal = new Vector(0,0,-1);
        Light l = new Light(new Point(0,10,-10),new Color(1,1,1));
        Material m = new Material();
        Point pos = new Point(0,0,0);
        Sphere s = new Sphere();
        Computation comp = new Computation();
        comp.eye = eye;
        comp.normal = normal;
        comp.point = pos;
        comp.shape = s;
        assertEquals(m.lighting(l,comp,false).equals(new Color(0.7364,0.7364,0.7364)),true);

        Tuple eye2 = new Vector(0,-0.70711,-0.70711);
        Tuple normal2 = new Vector(0,0,-1);
        Light l2 = new Light(new Point(0,10,-10),new Color(1,1,1));
        Material m2 = new Material();
        Point pos2 = new Point(0,0,0);
        Sphere s2 = new Sphere();
        Computation comp2 = new Computation();
        comp2.eye = eye2;
        comp2.normal = normal2;
        comp2.point = pos2;
        comp2.shape = s2;
        assertEquals(m2.lighting(l2,comp2,false).equals(new Color(1.6364,1.6364,1.6364)),true);

        Tuple eye3 = new Vector(0,0.70711,-0.70711);
        Tuple normal3 = new Vector(0,0,-1);
        Light l3 = new Light(new Point(0,0,-10),new Color(1,1,1));
        Material m3 = new Material();
        Point pos3 = new Point(0,0,0);
        Sphere s3 = new Sphere();
        Computation comp3 = new Computation();
        comp3.eye = eye3;
        comp3.normal = normal3;
        comp3.point = pos3;
        comp3.shape = s3;
        assertEquals(m3.lighting(l3,comp3,false).equals(new Color(1,1,1)),true);

        Tuple eye4 = new Vector(0,0,-1);
        Tuple normal4 = new Vector(0,0,-1);
        Light l4 = new Light(new Point(0,0,-10),new Color(1,1,1));
        Material m4 = new Material();
        Point pos4 = new Point(0,0,0);
        Sphere s4 = new Sphere();
        Computation comp4 = new Computation();
        comp4.eye = eye4;
        comp4.normal = normal4;
        comp4.point = pos4;
        comp4.shape = s4;
        assertEquals(m4.lighting(l4,comp4,false).equals(new Color(1.9,1.9,1.9)),true);

        Tuple eye5 = new Vector(0,0,-1);
        Tuple normal5 = new Vector(0,0,-1);
        Light l5 = new Light(new Point(0,0,-10),new Color(1,1,1));
        Material m5 = new Material();
        Point pos5 = new Point(0,0,0);
        Sphere s5 = new Sphere();
        Computation comp5 = new Computation();
        comp5.eye = eye5;
        comp5.normal = normal5;
        comp5.point = pos5;
        comp5.shape = s5;
        assertEquals(m5.lighting(l5,comp5,true).equals(new Color(0.1,0.1,0.1)),true);
    }
}