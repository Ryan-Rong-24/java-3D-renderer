import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    @Test
    void assignValue() {
    }

    @Test
    void checkEqual() {
        Matrix m = new Matrix(2,2);
        Matrix m2 = new Matrix(2,2);
        int t = 1;
        for(int i=0;i<m.width;i++){
            for (int j = 0; j < m.height; j++) {
                m.matrix[i][j] = t;
                m2.matrix[i][j] = t++;
            }
        }
        assertEquals(m.checkEqual(m2),true);
    }
    @Test
    void multiplyMatrix() {
        Matrix m = new Matrix(2,2);
        Matrix m2 = new Matrix(2,2);
        Matrix m3 = new Matrix(2,2);
        Matrix m4 = new Matrix(2,2);

        int t = 1;
        for(int i=0;i<m.width;i++){
            for (int j = 0; j < m.height; j++) {
                m.matrix[i][j] = t;
                m3.matrix[i][j] = t++;
            }
        }
        m2.assignValue(0,0,1);
        m2.assignValue(0,1,0);
        m2.assignValue(1,0,0);
        m2.assignValue(1,1,1);
        m4.assignValue(0,0,2);
        m4.assignValue(0,1,0);
        m4.assignValue(1,0,0);
        m4.assignValue(1,1,2);

        Matrix result = m.multiplyMatrix(m2);
        Matrix result2 = m3.multiplyMatrix(m4);

        Matrix ans1 = new Matrix(2,2);
        double temp[][] = {{1,2},{3,4}};
        ans1.matrix = temp;
        Matrix ans2 = new Matrix(2,2);
        double temp2[][] = {{2,4},{6,8}};
        ans2.matrix = temp2;

        assertEquals(result.checkEqual(ans1),true);
        assertEquals(result2.checkEqual(ans2),true);

        Matrix mm = new Matrix(2,2);
        double tempmm[][] = {{1,2},{3,4}};
        mm.matrix = tempmm;
        Matrix i = new Matrix(2,2);
        double tempi[][] = {{1,0},{0,1}};
        i.matrix = tempi;

        assertEquals(i.multiplyMatrix(mm).checkEqual(mm),true);

    }
    @Test
    void multiplyTuple() {
        Matrix m = new Matrix(2, 2);
        Tuple t =new Tuple(1,2,3,1);

        double temp[][] = {{1,2,3,4},{2,4,4,2},{8,6,4,1},{0,0,0,1}};
        m.matrix = temp;
        Tuple ans = new Tuple(18,24,33,1);

        Tuple result = m.multiplyTuple(t);
        
        assertEquals(result.equals(ans),true);

    }
    @Test
    void getIdentity() {
        Matrix m = new Matrix(2, 2);
        double temp[][] = {{1,2},{3,4}};
        m.matrix = temp;
        Matrix i = m.getIdentity();
        i.multiplyMatrix(m).printMatrix();
        assertEquals(i.multiplyMatrix(m).checkEqual(m),true);

    }
    @Test
    void transpose() {
        Matrix m = new Matrix(4, 4);
        double temp[][] = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        m.matrix = temp;
        Matrix ans = new Matrix(4, 4);
        double temp2[][] = {{1,5,9,13},{2,6,10,14},{3,7,11,15},{4,8,12,16}};
        ans.matrix = temp2;

        assertEquals(m.transpose().checkEqual(ans),true);

    }
    @Test
    void determinant() {
        Matrix m = new Matrix(2, 2);
        double temp[][] = {{1,5},{-3,2}};
        m.matrix = temp;
        double ans = 17;

        assertEquals(m.doubleEqual(m.determinant(),ans),true);

        Matrix m2 = new Matrix(3, 3);
        double temp2[][] = {{1,2,6},{-5,8,-4},{2,6,4}};
        m2.matrix = temp2;
        double ans2 = -196;

        double c = m2.cofactor(0,0);
        double cAns = 56;
        assertEquals(m2.doubleEqual(c,cAns),true);

        assertEquals(m2.doubleEqual(m2.determinant(),ans2),true);

        Matrix m3 = new Matrix(4, 4);
        double temp3[][] = {{-2,-8,3,5},{-3,1,7,3},{1,2,-9,6},{-6,7,7,-9}};
        m3.matrix = temp3;
        double ans3 = -4071;

        double c2 = m3.cofactor(0,0);
        double cAns2 = 690;
        assertEquals(m3.doubleEqual(c2,cAns2),true);

        assertEquals(m3.doubleEqual(m3.determinant(),ans3),true);
    }
    @Test
    void subMatrix() {
        Matrix m = new Matrix(3, 3);
        double temp[][] = {{1,5,0},{-3,2,7},{0,6,-3}};
        m.matrix = temp;

        Matrix ans = new Matrix(2, 2);
        double temp2[][] = {{1,0},{0,-3}};
        ans.matrix = temp2;
        assertEquals(m.subMatrix(1,1).checkEqual(ans),true);
    }
    @Test
    void cofactor() {
        Matrix m = new Matrix(3, 3);
        double temp[][] = {{3,5,0},{2,-1,-7},{6,-1,5}};
        m.matrix = temp;

        double d = m.cofactor(0,0);
        double d2 = m.cofactor(1,0);

        double ans = -12;
        double ans2 = -25;

        assertEquals(m.doubleEqual(d,ans),true);
        assertEquals(m.doubleEqual(d2,ans2),true);
    }
    @Test
    void inverse() {
        Matrix m = new Matrix(4, 4);
        double temp[][] = {{6,4,4,4},{5,5,7,6},{4,-9,3,-7},{9,1,7,-6}};
        m.matrix = temp;

        Matrix m2 = new Matrix(4, 4);
        double temp2[][] = {{-4,2,-2,-3},{9,6,2,6},{0,-5,1,-5},{0,0,0,0}};
        m2.matrix = temp2;

        Matrix a = new Matrix(4, 4);
        double at[][] = {{3,-9,7,3},{3,-8,2,-9},{-4,4,4,1},{-6,5,-1,1}};
        a.matrix = at;

        Matrix b = new Matrix(4, 4);
        double bt[][] = {{8,2,2,2},{3,-1,7,0},{7,0,5,4},{6,-2,0,5}};
        b.matrix = bt;

        Matrix c = a.multiplyMatrix(b);

        assertEquals(m.multiplyMatrix((Matrix)m.inverse()[0]).checkEqual(m.getIdentity()),true);
        assertEquals(m.doubleEqual(m.determinant(),-2120),true);
        assertEquals(m2.inverse()[1],false);
        assertEquals(m2.doubleEqual(m2.determinant(),0),true);

        assertEquals(c.multiplyMatrix((Matrix)b.inverse()[0]).checkEqual(a),true);
    }

}