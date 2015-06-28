package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.PCValue;

public class PCVUtils {
	public static Map<Character,PCValue> makePCVMap(List<PCValue> list){
		Map<Character,PCValue> map=new HashMap<Character,PCValue>();
		for(PCValue pcv:list){
			map.put(pcv.getAa(),pcv);
		}
		System.out.println("map size="+map.size());
		return map;
		
	}
	
	public static void main(String[] args) {
		makePCVMatrix_toOne();
		makePCVMatrix_toNormalize();
	}
	
	public static void makePCVMatrix_toOne(){
		String filename="pcv.txt";
		BufferedReader br = null;
		File file=new File(filename);
		String pcvs[][] = new String[20][7];
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			int i=0;
			while((line=br.readLine())!=null)
			{
				String sTemp[]=line.split(" ");
				pcvs[i]=sTemp;
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int j=1;j<7;j++){
			double max=-99999;double min=99999;
			for(int i=0;i<20;i++){
				double temp=Double.valueOf(pcvs[i][j]);
				if(temp>max)
						max=temp;
				if(temp<min)
					min=temp;
			}
			for(int i=0;i<20;i++){
				double temp=(Double.valueOf(pcvs[i][j])-min)/(max-min);
				pcvs[i][j]=String.valueOf(temp);
			}
		}
		
		String outfilename="d:newPCV.txt";
		File file1=new File(outfilename);
		PrintWriter pw=null;

		try {
			pw=new PrintWriter(new FileWriter(file1));
			for(int i=0;i<pcvs.length;++i){
				pw.println(pcvs[i][0]+" "+pcvs[i][1]+" "+pcvs[i][2]+" "+pcvs[i][3]+" "+pcvs[i][4]+" "+pcvs[i][5]+" "+pcvs[i][6]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			pw.close();
		}
		
	}///-fun
	
	public static void makePCVMatrix_toNormalize(){
		String filename="pcv.txt";
		BufferedReader br = null;
		File file=new File(filename);
		String pcvs[][] = new String[20][7];
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			int i=0;
			while((line=br.readLine())!=null)
			{
				String sTemp[]=line.split(" ");
				pcvs[i]=sTemp;
				i++;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally
		{
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		for(int j=1;j<7;j++){
			double sum=0.0;
			for(int i=0;i<20;i++){
				double temp=Double.valueOf(pcvs[i][j]);
				sum+=temp;
			}
			Double mean=sum/20.0;
			double sj=0.0;
			for(int i=0;i<20;i++){
				double temp=Double.valueOf(pcvs[i][j]);
				sj+=(temp-mean)*(temp-mean);
			}
			sj=sj/20.0;
			sj=Math.sqrt(sj);
			
			for(int i=0;i<20;i++){
				double temp=(Double.valueOf(pcvs[i][j])-mean)/sj;
				pcvs[i][j]=String.valueOf(temp);
			}
		}
		
		String outfilename="newPCV-norm.txt";
		File file1=new File(outfilename);
		PrintWriter pw=null;

		try {
			pw=new PrintWriter(new FileWriter(file1));
			for(int i=0;i<pcvs.length;++i){
				pw.println(pcvs[i][0]+" "+pcvs[i][1]+" "+pcvs[i][2]+" "+pcvs[i][3]+" "+pcvs[i][4]+" "+pcvs[i][5]+" "+pcvs[i][6]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			pw.close();
		}
		
	}///-fun
}
