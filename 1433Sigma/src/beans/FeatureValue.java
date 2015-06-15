package beans;

import java.util.HashMap;
import java.util.Map;

public class FeatureValue {
	private Character aa;
	private Map<String,Double> map;
	public FeatureValue(){
		map=new HashMap<String,Double>();
	}
	public Character getAa() {
		return aa;
	}
	public void setAa(Character aa) {
		this.aa = aa;
	}
	public Map<String, Double> getMap() {
		return map;
	}
	public void setMap(Map<String, Double> map) {
		this.map = map;
	}
	
}
