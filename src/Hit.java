public class Hit {
    boolean hasIntersection;
    int numOfIntersection;
    Intersection[] intersection;

    public Hit(boolean i, int c, Intersection[] ii) {
        hasIntersection = i;
        numOfIntersection = c;
        intersection = ii;
    }

    public Hit(boolean i, int c) {
        hasIntersection = i;
        numOfIntersection = c;
    }

    public Hit() {

    }
}
