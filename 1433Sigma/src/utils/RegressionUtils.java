package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import beans.Matrix;

public class RegressionUtils {
	private static double y_Mean;
	private static double x_Mean[];

	/**
	 * 根据给定的矩阵生成X矩阵
	 * 
	 * @param matrix
	 *            给定的参数矩阵
	 * @return 生成的X矩阵
	 */
	public static Matrix makeM_X(Matrix matrix) {
		Matrix newMatrix = new Matrix(matrix.row, matrix.col);
		double sum = 0.0;
		x_Mean = new double[matrix.col];
		for (int j = 0; j < matrix.col; ++j) {
			sum = 0.0;
			for (int i = 0; i < matrix.row; ++i) {
				sum += matrix.data[i][j];
			}
			x_Mean[j] = sum / matrix.row;
		}

		for (int i = 0; i < matrix.row; ++i) {
			for (int j = 0; j < matrix.col; ++j) {
				newMatrix.data[i][j] = matrix.data[i][j] - x_Mean[j];
			}
		}

		return newMatrix;
	}

	/**
	 * 根据给定的矩阵生成Y矩阵
	 * 
	 * @param matrix
	 *            给定的值矩阵
	 * @return 生成的Y矩阵
	 */
	public static Matrix makeM_Y(Matrix matrixY) {
		Matrix newMatrixY = new Matrix(matrixY.row, matrixY.col);
		y_Mean = 0.0;
		double sum = 0.0;
		sum = 0.0;
		for (int j = 0; j < matrixY.row; ++j) {
			sum += matrixY.data[j][0];
		}
		y_Mean = sum / matrixY.row;

		for (int i = 0; i < matrixY.row; ++i) {
			newMatrixY.data[i][0] = matrixY.data[i][0] - y_Mean;
		}
		return newMatrixY;
	}

	/**
	 * 计算回归
	 * 
	 * @param matrixX
	 *            参数矩阵
	 * @param matrixY
	 *            函数值矩阵
	 * @return 回归计算得到的参数数组
	 */
	public static double[] compute(Matrix matrixX, Matrix matrixY) {

		Matrix newMatrixX = makeM_X(matrixX);
		Matrix newMatrixY = makeM_Y(matrixY);
		Matrix matrix = MatrixUtils.multiply(MatrixUtils.multiply(
				MatrixUtils.inverse(MatrixUtils.multiply(
						MatrixUtils.transpose(newMatrixX), newMatrixX)),
				MatrixUtils.transpose(newMatrixX)), newMatrixY);
		double[] result = new double[matrix.row + 1];
		int i = 0;
		int m = matrix.row;
		int q = matrix.col;
		if (q != 1)
			System.out.println("计算失败！！！");
		for (i = 0; i < m; i++) {
			result[i + 1] = matrix.data[i][0];

		}

		double sum = 0.0;
		for (i = 0; i < m; i++) {
			sum += matrix.data[i][0] * x_Mean[i];
		}
		double b0 = y_Mean - sum;
		result[0] = b0;
		return result;
	}

	/**
	 * 预测函数，特征变量为6个理化性质*3
	 */
	public static void predict_pcv() {
		List<String> resultList=new ArrayList<String>();
		String filename;
		for (int ii = 0; ii < 500; ii++) {
//		for (int ii = 0; ii < 8000; ii++) {
			filename = "D:/pcvOriginal/single-" + ii + ".txt";
			BufferedReader br = null;
			File file = new File(filename);
			List<String> nodes = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					nodes.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Matrix matrixX, matrixY;
			if (nodes != null && nodes.size() != 0) {
				matrixX = new Matrix(nodes.size() - 1, 18);
				matrixY = new Matrix(nodes.size() - 1, 1);
				for (int i = 0; i < nodes.size() - 1; ++i) {
					String s[] = nodes.get(i + 1).split("\t");
					matrixY.data[i][0] = Double.valueOf(s[22]);
					matrixX.data[i][0] = Double.valueOf(s[2]);
					matrixX.data[i][1] = Double.valueOf(s[3]);
					matrixX.data[i][2] = Double.valueOf(s[4]);
					matrixX.data[i][3] = Double.valueOf(s[5]);
					matrixX.data[i][4] = Double.valueOf(s[6]);
					matrixX.data[i][5] = Double.valueOf(s[7]);
					matrixX.data[i][6] = Double.valueOf(s[9]);
					matrixX.data[i][7] = Double.valueOf(s[10]);
					matrixX.data[i][8] = Double.valueOf(s[11]);
					matrixX.data[i][9] = Double.valueOf(s[12]);
					matrixX.data[i][10] = Double.valueOf(s[13]);
					matrixX.data[i][11] = Double.valueOf(s[14]);
					matrixX.data[i][12] = Double.valueOf(s[16]);
					matrixX.data[i][13] = Double.valueOf(s[17]);
					matrixX.data[i][14] = Double.valueOf(s[18]);
					matrixX.data[i][15] = Double.valueOf(s[19]);
					matrixX.data[i][16] = Double.valueOf(s[20]);
					matrixX.data[i][17] = Double.valueOf(s[21]);

				}
				double result[] = compute(matrixX, matrixY);

				String s[] = nodes.get(0).split("\t");
				Matrix matrixTest = new Matrix(18, 1);
				matrixTest.data[0][0] = Double.valueOf(s[3]);
				matrixTest.data[1][0] = Double.valueOf(s[4]);
				matrixTest.data[2][0] = Double.valueOf(s[5]);
				matrixTest.data[3][0] = Double.valueOf(s[6]);
				matrixTest.data[4][0] = Double.valueOf(s[7]);
				matrixTest.data[5][0] = Double.valueOf(s[8]);
				matrixTest.data[6][0] = Double.valueOf(s[10]);
				matrixTest.data[7][0] = Double.valueOf(s[11]);
				matrixTest.data[8][0] = Double.valueOf(s[12]);
				matrixTest.data[9][0] = Double.valueOf(s[13]);
				matrixTest.data[10][0] = Double.valueOf(s[14]);
				matrixTest.data[11][0] = Double.valueOf(s[15]);
				matrixTest.data[12][0] = Double.valueOf(s[17]);
				matrixTest.data[13][0] = Double.valueOf(s[18]);
				matrixTest.data[14][0] = Double.valueOf(s[19]);
				matrixTest.data[15][0] = Double.valueOf(s[20]);
				matrixTest.data[16][0] = Double.valueOf(s[21]);
				matrixTest.data[17][0] = Double.valueOf(s[22]);
				double predictResult = 0.0;
				for (int i = 0; i < result.length - 1; ++i) {
					predictResult += matrixTest.data[i][0] * result[i + 1];
					// System.out.println(result[i]);
				}
				predictResult += result[0];
				double trueResult = Double.valueOf(s[23]);
				double percent = Math.abs(predictResult - trueResult)
						/ trueResult;
				String resultString=ii + "\t" + "sequence:" + "\t" + s[1] + "\t"
						+ "predictValue: " + "\t" + predictResult + "\t"
						+ "true Value: " + "\t" + trueResult + "\t"
						+ "errorPercent:" + "\t" + percent;
				System.out.println(ii + "\t" + "sequence:" + "\t" + s[1] + "\t"
						+ "predictValue: " + "\t" + predictResult + "\t"
						+ "true Value: " + "\t" + s[23] + "\t"
						+ "errorPercent:" + "\t" + percent);
				resultList.add(resultString);
			}

		}
		
		writePredictResult(resultList);
	}

	
	/**
	 * 将预测得到的8000组结果写入文件中
	 * @param resultList 预测的结果表
	 */
	private static void writePredictResult(List<String> resultList) {
		String outfilename="d:predictResult.txt";
		File file=new File(outfilename);
		PrintWriter pw=null;
		try{
			pw=new PrintWriter(new FileWriter(file));
			for(int i=0;i<resultList.size();++i)
				pw.println(resultList.get(i));
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			pw.close();
		}
		
	}

	/**
	 * 预测函数，特征变量为3个理化性质*3
	 */
	public static void predict_rsf() {
		String filename;
		// for(int ii=0;ii<500;ii++){
		for (int ii = 0; ii < 8000; ii++) {
			filename = "D:/rsfOriginal/single-" + ii + ".txt";
			BufferedReader br = null;
			File file = new File(filename);
			List<String> nodes = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					nodes.add(line);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			} finally {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			Matrix matrixX, matrixY;
			if (nodes != null && nodes.size() != 0) {
				matrixX = new Matrix(nodes.size() - 1, 9);
				matrixY = new Matrix(nodes.size() - 1, 1);
				for (int i = 0; i < nodes.size() - 1; ++i) {
					String s[] = nodes.get(i + 1).split("\t");
					matrixY.data[i][0] = Double.valueOf(s[13]);
					matrixX.data[i][0] = Double.valueOf(s[2]);
					matrixX.data[i][1] = Double.valueOf(s[3]);
					matrixX.data[i][2] = Double.valueOf(s[4]);
					matrixX.data[i][3] = Double.valueOf(s[6]);
					matrixX.data[i][4] = Double.valueOf(s[7]);
					matrixX.data[i][5] = Double.valueOf(s[8]);
					matrixX.data[i][6] = Double.valueOf(s[10]);
					matrixX.data[i][7] = Double.valueOf(s[11]);
					matrixX.data[i][8] = Double.valueOf(s[12]);

				}
				double result[] = compute(matrixX, matrixY);

				String s[] = nodes.get(0).split("\t");
				Matrix matrixTest = new Matrix(9, 1);
				matrixTest.data[0][0] = Double.valueOf(s[3]);
				matrixTest.data[1][0] = Double.valueOf(s[4]);
				matrixTest.data[2][0] = Double.valueOf(s[5]);
				matrixTest.data[3][0] = Double.valueOf(s[7]);
				matrixTest.data[4][0] = Double.valueOf(s[8]);
				matrixTest.data[5][0] = Double.valueOf(s[9]);
				matrixTest.data[6][0] = Double.valueOf(s[11]);
				matrixTest.data[7][0] = Double.valueOf(s[12]);
				matrixTest.data[8][0] = Double.valueOf(s[13]);
				double predictResult = 0.0;
				for (int i = 0; i < result.length - 1; ++i) {
					predictResult += matrixTest.data[i][0] * result[i + 1];
					// System.out.println(result[i]);
				}
				predictResult += result[0];
				double trueResult = Double.valueOf(s[14]);
				double percent = Math.abs(predictResult - trueResult)
						/ trueResult;
				
				System.out.println(ii + "\t" + "sequence:" + "\t" + s[1] + "\t"
						+ "predictValue: " + "\t" + predictResult + "\t"
						+ "true Value: " + "\t" + trueResult + "\t"
						+ "errorPercent:" + "\t" + percent);
			}

		}
	}

	public static void main(String[] args) {
		//predict_pcv();
		// predict_rsf();
		predict_new();
	}

	private static void predict_new() {
		String filename;
		 for(int ii=0;ii<500;ii++){
		//for (int ii = 0; ii < 8000; ii++) {
			filename = "D:/pcvR/single-" + ii + ".txt";
			BufferedReader br = null;
			File file = new File(filename);
			List<String> nodes = new ArrayList<String>();
			try {
				br = new BufferedReader(new FileReader(file));
				String line = "";
				while ((line = br.readLine()) != null) {
					nodes.add(line);
				}
			} catch (IOException e) {
				e.printStackTrace();

			} finally {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Matrix matrixX, matrixY;
			int row=nodes.size() - 1;
			int col=nodes.get(0).split("\t").length-1;
			//System.out.println("row="+row+"\tcol="+col);
			if (nodes != null && nodes.size() != 0) {
				matrixX = new Matrix(row, col);
				matrixY = new Matrix(row, 1);
				for (int i = 0; i < row; ++i) {
					String s[] = nodes.get(i + 1).split("\t");
				//	System.out.println(i+" "+s.length);
					matrixY.data[i][0] = Double.valueOf(s[col]);
					for(int j=0;j<col;j++)
					{
						matrixX.data[i][j] = Double.valueOf(s[j]);
					}
				}
				double result[] = compute(matrixX, matrixY);

				String s[] = nodes.get(0).split("\t");
				Matrix matrixTest = new Matrix(col, 1);
				for(int j=0;j<col;j++){
					matrixTest.data[j][0] = Double.valueOf(s[j]);
				}
				double predictResult = 0.0;
				for (int i = 0; i < result.length - 1; ++i) {
					predictResult += matrixTest.data[i][0] * result[i + 1];
				//	System.out.println(result[i]);
				}
				predictResult += result[0];
				double trueResult = Double.valueOf(s[col]);
				double percent = Math.abs(predictResult - trueResult)
						/ trueResult;
				
				System.out.println(ii + "\t" + "predictValue: " + "\t" + predictResult + "\t"
						+ "true Value: " + "\t" + trueResult + "\t"
						+ "errorPercent:" + "\t" + percent);
			}

		}
	}
}
