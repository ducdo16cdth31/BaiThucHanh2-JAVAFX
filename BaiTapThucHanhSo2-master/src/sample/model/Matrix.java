package sample.model;

import java.io.Serializable;

public class Matrix {
    private int col, row;
    private float[][] data;
    public int getCol() {
        return col;
    }
    public void setCol(int col) {
        this.col = col;
    }
    public int getRow() {
        return row;
    }
    public void setRow(int row) {
        this.row = row;
    }
    public Matrix(int r, int c) {
        col = c;
        row = r;
        setData(new float[r][c]);
    }
    public float[][] getData() {
        return data;
    }
    public void setData(float[][] data) {
        this.data = data;
    }public Matrix Add(Matrix d){
        Matrix m = new Matrix(row, col);
        for(int i=0; i<row; i++)
            for(int j=0; j<col; j++)
                m.data[i][j]=d.data[i][j]+data[i][j];
        return m;
    }
    public void deleteMatrix(){
        col = row = 0;
        data = null;
    }
}