package utils;

import beans.Matrix;

public class MatrixUtils {
	/**
	 * 求给定矩阵的转置矩阵
	 * @param m 所要求的矩阵
	 * @return 转置之后的矩阵
	 */
	public	static Matrix transpose(Matrix m){
		int r=m.row;
		int c=m.col;
		Matrix m_t=new Matrix(c,r);
		for(int i=0;i<c;++i)
		{
			for(int j=0;j<r;++j)
			{
				m_t.data[i][j]=m.data[j][i];
			}
		}
		return m_t;//矩阵转置 
		
	}
	/**
	 * 求给定矩阵的逆矩阵
	 * @param m 所要求的矩阵
	 * @return 所求矩阵的逆矩阵
	 */
	public	static Matrix inverse(Matrix m){
		int n=0;
		if(m.col!=m.row)
		{
			System.out.println("错误，不是一个方阵！");
			return null; 
		}
		n=m.col;
		double a[][]=m.data;
	    
		double b[][];
		//开辟b数组    保存增广矩阵 
		b=new double[n][];
		for(int i = 0; i < n; ++i)
	    {
	        //再开第二维空间
	        b[i] = new double[2*n];
	    }
		
		double c[][];
		//开辟c数组  保存结果矩阵 
		c=new double[n][];
		for(int i = 0; i < n; ++i)
	    {
	        //再开第二维空间
	        c[i] = new double[n];
	    }
	    
		double det1,yinzhi;
		double bb;
		int i,j,kk=0,k,u;
		
		//生成增广矩阵 
		for(i=0;i<n;i++)
			for(j=0;j<2*n;j++)
				b[i][j]=0;
		for(i=0;i<n;i++)
			for(j=0;j<n;j++)
				b[i][j]=a[i][j];
	    for(j=0;j<n;j++)
		       b[j][n+j]=1;
		kk=0;

		kk=0;

		det1=fun(a,n);
	   for(i=0;i<n;i++)
	   {   if(b[i][i]==0) 
	       for(j=i;j<n;j++)
		   {  if(b[j][i]!=0)
		  temp(b[i],b[j],n);
		  }
		 for(k=i+1;k<n;k++)
		  {  yinzhi=-1*b[k][i]/b[i][i];
			  for(u=0;u<2*n;u++)
			  { b[k][u]=b[k][u]+b[i][u]*yinzhi;
			  }
		  }
	  }
		det1=fun(a,n);
		 if(det1==0)
		    System.out.println("此矩阵不可逆：");
			 if(det1!=0)
			 { 
				 for(i=0;i<n;i++)
				 {bb=b[i][i];
	      	       for(j=0;j<2*n;j++)
				     b[i][j]=b[i][j]/bb;
				 }
			 for(i=n-1;i>0;i--)
			 for(k=0;k<i;k++)
			 {bb=b[k][i];
				 for(u=0;u<2*n;u++)
					 b[k][u]=b[k][u]-bb*b[i][u];
			 }
			 }

	 
	   for(i=0;i<n;i++)
		  for(j=0;j<n;j++)
		         c[i][j]=b[i][j+n];
	    kk=0;
	     if(det1!=0)
		{ 	

		} 
		Matrix m_t=new Matrix(n,n);
		m_t.data=c; 
		return m_t;    //矩阵求逆
	}
	/**
	 *求两个矩阵相乘
	 * @param m1 第一个矩阵
	 * @param m2 第二个矩阵
	 * @return 第一个矩阵与第二个矩阵相称的结果
	 */
	public	static Matrix multiply(Matrix m1,Matrix m2){
		double a[][]=m1.data;
		double b[][]=m2.data;
		double c[][];
		int m=m1.row;
		int n=m1.col;
		int q=m2.col;
		int i,j,kk=0;
		kk=0;
		//开辟c数组  保存结果矩阵 
		c=new double[m][];
		for(i = 0; i < m; ++i)
	    {
	        //再开第二维空间
	        c[i] = new double[q];
	    }
		
		for(i=0;i<m;i++)
	      for(j=0;j<q;j++)
		  {
		        c[i][j]=0;
			 for(int k=0;k<n;k++)
			c[i][j]=c[i][j]+a[i][k]*b[k][j];
		 }
		 
		Matrix m_m=new Matrix(m,q);
		 m_m.data=c;
		 	kk=0;
		 return m_m;  //矩阵相乘
	}
	public	static double MatDet(Matrix m){
		return 0;   //矩阵求行列式值
	}
	public	static double Creat_M(Matrix m,int r,int c){
		return c;  //求给定行列元素的代数余子式
	}
	
	/**
	 * 矩阵求逆的内部函数
	 * @param aa
	 * @param bb
	 * @param n
	 */
	private static void temp(double aa[],double bb[],int n)
	{   int i;
	   double temp1;
	    for(i=0;i<n;i++)
		{temp1=aa[i];aa[i]=bb[i];bb[i]=temp1;  }  
	}
	/**
	 * 矩阵求逆的内部函数
	 * @param array
	 * @param n
	 * @return
	 */
	private static double fun(double array[][],int n)
	{   int ii,jj,k,u;
	    double det1=1,yin;
	   for(ii=0;ii<n;ii++)
	   {   if(array[ii][ii]==0) 
	       for(jj=ii;jj<n;jj++)
		   {  if(array[jj][ii]!=0)
		  temp(array[ii],array[jj],n);
		  }
		  for(k=ii+1;k<n;k++)
		  {  yin=-1*array[k][ii]/array[ii][ii];
			  for(u=0;u<n;u++)
			  { array[k][u]=array[k][u]+array[ii][u]*yin;
			  }
		  }
	   }
	  for(ii=0;ii<n;ii++)
	   det1=det1*array[ii][ii];
	    return (det1);
	}
}
