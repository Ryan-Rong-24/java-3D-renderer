import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @org.junit.jupiter.api.Test
    void add() {
        Color c1 = new Color(0.9,0.6,0.75);
        Color c2 = new Color(0.7,0.1,0.25);
        Color answer = new Color(1.6,0.7,1);
        assertEquals(c1.add(c2).color.equals(answer.color),true);
    }

    @org.junit.jupiter.api.Test
    void subtract() {
        Color c1 = new Color(0.9,0.6,0.75);
        Color c2 = new Color(0.7,0.1,0.25);
        Color answer = new Color(0.2,0.5,0.5);
        assertEquals(c1.subtract(c2).color.equals(answer.color),true);
    }

    @org.junit.jupiter.api.Test
    void multiplyScalar() {
        Color c1 = new Color(0.9,0.6,0.75);
        Color answer = new Color(1.8,1.2,1.5);
        assertEquals(c1.multiplyScalar(2).color.equals(answer.color),true);
    }

    @org.junit.jupiter.api.Test
    void multiply() {
        Color c1 = new Color(1,0.2,0.4);
        Color c2 = new Color(0.9,1,0.1);
        Color answer = new Color(0.9,0.2,0.04);
        assertEquals(c1.multiply(c2).color.equals(answer.color),true);
    }
}