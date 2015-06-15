package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Node;
import beans.PCValue;
import beans.RSFValue;

public class FileUtils {
	public static List<Node> readData()
	{
		String filename="sigma2.txt";
		//String filename="sigma_right.txt";
		BufferedReader br = null;
		File file=new File(filename);
		List<Node> nodes=new ArrayList<Node>();
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			while((line=br.readLine())!=null)
			{
				Node record=new Node(line);
				nodes.add(record);
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
		return nodes;	
	}
	//get pcv matrix
	public static List<PCValue> getPcvMatrix(){
		//String filename="newPCV.txt";
		String filename="pcv.txt";
		BufferedReader br = null;
		File file=new File(filename);
		List<PCValue> nodes=new ArrayList<PCValue>();
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			while((line=br.readLine())!=null)
			{
				PCValue record=new PCValue(line);
				nodes.add(record);
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
		return nodes;	
	}
	
	//get rsf matrix
		public static List<RSFValue> getRsfMatrix(){
			String filename="rsf.txt";
			BufferedReader br = null;
			File file=new File(filename);
			Map<String,Character> map=getAbbrMap_key_abbr();
			List<RSFValue> nodes=new ArrayList<RSFValue>();
			try {
				br=new  BufferedReader(new FileReader(file));
				String line="";
				while((line=br.readLine())!=null)
				{
					String s[]=line.split("\t");
					RSFValue rsfvalue=new RSFValue();
					Character aa=map.get(s[0].toUpperCase());
					rsfvalue.setAa(aa);
					rsfvalue.setComposition(Double.valueOf(s[1]));
					rsfvalue.setPolarity(Double.valueOf(s[2]));
					rsfvalue.setVolume(Double.valueOf(s[3]));
					nodes.add(rsfvalue);
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
			return nodes;	
		}
		
		/**
		 * 返回氨基酸的代码及其缩写的map
		 * 氨基酸缩写全为大写字母
		 * @return   map 键是aa  值是abbr
		 */
		public static Map<Character,String> getAbbrMap_key_aa(){
			Map<Character,String> map=new HashMap<Character,String>();
			String filename="aa_abbr.txt";
			BufferedReader br = null;
			File file=new File(filename);
			try {
				br=new  BufferedReader(new FileReader(file));
				String line="";
				while((line=br.readLine())!=null)
				{
					String s[]=line.split("\t");
					if(s.length==2){
						String abbr=s[0].toUpperCase();
						Character aa=s[1].charAt(0);
						map.put(aa, abbr);
					}
					else{
						System.out.println("ERROR!Import AA_Abbr ERROR!");
					}
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
			return map;
		}
		
		/**
		 * 返回氨基酸的代码及其缩写的map
		 * @return   map 键是abbr 值是aa
		 */
		public static Map<String,Character> getAbbrMap_key_abbr(){
			Map<String,Character> map=new HashMap<String,Character>();
			String filename="aa_abbr.txt";
			BufferedReader br = null;
			File file=new File(filename);
			try {
				br=new  BufferedReader(new FileReader(file));
				String line="";
				while((line=br.readLine())!=null)
				{
					String s[]=line.split("\t");
					if(s.length==2){
						String abbr=s[0].toUpperCase();
						Character aa=s[1].charAt(0);
						map.put( abbr,aa);
					}
					else{
						System.out.println("ERROR!Import AA_Abbr ERROR!");
					}
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
			return map;
		}
}
