import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GeneralTest {

    @Test
    void doubleEqual() {
        General test = new General();
        double [] A = {0.1,0.7,1.2,1.5};
        double [] B = {0.2,0.9,1.2,1.5};
        boolean [] result = {true,false,true,true};
        for(int i =0;i<4;i++){
            System.out.print(i);
            assertEquals(test.doubleEqual(A[i],B[i]),result[i]);
        }
    }
}