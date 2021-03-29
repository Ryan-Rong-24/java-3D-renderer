import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class General {
    public class res{
        Intersection h;
        Boolean hitted;
        public res(Intersection h, Boolean hitted){
            this.h = h;
            this.hitted = hitted;
        }
    }
    public static Color black = new Color(0,0,0);
    public boolean doubleEqual(double a, double b, double e) {
        return Math.abs(a - b) <= e;
    }

    public boolean doubleEqual(double a, double b) {
        return Math.abs(a - b) <= 0.0001;
    }
    public Object[] hit(Intersection[] i){
        ArrayList<Intersection> intersections = new ArrayList<>();
        for (int j = 0; j < i.length; j++) {
            intersections.add(i[j]);
        }
        Collections.sort(intersections, new Comparator<Intersection>() {
            @Override
            public int compare(Intersection o1, Intersection o2) {
                if(o1.lambda-o2.lambda>0){
                    return 0;
                } return 1;
            }
        });
        for (int j = 0; j < i.length; j++) {
            if(i[j].lambda>=0) return new Object[]{i[j],true};
        }
        return new Object[]{new Intersection(),false};
    }
    public res hit(ArrayList<Intersection> i){

        double smallest = Integer.MAX_VALUE;
        Intersection h = new Intersection();
        Boolean hitted = false;
        for (int j = 0; j < i.size(); j++) {
            if(i.get(j).lambda>=0&&i.get(j).lambda<=smallest){
               smallest = i.get(j).lambda;
               hitted = true;
               h = i.get(j);
            }
        }
        return new res(h,hitted);
//        return new Object[]{h,hitted};
    }
}
