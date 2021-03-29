import java.util.*;



public class Matrix extends General {
    int width;
    int height;
    double[][] matrix;
    double determinant = 0;
    boolean hasDeter = false;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.matrix = new double[width][height];
    }
    private class Result{
        Matrix m;
        boolean b;
        private Result(Matrix m, boolean b){
            this.m = m;
            this.b = b;
        }
    }

    public void assignValue(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    public boolean checkEqual(Matrix m2) {

        if (this.width != m2.width || this.height != m2.height) {
            return false;
        }

        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (!doubleEqual(this.matrix[i][j], m2.matrix[i][j]))
                    return false;
            }
        }
        return true;
    }

    public Matrix addMatrix(Matrix m2) {
        Matrix m3 = new Matrix(this.width, this.height);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                m3.matrix[i][j] = this.matrix[i][j] + m2.matrix[i][j];
            }
        }
        return m3;
    }

    public Matrix subtractMatrix(Matrix m2) {
        Matrix m3 = new Matrix(this.width, this.height);
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                m3.matrix[i][j] = this.matrix[i][j] - m2.matrix[i][j];
            }
        }
        return m3;
    }

    public Matrix multiplyMatrix(Matrix m2) {
        Matrix m3 = new Matrix(this.width, m2.height);
        for (int i = 0; i < m3.width; i++) {
            for (int j = 0; j < m3.height; j++) {
//                m3.matrix[i][k] = 0;
                for (int k = 0; k < this.height; k++) {
                    m3.matrix[i][j] += this.matrix[i][k] * m2.matrix[k][j];
                }
            }
        }
        return m3;
    }

    public Matrix multiplyScalar(double s) {
        Matrix m3 = new Matrix(this.width, this.height);
        for (int i = 0; i < m3.width; i++) {
            for (int j = 0; j < m3.height; j++) {
                m3.matrix[i][j] = this.matrix[i][j] * s;
            }
        }
        return m3;
    }

    public Tuple multiplyTuple(Tuple t) {
        double[] d = {t.x, t.y, t.z, t.w};
        double[] ans = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            double temp = 0;
            for (int j = 0; j < 4; j++) {
                temp += this.matrix[i][j] * d[j];
            }
            ans[i] = temp;
        }
        return new Tuple(ans[0], ans[1], ans[2], ans[3]);
    }

    public Matrix getIdentity() {
        int i = this.width;
        Matrix m = new Matrix(i, i);
        for (int r = 0; r < i; r++) {
            m.matrix[r][r] = 1;
        }
        return m;
    }

    public void printMatrix() {
        int i = this.width;
        for (int r = 0; r < i; r++) {
            for (int c = 0; c < i; c++) {
                System.out.print(this.matrix[r][c] + " ");
            }
            System.out.println();
        }
    }

    public Matrix transpose() {
        Matrix m = new Matrix(4, 4);
        for (int r = 0; r < 4; r++) {
            for (int c = r; c < 4; c++) {
                m.matrix[r][c] = this.matrix[c][r];
                m.matrix[c][r] = this.matrix[r][c];
            }
        }
        return m;
    }

    public double determinant() {
        this.hasDeter = true;
        if (this.width == 2 && this.height == 2) {
            double d = this.matrix[0][0] * this.matrix[1][1] - this.matrix[0][1] * this.matrix[1][0];
            return d;
        }
        double d = 0;
        for (int i = 0; i < this.width; i++)
            d += this.matrix[0][i] * this.cofactor(0, i);

        if(d!=0) this.determinant = d;
        return d;
    }

    public Matrix subMatrix(int i, int j) {
        Matrix m = new Matrix(this.width - 1, this.height - 1);
        ArrayList<Double> vars = new ArrayList<>();
        for (int r = 0; r < this.width; r++) {
            if (r != i) {
                for (int c = 0; c < this.height; c++) {
                    if (c != j) {
                        vars.add(this.matrix[r][c]);
                    }
                }
            }
        }
        int index = 0;
        for (int r = 0; r < this.width - 1; r++) {
            for (int c = 0; c < this.height - 1; c++) {
                m.matrix[r][c] = vars.get(index);
                index++;
            }
        }
        return m;
    }

    //minor = determinant of submatrix
    public double minor(int i, int j) {
        return this.subMatrix(i, j).determinant();
    }

    public double cofactor(int i, int j) {
        return minor(i, j) * Math.pow(-1, i + j);
    }

    public Object[] inverse(){
        Matrix m = new Matrix(this.width, this.height);

        if(!this.hasDeter) //hasDeter -> 我算过了
            this.determinant = this.determinant();
        if(this.determinant==0)
            return new Object[] {m,false};
        //get inverse
        for (int r = 0; r < this.width; r++) {
            for (int c = 0; c < this.height; c++) {
                double cofact = cofactor(r,c);
                m.assignValue(c,r,cofact/this.determinant);
            }
        }
        return new Object[] {m,true};
    }
}
