import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void position(){
        Point o = new Point(2,3,4);
        Vector d = new Vector(1,0,0);
        Ray r = new Ray(o,d);

        Tuple t = r.position(0);
        Tuple ans = new Tuple(2,3,4,1);

        assertEquals(t.equals(ans),true);
    }
}