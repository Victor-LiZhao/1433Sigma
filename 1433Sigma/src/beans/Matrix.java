package beans;

public class Matrix {
	
	public int row;
	public int col;
	public double data[][];
		
	public	Matrix(){
		this.row=0;
		this.col=0;
	}
	public Matrix(int r,int c){
		this.row=r;
		this.col=c;
		this.data = new double[r][];
	    for(int i = 0; i < r; ++i)
	    {
	        //�ٿ��ڶ�ά�ռ�
	        data[i] = new double[c];
	    }  
	}
	
}
