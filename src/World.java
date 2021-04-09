import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class World extends General{
    Light[] lights;
    Object[] objects;
    public World (){

    }
    public World(String s) {
        Light l = new Light(new Point(-10, 10, -10), new Color(1, 1, 1));
        Sphere s1 = new Sphere();
        Sphere s2 = new Sphere();
        s1.m.c = new Color(0.8, 1.0, 0.6);
        s1.m.diffused = 0.7;
        s1.m.specular = 0.2;
        s2.transform = s2.scale(0.5, 0.5, 0.5);

        Light[] lights = {l};
        Object[] spheres = {s1, s2};
        this.lights = lights;
        this.objects = spheres;
    }
    public World(Light[] l,Object[] s){
        lights = l;
        objects = s;
    }
    public World(Light l,Object[] s){
        lights = new Light[1];
        lights[0] = l;
        objects = s;
    }
    public Color shadeHit(Computation c, int i){ //算阴影 shade hitting on another object, there could be multiple
        Color color = new Color();
        for (Light light:this.lights) {
            color = color.add(c.shape.m.lighting(light,c,this.isInShadow(new Point(c.overPoint),light)));
            //when calculating floats, there are small errors, use overPoint to deal with this error
        }
        return color;
    }
    public Object[] intersectWorld(Ray r){
        int count = 0;
        ArrayList<Intersection> intersections = new ArrayList<>();
        for (Object s: objects) { //获取每个shape 和 ray 交点个数
            Hit h = new Hit();
            if(s instanceof Plane)
                h = ((Plane)s).intersectionWithRay(r);
            else if(s instanceof Sphere)
                h = ((Sphere)s).intersectionWithRay(r);

            count += h.numOfIntersection;
            if(h.numOfIntersection==1){
                intersections.add(h.intersection[0]);
            } else if (h.numOfIntersection==2){
                intersections.add(h.intersection[0]);
                intersections.add(h.intersection[1]);
            }
        }

        for (int i = 0; i < intersections.size()-1; i++) {
            for (int j = 0; j < intersections.size()-i- 1; j++) {
                if (intersections.get(j).lambda > intersections.get(j+1).lambda) {
                    Collections.swap(intersections, j, j+1);
                }
            }
        }

//        for (int i = 0; i < intersections.size()-1; i++) {
//            int min_id = i;
//            for (int j = i+1; j < intersections.size(); j++)
//                if(intersections.get(j).lambda<intersections.get(i).lambda)
//                    min_id = j;
//            Collections.swap(intersections,i,min_id);
//            for(Intersection ppp: intersections)
//                System.out.print(ppp.lambda+" ");
//            System.out.println();
//        }

//        System.out.println("AS");
//        for(Intersection h:intersections){
//            System.out.println(h.lambda);
//        }
//        System.out.println("ED");

        return new Object[] {count, intersections};
    }
    public Color colorAt(Ray r,int remaining){ //the color of reflection at this point of the ray
        ArrayList<Intersection> i = (ArrayList<Intersection>) intersectWorld(r)[1];
        res temp = hit(i);
        if(temp.hitted){
            return shadeHit(temp.h.prepareComputation(r),remaining);
        }
        return black;
    }
    public boolean isInShadow(Point p, Light l){
        Vector v = new Vector(l.pos.subtract(p));
        double dist = v.calcMag();
        Vector dir = new Vector(v.normalize());
        Ray r = new Ray(p,dir);
        ArrayList<Intersection> i = (ArrayList<Intersection>)this.intersectWorld(r)[1];
        res hitStuff = this.hit(i);
        if(hitStuff.hitted&&hitStuff.h.lambda<dist)
            return true;
        return false;
    }
}
