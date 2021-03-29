import static org.junit.jupiter.api.Assertions.*;

class CanvasTest {
    @org.junit.jupiter.api.Test
    void writePixel() {
        Canvas c = new Canvas(2,2);
        Color c1 = new Color(1,0,0);
        c.writePixel(1,1,c1);
        assertEquals(c.getPixel(1,1).equals(c1),true);
    }
}