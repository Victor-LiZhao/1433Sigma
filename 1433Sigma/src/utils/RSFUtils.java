package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.RSFValue;

public class RSFUtils {
	public static Map<Character,RSFValue> makeRSFMap(List<RSFValue> list){
		Map<Character,RSFValue> map=new HashMap<Character,RSFValue>();
		for(RSFValue rsf:list){
			map.put(rsf.getAa(),rsf);
		}
		System.out.println("map size="+map.size());
		return map;
		
	}
}
