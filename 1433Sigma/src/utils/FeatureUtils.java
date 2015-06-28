package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.PCValue;
import beans.RSFValue;

public class FeatureUtils {

	public static Map<Character,Map<String,Double>> featureMap;
	static {
		featureMap=new HashMap<Character,Map<String,Double>>();
		makeFeatureMap();
	}
	
	/**
	 * 创建feature map
	 */
	public static void makeFeatureMap(){
		
		//读入pcv
		List<PCValue> listPcv=FileUtils.getPcvMatrix();
		Map<Character,PCValue> map=PCVUtils.makePCVMap(listPcv);
		Set<Character> keyset=map.keySet();
		Iterator<Character> iterator=keyset.iterator();
		while(iterator.hasNext()){
			Character c=iterator.next();
			PCValue pcvalue=map.get(c);
			Map<String,Double> valueMap=new HashMap<String,Double>();
			valueMap.put("hrdrophobicity", pcvalue.getH());
			valueMap.put("vsc", pcvalue.getVsc());
			valueMap.put("polarity", pcvalue.getP1());
			valueMap.put("polarizability", pcvalue.getP2());
			valueMap.put("sasa", pcvalue.getSasa());
			valueMap.put("ncisc", pcvalue.getNcisc());
			featureMap.put(c, valueMap);
		}
		
		
		//  add  mass  property
		Map<Character,Double> massMap=readProperty("mass.txt");
		Map<Character,Double> massMapnorm=normalizeMap(massMap);
		addProperty(massMapnorm,"mass");
		//	add hydrogenbond property
		Map<Character,Double> hydrogenbondMap=readProperty_2("hydrogenbond.txt");
		Map<Character,Double> hydrogenbondMapnorm=normalizeMap(hydrogenbondMap);
		addProperty(hydrogenbondMapnorm,"hydrogenbond");
		// add Electrostaticpotential property
		Map<Character,Double> electrostaticpotentialMap=readProperty_2("electrostaticpotential.txt");
		Map<Character,Double> electrostaticpotentialMapnorm=normalizeMap(hydrogenbondMap);
		addProperty(electrostaticpotentialMapnorm,"electrostaticpotential");
	}
	
	private static Map<Character, Double> normalizeMap(Map<Character, Double> map) {
		Collection<Double> valueset=map.values();
		Double[] temparray=new Double[valueset.size()];
		valueset.toArray(temparray);
		double sum=0.0;
		for(int i=0;i<20;i++){
			double temp=Double.valueOf(temparray[i]);
			sum+=temp;
		}
		Double mean=sum/20.0;
		double sj=0.0;
		for(int i=0;i<20;i++){
			double temp=Double.valueOf(temparray[i]);
			sj+=(temp-mean)*(temp-mean);
		}
		sj=sj/20.0;
		sj=Math.sqrt(sj);
		Set<Character> keyset=map.keySet();
		Iterator<Character> iterator=keyset.iterator();
		while(iterator.hasNext()){
			Character c=iterator.next();
			Double property=map.get(c);
			Double newproperty=(property-mean)/sj;
			map.put(c, newproperty);
		}
		return map;
	}

	private static Map<Character, Double> readProperty_2(String filename) {
		BufferedReader br = null;
		File file=new File(filename);
		Map<String,Character> map=FileUtils.getAbbrMap_key_abbr();
		Map<Character,Double> map1=new HashMap<Character,Double>();
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			while((line=br.readLine())!=null)
			{
				String s[]=line.split("\t");
				map1.put(map.get(s[0].toUpperCase()), Double.valueOf(s[1]));
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
		return map1;
	}

	private static void addProperty(Map<Character,Double> map,String propertyname){
		Set<Character> keyset=map.keySet();
		Iterator<Character> iterator=keyset.iterator();
		while(iterator.hasNext()){
			Character c=iterator.next();
			Double property=map.get(c);
			Map<String,Double> valueMap=featureMap.get(c);
			valueMap.put(propertyname,property);
			featureMap.put(c, valueMap);
		}
	}
	
	private static Map<Character,Double> readProperty(String filename) {
		BufferedReader br = null;
		File file=new File(filename);
		Map<Character,Double> map=new HashMap<Character,Double>();
		try {
			br=new  BufferedReader(new FileReader(file));
			String line="";
			while((line=br.readLine())!=null)
			{
				String s[]=line.split("\t");
				map.put(Character.valueOf(s[0].charAt(0)), Double.valueOf(s[1]));
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
	 * ******未完成
	 * 标准化所有的特征标准化方法是同时对数据进行中心化-压缩处理
	 */
	private static void normalize(){
		//TODO
		Map<String, Double> map = featureMap.get(featureMap.keySet().iterator().next());
		Set<String> keySet = map.keySet();
		Iterator<String> iterator = keySet.iterator();
		while (iterator.hasNext()) {
			String property_name = iterator.next();
		}
	}
	public static void main(String[] args) {

	}

}
