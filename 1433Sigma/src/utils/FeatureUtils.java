package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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
	 * ¥¥Ω®feature map
	 */
	public static void makeFeatureMap(){
		
		//∂¡»Îpcv
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
		addProperty(massMap,"mass");
		//	add hydrogenbond property
		Map<Character,Double> hydrogenbondMap=readProperty_2("hydrogenbond.txt");
		addProperty(hydrogenbondMap,"hydrogenbond");
		// add Electrostaticpotential property
		Map<Character,Double> electrostaticpotentialMap=readProperty_2("electrostaticpotential.txt");
		addProperty(electrostaticpotentialMap,"electrostaticpotential");
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

	public static void main(String[] args) {

	}

}
