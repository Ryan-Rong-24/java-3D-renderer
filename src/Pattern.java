public class Patterns {
    Matrix transform;
    Color c1,c2;
    public Patterns(Color c1, Color c2){
        this.c1 = c1;
        this.c2 = c2;
        transform = new Matrix(4,4).getIdentity();
    }
    public Color patternAt(Point p, Matrix m, String text){ //what color it is at this point

    }
}
